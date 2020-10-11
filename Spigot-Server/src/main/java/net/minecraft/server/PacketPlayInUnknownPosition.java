package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInUnknownPosition
  implements Packet<PacketListenerPlayIn>
{
  public double a;
  public double b;
  public double c;
  public float d;
  public float e;

  public PacketPlayInUnknownPosition()
  {
  }

  public void a(PacketDataSerializer dataSerializer) throws IOException
  {
    this.a = dataSerializer.readDouble();
    this.b = dataSerializer.readDouble();
    this.c = dataSerializer.readDouble();
    this.d = dataSerializer.readFloat();
    this.e = dataSerializer.readFloat();
  }

  public void b(PacketDataSerializer dataSerializer) throws IOException
  {
    dataSerializer.writeDouble(this.a);
    dataSerializer.writeDouble(this.b);
    dataSerializer.writeDouble(this.c);
    dataSerializer.writeFloat(this.d);
    dataSerializer.writeFloat(this.e);
  }

  public void a(PacketListenerPlayIn listenerPlayIn)
  {
    listenerPlayIn.a(this);
  }

  public double a() {
    return this.a;
  }

  public double b() {
    return this.b;
  }

  public double c() {
    return this.c;
  }

  public float d() {
    return this.d;
  }

  public float e() {
    return this.e;
  }
}