package net.minecraft.server;

import java.io.IOException;

// Referenced classes of package net.minecraft.server.v1_8_R3:
//            Packet, EntityPainting, PacketDataSerializer, EnumDirection,
//            PacketListenerPlayOut, BlockPosition, PacketListener

public class PacketPlayOutSpawnEntityPainting implements Packet
{

    public PacketPlayOutSpawnEntityPainting() {}

    public PacketPlayOutSpawnEntityPainting(EntityPainting entitypainting)
    {
        a = entitypainting.getId();
        b = entitypainting.getBlockPosition();
        c = entitypainting.direction;
        d = entitypainting.art.B;
    }

    public void a(PacketDataSerializer packetdataserializer)
        throws IOException
    {
        a = packetdataserializer.e();
        d = packetdataserializer.c(EntityPainting.EnumArt.A);
        b = packetdataserializer.c();
        c = EnumDirection.fromType2(packetdataserializer.readUnsignedByte());
    }

    public void b(PacketDataSerializer packetdataserializer)
        throws IOException
    {
        packetdataserializer.b(a);
        packetdataserializer.a(d);
        packetdataserializer.a(b);
        packetdataserializer.writeByte(c.b());
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
    public BlockPosition b;
    public EnumDirection c;
    public String d;
}