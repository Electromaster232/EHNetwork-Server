package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutUnloadChunk
  implements Packet<PacketListenerPlayOut>
{
  public int a;
  public int b;

  public PacketPlayOutUnloadChunk()
  {
  }

  public PacketPlayOutUnloadChunk(int paramInt1, int paramInt2)
  {
    this.a = paramInt1;
    this.b = paramInt2;
  }

  public void a(PacketListenerPlayOut paramPacketListenerPlayOut)
  {
    paramPacketListenerPlayOut.a(this);
  }

  public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
  }

  public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    paramPacketDataSerializer.writeInt(this.a);
    paramPacketDataSerializer.writeInt(this.b);
  }
}