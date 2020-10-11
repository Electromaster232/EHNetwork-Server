package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutAttachEntity implements Packet<PacketListenerPlayOut>
{

    public int a;
    public int b;
    public int c;

    public PacketPlayOutAttachEntity()
    {
    }

    public PacketPlayOutAttachEntity(int i, Entity entity, Entity entity1)
    {
        this.a = i;
        this.b = entity.getId();
        this.c = entity1 != null ? entity1.getId() : -1;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException
    {
        this.b = packetdataserializer.readInt();
        this.c = packetdataserializer.readInt();
        this.a = packetdataserializer.readUnsignedByte();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException
    {
        packetdataserializer.writeInt(this.b);
        packetdataserializer.writeInt(this.c);
        if (packetdataserializer.version == 47)
            packetdataserializer.writeByte(this.a);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout)
    {
        packetlistenerplayout.a(this);
    }
}