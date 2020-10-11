package net.minecraft.server;

import java.io.IOException;

// Referenced classes of package net.minecraft.server.v1_8_R3:
//            Packet, WorldSettings, PacketListenerPlayOut, PacketDataSerializer,
//            EnumDifficulty, WorldType, PacketListener

public class PacketPlayOutRespawn implements Packet
{

    public PacketPlayOutRespawn() {}

    public PacketPlayOutRespawn(int i, EnumDifficulty enumdifficulty, WorldType worldtype, WorldSettings.EnumGamemode enumgamemode)
    {
        a = i;
        b = enumdifficulty;
        c = enumgamemode;
        d = worldtype;
    }

    public void a(PacketListenerPlayOut packetlistenerplayout)
    {
        packetlistenerplayout.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer)
        throws IOException
    {
        a = packetdataserializer.readInt();
        b = EnumDifficulty.getById(packetdataserializer.readUnsignedByte());
        c = WorldSettings.EnumGamemode.getById(packetdataserializer.readUnsignedByte());
        d = WorldType.getType(packetdataserializer.c(16));
        if(d == null)
            d = WorldType.NORMAL;
    }

    public void b(PacketDataSerializer packetdataserializer)
        throws IOException
    {
        packetdataserializer.writeInt(a);
        packetdataserializer.writeByte(b.a());
        packetdataserializer.writeByte(c.getId());
        packetdataserializer.a(d.name());
    }

    public void a(PacketListener packetlistener)
    {
        a((PacketListenerPlayOut)packetlistener);
    }

    public int a;
    public EnumDifficulty b;
    public WorldSettings.EnumGamemode c;
    public WorldType d;
}