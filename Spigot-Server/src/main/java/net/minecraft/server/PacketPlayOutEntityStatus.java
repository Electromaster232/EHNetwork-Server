package net.minecraft.server;

public class PacketPlayOutEntityStatus
    implements Packet
{
    public int a;
    public byte b;

    public PacketPlayOutEntityStatus() {}

    public PacketPlayOutEntityStatus(Entity entity, byte byte0)
    {
        a = entity.getId();
        b = byte0;
    }

    public void a(PacketDataSerializer packetdataserializer)
    {
        a = packetdataserializer.readInt();
        b = packetdataserializer.readByte();
    }

    public void b(PacketDataSerializer packetdataserializer)
    {
        packetdataserializer.writeInt(a);
        packetdataserializer.writeByte(b);
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