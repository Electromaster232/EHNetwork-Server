package net.minecraft.server;

import java.io.IOException;
import java.util.*;

public class PacketPlayOutPosition
    implements Packet<PacketListenerPlayOut>
{
    public enum EnumPlayerTeleportFlags
    {

        X(0), Y(1), Z(2), Y_ROT(3), X_ROT(4);

        private int f;

        private EnumPlayerTeleportFlags(int j)
        {
            f = j;
        }

        private int a()
        {
            return 1 << f;
        }

        private boolean b(int i)
        {
            return (i & a()) == a();
        }

        public static Set a(int i)
        {
            EnumSet enumset = EnumSet.noneOf(PacketPlayOutPosition.EnumPlayerTeleportFlags.class);
            int j = values().length;
            for(int k = 0; k < j; k++)
            {
                EnumPlayerTeleportFlags enumplayerteleportflags = values()[k];
                if(enumplayerteleportflags.b(i))
                    enumset.add(enumplayerteleportflags);
            }

            return enumset;
        }

        public static int a(Set set)
        {
            int i = 0;
            for(Iterator iterator = set.iterator(); iterator.hasNext();)
            {
                EnumPlayerTeleportFlags enumplayerteleportflags = (EnumPlayerTeleportFlags)iterator.next();
                i |= enumplayerteleportflags.a();
            }

            return i;
        }
    }

    public double a;
    public double b;
    public double c;
    public float d;
    public float e;
    public Set f;

    public PacketPlayOutPosition()
    {
    }

    public PacketPlayOutPosition(double d1, double d2, double d3, float f1,
            float f2, Set set)
    {
        a = d1;
        b = d2;
        c = d3;
        d = f1;
        e = f2;
        f = set;
    }

    public void a(PacketDataSerializer packetdataserializer)
        throws IOException
    {
        a = packetdataserializer.readDouble();
        b = packetdataserializer.readDouble();
        c = packetdataserializer.readDouble();
        d = packetdataserializer.readFloat();
        e = packetdataserializer.readFloat();
        f = EnumPlayerTeleportFlags.a(packetdataserializer.readUnsignedByte());
    }

    public void b(PacketDataSerializer packetdataserializer)
        throws IOException
    {
        packetdataserializer.writeDouble(a);
        packetdataserializer.writeDouble(b);
        packetdataserializer.writeDouble(c);
        packetdataserializer.writeFloat(d);
        packetdataserializer.writeFloat(e);
        packetdataserializer.writeByte(EnumPlayerTeleportFlags.a(f));
        if (packetdataserializer.version != 47)
        packetdataserializer.b(0);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout)
    {
        packetlistenerplayout.a(this);
    }
}