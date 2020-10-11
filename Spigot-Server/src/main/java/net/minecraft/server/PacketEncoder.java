package net.minecraft.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.Attribute;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class PacketEncoder extends MessageToByteEncoder<Packet>
{
  private static final Logger a = LogManager.getLogger();
  private static final Marker b = MarkerManager.getMarker("PACKET_SENT", NetworkManager.b);
  private final EnumProtocolDirection c;

  public PacketEncoder(EnumProtocolDirection paramEnumProtocolDirection)
  {
    this.c = paramEnumProtocolDirection;
  }

  protected void encode(ChannelHandlerContext paramChannelHandlerContext, Packet paramPacket, ByteBuf paramByteBuf) throws Exception
  {
    int version = NetworkManager.getVersion(paramChannelHandlerContext.channel());
    Integer localInteger = ((EnumProtocol)paramChannelHandlerContext.channel().attr(NetworkManager.c).get()).a(this.c, paramPacket, version == 47);

    if (a.isDebugEnabled()) {
      a.debug(b, "OUT: [{}:{}] {}", new Object[] { paramChannelHandlerContext.channel().attr(NetworkManager.c).get(), localInteger, paramPacket.getClass().getName() });
    }

    if (localInteger == null) {
      throw new IOException("Can't serialize unregistered packet");
    }

    PacketDataSerializer localPacketDataSerializer = new PacketDataSerializer(paramByteBuf, version);
    localPacketDataSerializer.b(localInteger.intValue());
    try
    {
      if ((paramPacket instanceof PacketPlayOutNamedEntitySpawn)) {
        paramPacket = paramPacket;
      }
      paramPacket.b(localPacketDataSerializer);
    } catch (Throwable localThrowable) {
      a.error(localThrowable);
    }
  }
}