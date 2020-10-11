package net.minecraft.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.Attribute;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class PacketDecoder extends ByteToMessageDecoder
{
  private static final Logger a = LogManager.getLogger();
  private static final Marker b = MarkerManager.getMarker("PACKET_RECEIVED", NetworkManager.b);
  private final EnumProtocolDirection c;

  public PacketDecoder(EnumProtocolDirection paramEnumProtocolDirection)
  {
    this.c = paramEnumProtocolDirection;
  }

  protected void decode(ChannelHandlerContext paramChannelHandlerContext, ByteBuf paramByteBuf, List<Object> paramList) throws Exception
  {
    if (paramByteBuf.readableBytes() == 0) {
      return;
    }

    int version = NetworkManager.getVersion(paramChannelHandlerContext.channel());
    PacketDataSerializer localPacketDataSerializer = new PacketDataSerializer(paramByteBuf, version);
    int i = localPacketDataSerializer.e();
    Packet localPacket = ((EnumProtocol)paramChannelHandlerContext.channel().attr(NetworkManager.c).get()).a(this.c, i, version == 47);

    if (localPacket == null) {
      throw new IOException("Bad packet id " + i);
    }

    localPacket.a(localPacketDataSerializer);
    if (localPacketDataSerializer.readableBytes() > 0) {
      throw new IOException("Packet " + ((EnumProtocol)paramChannelHandlerContext.channel().attr(NetworkManager.c).get()).a() + "/" + i + " (" + localPacket.getClass().getSimpleName() + ") was larger than I expected, found " + localPacketDataSerializer.readableBytes() + " bytes extra whilst reading packet " + i);
    }
    paramList.add(localPacket);

    if (a.isDebugEnabled())
      a.debug(b, " IN: [{}:{}] {}", new Object[] { paramChannelHandlerContext.channel().attr(NetworkManager.c).get(), Integer.valueOf(i), localPacket.getClass().getName() });
  }
}