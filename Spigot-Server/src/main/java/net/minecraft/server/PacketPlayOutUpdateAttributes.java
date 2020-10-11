package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.*;

public class PacketPlayOutUpdateAttributes implements Packet {
    public int a;
    public final List<AttributeSnapshot> b = new ArrayList();

    public PacketPlayOutUpdateAttributes() {}

    public PacketPlayOutUpdateAttributes(int i, Collection<AttributeInstance> collection)
    {
        a = i;

        for (AttributeInstance instance : collection)
            this.b.add(new AttributeSnapshot(instance.getAttribute().getName(), instance.b(), instance.c()));
    }

    public void a(PacketDataSerializer packetdataserializer)
    {
        a = packetdataserializer.e();
        int i = packetdataserializer.readInt();
        for(int j = 0; j < i; j++)
        {
            String s = packetdataserializer.c(64);
            double d = packetdataserializer.readDouble();
            ArrayList arraylist = Lists.newArrayList();
            int k = packetdataserializer.e();
            for(int l = 0; l < k; l++)
            {
                java.util.UUID uuid = packetdataserializer.g();
                arraylist.add(new AttributeModifier(uuid, "Unknown synced attribute modifier", packetdataserializer.readDouble(), packetdataserializer.readByte()));
            }

            b.add(new AttributeSnapshot(s, d, arraylist));
        }

    }

    public void b(PacketDataSerializer packetdataserializer)
    {
        packetdataserializer.b(a);
        packetdataserializer.writeInt(b.size());

        for (AttributeSnapshot attributesnapshot : b)
        {
            packetdataserializer.a(attributesnapshot.a());
            packetdataserializer.writeDouble(attributesnapshot.b());
            packetdataserializer.b(attributesnapshot.c().size());
            Iterator iterator1 = attributesnapshot.c().iterator();
            while(iterator1.hasNext())
            {
                AttributeModifier attributemodifier = (AttributeModifier)iterator1.next();
                packetdataserializer.a(attributemodifier.a());
                packetdataserializer.writeDouble(attributemodifier.d());
                packetdataserializer.writeByte(attributemodifier.c());
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

    public class AttributeSnapshot
    {
    private final String b;
    private final double c;
    private final Collection d;

    public AttributeSnapshot(String s, double d1, Collection collection)
    {
        b = s;
        c = d1;
        d = collection;
    }

    public String a()
    {
        return b;
    }

    public double b()
    {
        return c;
    }

    public Collection c()
    {
        return d;
    }
  }
}