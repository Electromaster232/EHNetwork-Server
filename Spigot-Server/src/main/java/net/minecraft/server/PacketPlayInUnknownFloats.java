package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInUnknownFloats
  implements Packet<PacketListenerPlayIn>
{
  public float a;
  public float b;

  public PacketPlayInUnknownFloats()
  {
  }

  public void a(PacketDataSerializer dataSerializer) throws IOException
  {
    this.a = dataSerializer.readFloat();
    this.b = dataSerializer.readFloat();
  }

  public void b(PacketDataSerializer dataSerializer) throws IOException
  {
    dataSerializer.writeFloat(this.a);
    dataSerializer.writeFloat(this.b);
  }

  public void a(PacketListenerPlayIn listenerPlayIn)
  {
    listenerPlayIn.a(this);
  }

  public float a() {
    return this.a;
  }

  public float b() {
    return this.b;
  }
}