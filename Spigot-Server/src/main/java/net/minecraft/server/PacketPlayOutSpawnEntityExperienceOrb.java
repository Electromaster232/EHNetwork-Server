package net.minecraft.server;

import java.io.IOException;

// Referenced classes of package net.minecraft.server.v1_8_R3:
//            Packet, EntityExperienceOrb, MathHelper, PacketDataSerializer,
//            PacketListenerPlayOut, PacketListener

public class PacketPlayOutSpawnEntityExperienceOrb
    implements Packet
{

    public PacketPlayOutSpawnEntityExperienceOrb()
    {
    }

    public PacketPlayOutSpawnEntityExperienceOrb(EntityExperienceOrb entityexperienceorb)
    {
        a = entityexperienceorb.getId();
        b = MathHelper.floor(entityexperienceorb.locX * 32D);
        c = MathHelper.floor(entityexperienceorb.locY * 32D);
        d = MathHelper.floor(entityexperienceorb.locZ * 32D);
        e = entityexperienceorb.j();
    }

    public void a(PacketDataSerializer packetdataserializer)
        throws IOException
    {
        a = packetdataserializer.e();
        b = packetdataserializer.readInt();
        c = packetdataserializer.readInt();
        d = packetdataserializer.readInt();
        e = packetdataserializer.readShort();
    }

    public void b(PacketDataSerializer packetdataserializer)
        throws IOException
    {
        packetdataserializer.b(a);
        if (packetdataserializer.version != 47){
            packetdataserializer.writeDouble(this.b/32D);
            packetdataserializer.writeDouble(this.c/32D);
            packetdataserializer.writeDouble(this.d/32D);
        }else {
            packetdataserializer.writeInt(this.b);
            packetdataserializer.writeInt(this.c);
            packetdataserializer.writeInt(this.d);
        }
        packetdataserializer.writeShort(e);
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