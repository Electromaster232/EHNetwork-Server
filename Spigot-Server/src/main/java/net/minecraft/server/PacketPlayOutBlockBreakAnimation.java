package net.minecraft.server;

import java.io.IOException;

// Referenced classes of package net.minecraft.server.v1_8_R3:
//            Packet, PacketDataSerializer, PacketListenerPlayOut, BlockPosition,
//            PacketListener

public class PacketPlayOutBlockBreakAnimation
    implements Packet
{

    public PacketPlayOutBlockBreakAnimation()
    {
    }

    public PacketPlayOutBlockBreakAnimation(int i, BlockPosition blockposition, int j)
    {
        a = i;
        b = blockposition;
        c = j;
    }

    public void a(PacketDataSerializer packetdataserializer)
        throws IOException
    {
        a = packetdataserializer.e();
        b = packetdataserializer.c();
        c = packetdataserializer.readUnsignedByte();
    }

    public void b(PacketDataSerializer packetdataserializer)
        throws IOException
    {
        packetdataserializer.b(a);
        packetdataserializer.a(b);
        packetdataserializer.writeByte(c);
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
    public int c;
}