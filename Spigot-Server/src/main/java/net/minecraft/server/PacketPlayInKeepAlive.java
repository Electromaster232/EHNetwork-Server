package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInKeepAlive
  implements Packet<PacketListenerPlayIn>
{
  private int a;

  public void a(PacketListenerPlayIn paramPacketListenerPlayIn)
  {
    paramPacketListenerPlayIn.a(this);
  }

  public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    this.a = paramPacketDataSerializer.e();
  }

  public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    paramPacketDataSerializer.b(this.a);
  }

  public int a() {
    return this.a;
  }
}