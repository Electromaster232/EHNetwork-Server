package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInEntityAction implements Packet
{
    public enum EnumPlayerAction
    {

        START_SNEAKING, STOP_SNEAKING, STOP_SLEEPING, START_SPRINTING, STOP_SPRINTING, RIDING_JUMP, OPEN_INVENTORY;
    }

    public int a;
    public EnumPlayerAction animation;
    public int c;

    public PacketPlayInEntityAction() {}

    public void a(PacketDataSerializer packetdataserializer) throws IOException
    {
        a = packetdataserializer.e();
        animation = (EnumPlayerAction)packetdataserializer.a(EnumPlayerAction.class);
        c = packetdataserializer.e();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException
    {
        packetdataserializer.b(a);
        packetdataserializer.a(animation);
        packetdataserializer.b(c);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin)
    {
        packetlistenerplayin.a(this);
    }

    public EnumPlayerAction b()
    {
        return animation;
    }

    public int c()
    {
        return c;
    }

    public void a(PacketListener packetlistener)
    {
        a((PacketListenerPlayIn)packetlistener);
    }
}