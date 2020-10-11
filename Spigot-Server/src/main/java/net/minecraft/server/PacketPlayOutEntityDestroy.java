package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutEntityDestroy
  implements Packet<PacketListenerPlayOut>
{
  public int[] a;

  public PacketPlayOutEntityDestroy()
  {
  }

  public PacketPlayOutEntityDestroy(int[] s)
  {
    this.a = s;
  }

  public void a(PacketDataSerializer f) throws IOException
  {
    this.a = new int[f.e()];

    for (int e = 0; e < this.a.length; e++)
      this.a[e] = f.e();
  }

  public void b(PacketDataSerializer q)
    throws IOException
  {
    q.b(this.a.length);

    for (int d = 0; d < this.a.length; d++)
      q.b(this.a[d]);
  }

  public void a(PacketListenerPlayOut w)
  {
    w.a(this);
  }
}