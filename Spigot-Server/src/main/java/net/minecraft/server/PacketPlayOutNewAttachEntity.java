package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutNewAttachEntity implements Packet<PacketListenerPlayOut>
{
    public int a;
    public int[] b;

    public PacketPlayOutNewAttachEntity()
    {
    }

    public PacketPlayOutNewAttachEntity(Entity vehicle, Entity[] riding)
    {
        this.a = vehicle.getId();
        this.b = new int[riding.length];

        for (int i=0; i<riding.length; i++)
            this.b[i] = riding[i].getId();
    }

    public PacketPlayOutNewAttachEntity(Entity vehicle, Entity riding)
    {
        this.a = vehicle.getId();
        this.b = new int[riding != null ? 1 : 0];

        if (riding != null)
            this.b[0] = riding.getId();
    }

    public PacketPlayOutNewAttachEntity(int entity, int[] riding)
    {
        this.a = entity;
        this.b = riding;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException
    {
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException
    {
        packetdataserializer.b(this.a);
        packetdataserializer.b(this.b.length);
        for (int i=0; i < this.b.length; i++)
        {
            packetdataserializer.b(this.b[i]);
        }
    }

    public void a(PacketListenerPlayOut packetlistenerplayout)
    {
        packetlistenerplayout.a(this);
    }
}