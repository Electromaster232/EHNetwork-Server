package net.minecraft.server;

import java.io.IOException;

// Referenced classes of package net.minecraft.server.v1_8_R3:
//            Packet, PacketDataSerializer, PacketListenerPlayOut, BlockPosition,
//            PacketListener

public class PacketPlayOutWorldEvent implements Packet
{
    public int a;
    public BlockPosition b;
    public int c;
    public boolean d;

    public PacketPlayOutWorldEvent() {}

    public PacketPlayOutWorldEvent(int i, BlockPosition blockposition, int j, boolean flag)
    {
        a = i;
        b = blockposition;
        c = j;
        d = flag;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException
    {
        a = packetdataserializer.readInt();
        b = packetdataserializer.c();
        c = packetdataserializer.readInt();
        d = packetdataserializer.readBoolean();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException
    {
        packetdataserializer.writeInt(a);
        packetdataserializer.a(b);
        packetdataserializer.writeInt(c);
        packetdataserializer.writeBoolean(d);
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