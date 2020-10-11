package net.minecraft.server;

import java.io.IOException;

// Referenced classes of package net.minecraft.server.v1_8_R3:
//            Packet, Entity, PacketDataSerializer, PacketListenerPlayOut,
//            PacketListener

public class PacketPlayOutEntityHeadRotation
    implements Packet
{

    public PacketPlayOutEntityHeadRotation()
    {
    }

    public PacketPlayOutEntityHeadRotation(Entity entity, byte byte0)
    {
        a = entity.getId();
        b = byte0;
    }

    public void a(PacketDataSerializer packetdataserializer)
        throws IOException
    {
        a = packetdataserializer.e();
        b = packetdataserializer.readByte();
    }

    public void b(PacketDataSerializer packetdataserializer)
        throws IOException
    {
        packetdataserializer.b(a);
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

    public int a;
    public byte b;
}