package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutCustomPayload
  implements Packet<PacketListenerPlayOut>
{
  public String a;
  public PacketDataSerializer b;

  public PacketPlayOutCustomPayload()
  {
  }

  public PacketPlayOutCustomPayload(String paramString, PacketDataSerializer paramPacketDataSerializer)
  {
    this.a = paramString;
    this.b = paramPacketDataSerializer;

    if (paramPacketDataSerializer.writerIndex() > 1048576)
      throw new IllegalArgumentException("Payload may not be larger than 1048576 bytes");
  }

  public void a(PacketDataSerializer paramPacketDataSerializer)
    throws IOException
  {
    this.a = paramPacketDataSerializer.c(20);
    int i = paramPacketDataSerializer.readableBytes();
    if ((i < 0) || (i > 1048576)) {
      throw new IOException("Payload may not be larger than 1048576 bytes");
    }
    this.b = new PacketDataSerializer(paramPacketDataSerializer.readBytes(i));
  }

  public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    paramPacketDataSerializer.a(this.a);
    paramPacketDataSerializer.writeBytes(this.b);
  }

  public void a(PacketListenerPlayOut paramPacketListenerPlayOut)
  {
    paramPacketListenerPlayOut.a(this);
  }
}