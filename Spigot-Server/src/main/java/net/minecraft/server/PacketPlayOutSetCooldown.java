package net.minecraft.server;

public class PacketPlayOutSetCooldown
    implements Packet
{
    public int itemId;
    public int cooldownTicks;

    public PacketPlayOutSetCooldown()
    {
    }

    public PacketPlayOutSetCooldown(int itemId, int cooldownTicks)
    {
        this.itemId = itemId;
        this.cooldownTicks = cooldownTicks;
    }

    public PacketPlayOutSetCooldown(org.bukkit.Material item, int cooldownTicks)
    {
        this.itemId = item.getId();
        this.cooldownTicks = cooldownTicks;
    }

    public void a(PacketDataSerializer packetdataserializer)
    {
    }

    public void b(PacketDataSerializer packetdataserializer)
    {
        packetdataserializer.a(itemId);
        packetdataserializer.a(cooldownTicks);
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