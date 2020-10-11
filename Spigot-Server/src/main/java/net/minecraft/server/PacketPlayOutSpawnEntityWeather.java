package net.minecraft.server;

import java.io.IOException;

// Referenced classes of package net.minecraft.server.v1_8_R3:
//            Packet, Entity, MathHelper, EntityLightning,
//            PacketDataSerializer, PacketListenerPlayOut, PacketListener

public class PacketPlayOutSpawnEntityWeather implements Packet
{

    public PacketPlayOutSpawnEntityWeather() {}

    public PacketPlayOutSpawnEntityWeather(Entity entity)
    {
        a = entity.getId();
        b = MathHelper.floor(entity.locX * 32D);
        c = MathHelper.floor(entity.locY * 32D);
        d = MathHelper.floor(entity.locZ * 32D);
        if(entity instanceof EntityLightning)
            e = 1;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException
    {
        a = packetdataserializer.e();
        e = packetdataserializer.readByte();
        b = packetdataserializer.readInt();
        c = packetdataserializer.readInt();
        d = packetdataserializer.readInt();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException
    {
        packetdataserializer.b(a);
        packetdataserializer.writeByte(e);
        if (packetdataserializer.version != 47){
            packetdataserializer.writeDouble(this.b/32D);
            packetdataserializer.writeDouble(this.c/32D);
            packetdataserializer.writeDouble(this.d/32D);
        }else {
            packetdataserializer.writeInt(this.b);
            packetdataserializer.writeInt(this.c);
            packetdataserializer.writeInt(this.d);
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

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
}