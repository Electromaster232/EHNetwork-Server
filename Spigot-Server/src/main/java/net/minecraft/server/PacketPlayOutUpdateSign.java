package net.minecraft.server;

import java.io.IOException;
import org.bukkit.craftbukkit.util.CraftChatMessage;

// Referenced classes of package net.minecraft.server.v1_8_R3:
//            Packet, IChatBaseComponent, PacketDataSerializer, PacketListenerPlayOut,
//            World, BlockPosition, PacketListener

public class PacketPlayOutUpdateSign implements Packet
{
    public World a;
    public BlockPosition b;
    public IChatBaseComponent c[];

    public PacketPlayOutUpdateSign() {}

    public PacketPlayOutUpdateSign(World world, BlockPosition blockposition, IChatBaseComponent aichatbasecomponent[])
    {
        a = world;
        b = blockposition;
        c = (new IChatBaseComponent[] {
            aichatbasecomponent[0], aichatbasecomponent[1], aichatbasecomponent[2], aichatbasecomponent[3]
        });
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException
    {
        b = packetdataserializer.c();
        c = new IChatBaseComponent[4];
        for(int i = 0; i < 4; i++)
        {
            packetdataserializer.a(c[i]);
        }

    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException
    {
        packetdataserializer.a(b);
        for(int i = 0; i < 4; i++)
        {
            if (packetdataserializer.version == 47)
            {
                packetdataserializer.a(c[i]);
            }
             else
            {
                packetdataserializer.a(c[i]);
            }
        }
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