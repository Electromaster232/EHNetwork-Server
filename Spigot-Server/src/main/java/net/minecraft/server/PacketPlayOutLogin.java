package net.minecraft.server;

import java.io.IOException;

// Referenced classes of package net.minecraft.server.v1_8_R3:
//            Packet, WorldSettings, PacketDataSerializer, EnumDifficulty,
//            WorldType, PacketListenerPlayOut, PacketListener

public class PacketPlayOutLogin
    implements Packet
{

    public PacketPlayOutLogin()
    {
    }

    public PacketPlayOutLogin(int i, WorldSettings.EnumGamemode enumgamemode, boolean flag, int j, EnumDifficulty enumdifficulty, int k, WorldType worldtype,
            boolean flag1)
    {
        a = i;
        d = j;
        e = enumdifficulty;
        c = enumgamemode;
        f = k;
        b = flag;
        g = worldtype;
        h = flag1;
    }

    public void a(PacketDataSerializer packetdataserializer)
        throws IOException
    {
        a = packetdataserializer.readInt();
        int i = packetdataserializer.readUnsignedByte();
        b = (i & 8) == 8;
        i &= -9;
        c = WorldSettings.EnumGamemode.getById(i);
        d = packetdataserializer.readByte();
        e = EnumDifficulty.getById(packetdataserializer.readUnsignedByte());
        f = packetdataserializer.readUnsignedByte();
        g = WorldType.getType(packetdataserializer.c(16));
        if(g == null)
            g = WorldType.NORMAL;
        h = packetdataserializer.readBoolean();
    }

    public void b(PacketDataSerializer packetdataserializer)
        throws IOException
    {
        packetdataserializer.writeInt(a);
        int i = c.getId();
        if(b)
            i |= 8;
        packetdataserializer.writeByte(i);
        packetdataserializer.writeByte(d);
        packetdataserializer.writeByte(e.a());
        packetdataserializer.writeByte(f);
        packetdataserializer.a(g.name());
        packetdataserializer.writeBoolean(h);
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
    public boolean b;
    public WorldSettings.EnumGamemode c;
    public int d;
    public EnumDifficulty e;
    public int f;
    public WorldType g;
    public boolean h;
}