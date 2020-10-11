package net.minecraft.server;

import java.util.UUID;
import java.io.IOException;

public class PacketPlayOutBossBar
    implements Packet
{
    public UUID uuid;
    /**
    * 0 = Add
    * 1 = Remove
    * 2 = Update Health
    * 3 = Update Title
    * 4 = Update Style
    * 5 = Update Flags
    **/
    public int action;
    public String title;
    public float health;
    /**
    * 0 = Pinkish
    * 1 = Cyan
    * 2 = Red
    * 3 = Lime
    * 4 = Yellow
    * 5 = Purple
    * 6 = White
    **/
    public int color;
    public int dividers;
    /**
    * 0x1 = Darken sky
    * 0x2 = Dragon Bar
    **/
    public byte flags;

    public PacketPlayOutBossBar()
    {
    }

    public void a(PacketDataSerializer packetdataserializer)
    {
    }

    public void b(PacketDataSerializer packetdataserializer)
    {
        try {
            packetdataserializer.a(this.uuid);
            packetdataserializer.b(this.action);

            if (this.action == 0)
            {
                packetdataserializer.a(org.bukkit.craftbukkit.util.CraftChatMessage.fromString(title)[0]);
                packetdataserializer.writeFloat(health);
                packetdataserializer.b(color);
                packetdataserializer.b(dividers);
                packetdataserializer.writeByte(flags);
            }

            if (action == 2)
            {
                packetdataserializer.writeFloat(health);
            }

            if (action == 3)
            {
                packetdataserializer.a(org.bukkit.craftbukkit.util.CraftChatMessage.fromString(title)[0]);
            }

            if (action == 4)
            {
                packetdataserializer.b(color);
                packetdataserializer.b(dividers);
            }

            if (action == 5)
            {
                packetdataserializer.writeByte(flags);
            }
        } catch (IOException ex)
        {
            ex.printStackTrace();
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