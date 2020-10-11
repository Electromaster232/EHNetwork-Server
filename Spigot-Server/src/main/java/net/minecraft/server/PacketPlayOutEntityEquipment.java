package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutEntityEquipment implements Packet<PacketListenerPlayOut> {

    public int a;
    public int b;
    public ItemStack c;

    public PacketPlayOutEntityEquipment() {}

    public PacketPlayOutEntityEquipment(int i, int j, ItemStack itemstack) {
        this.a = i;
        this.b = j;
        this.c = itemstack == null ? null : itemstack.cloneItemStack();
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e();
        this.b = packetdataserializer.readShort();
        this.c = packetdataserializer.i();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.b(this.a);
        if (packetdataserializer.version == 47)
            packetdataserializer.writeShort(this.b);
        else {
            packetdataserializer.b(this.b + (this.b == 0 ? 0 : 1));
        }
        packetdataserializer.a(this.c);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

}