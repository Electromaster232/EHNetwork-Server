package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutUpdateHealth implements Packet<PacketListenerPlayOut> {
  public float a;
  public int b;
  public float c;

    public PacketPlayOutUpdateHealth() {}

    public PacketPlayOutUpdateHealth(float f, int i, float f1)
    {
        a = f;
        b = i;
        c = f1;
    }

    public void a(PacketDataSerializer packetdataserializer)
    {
        a = packetdataserializer.readFloat();
        b = packetdataserializer.e();
        c = packetdataserializer.readFloat();
    }

    public void b(PacketDataSerializer packetdataserializer)
    {
        packetdataserializer.writeFloat(a);
        packetdataserializer.b(b);
        packetdataserializer.writeFloat(c);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout)
    {
        packetlistenerplayout.a(this);
    }
}