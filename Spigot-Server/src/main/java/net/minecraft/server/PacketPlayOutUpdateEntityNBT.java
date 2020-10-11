package net.minecraft.server;

import java.io.IOException;

// Referenced classes of package net.minecraft.server.v1_8_R3:
//            Packet, PacketDataSerializer, PacketListenerPlayOut, NBTTagCompound,
//            PacketListener

public class PacketPlayOutUpdateEntityNBT implements Packet
{
    public int a;
    public NBTTagCompound b;

    public PacketPlayOutUpdateEntityNBT() {}

    public PacketPlayOutUpdateEntityNBT(int i, NBTTagCompound nbttagcompound)
    {
        a = i;
        b = nbttagcompound;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException
    {
        a = packetdataserializer.e();
        b = packetdataserializer.h();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException
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