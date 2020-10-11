package net.minecraft.server;

import java.io.IOException;

// Referenced classes of package net.minecraft.server.v1_8_R3:
//            Packet, World, PacketDataSerializer, Block,
//            RegistryID, IBlockData, PacketListenerPlayOut, BlockPosition,
//            PacketListener

public class PacketPlayOutBlockChange
    implements Packet
{

    public PacketPlayOutBlockChange()
    {
    }

    public PacketPlayOutBlockChange(World world, BlockPosition blockposition)
    {
        a = blockposition;
        block = world.getType(blockposition);
    }

    public void a(PacketDataSerializer packetdataserializer)
        throws IOException
    {
        a = packetdataserializer.c();
        block = (IBlockData)Block.d.a(packetdataserializer.e());
    }

    public void b(PacketDataSerializer packetdataserializer)
        throws IOException
    {
        packetdataserializer.a(a);
        packetdataserializer.b(Block.d.b(block));
    }

    public void a(PacketListenerPlayOut packetlistenerplayout)
    {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener)
    {
        a((PacketListenerPlayOut)packetlistener);
    }

    public BlockPosition a;
    public IBlockData block;
}