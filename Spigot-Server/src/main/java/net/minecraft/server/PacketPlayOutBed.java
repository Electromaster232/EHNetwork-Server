package net.minecraft.server;

public class PacketPlayOutBed
    implements Packet
{
    public int a;
    public BlockPosition b;

    public PacketPlayOutBed()
    {
    }

    public PacketPlayOutBed(EntityHuman entityhuman, BlockPosition blockposition)
    {
        a = entityhuman.getId();
        b = blockposition;
    }

    public void a(PacketDataSerializer packetdataserializer)
    {
        a = packetdataserializer.e();
        b = packetdataserializer.c();
    }

    public void b(PacketDataSerializer packetdataserializer)
    {
        packetdataserializer.b(a);
        packetdataserializer.a(b);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout)
    {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener)
    {
        a((PacketListenerPlayOut)packetlistener);
    }
}