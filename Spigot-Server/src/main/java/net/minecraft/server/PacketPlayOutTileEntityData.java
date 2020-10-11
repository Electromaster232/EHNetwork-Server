package net.minecraft.server;

import java.io.IOException;

// Referenced classes of package net.minecraft.server.v1_8_R3:
//            Packet, PacketDataSerializer, PacketListenerPlayOut, BlockPosition,
//            NBTTagCompound, PacketListener

public class PacketPlayOutTileEntityData implements Packet
{

    public BlockPosition a;
    public int b;
    public NBTTagCompound c;

    public PacketPlayOutTileEntityData() {}

    public PacketPlayOutTileEntityData(BlockPosition blockposition, int i, NBTTagCompound nbttagcompound)
    {
        a = blockposition;
        b = i;
        c = nbttagcompound;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException
    {
        a = packetdataserializer.c();
        b = packetdataserializer.readUnsignedByte();
        c = packetdataserializer.h();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException
    {
        packetdataserializer.a(a);
        packetdataserializer.writeByte((byte)b);
        packetdataserializer.a(c);
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