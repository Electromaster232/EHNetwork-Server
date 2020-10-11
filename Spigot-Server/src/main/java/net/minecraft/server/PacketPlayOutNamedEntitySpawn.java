package net.minecraft.server;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class PacketPlayOutNamedEntitySpawn implements Packet<PacketListenerPlayOut> {

    public int a;
    public UUID b;
    public int c;
    public int d;
    public int e;
    public byte f;
    public byte g;
    public int h;
    public DataWatcher i;
    public List<DataWatcher.WatchableObject> j;

    public PacketPlayOutNamedEntitySpawn() {}

    public PacketPlayOutNamedEntitySpawn(EntityHuman entityhuman) {
        this.a = entityhuman.getId();
        this.b = entityhuman.getProfile().getId();
        this.c = MathHelper.floor(entityhuman.locX * 32.0D);
        this.d = MathHelper.floor(entityhuman.locY * 32.0D);
        this.e = MathHelper.floor(entityhuman.locZ * 32.0D);
        this.f = (byte) ((int) ((entityhuman.isFakeHead() ? entityhuman.fakeYaw : entityhuman.yaw) * 256.0F / 360.0F));
        this.g = (byte) ((int) ((entityhuman.isFakeHead() ? entityhuman.fakePitch : entityhuman.pitch) * 256.0F / 360.0F));
        ItemStack itemstack = entityhuman.inventory.getItemInHand();

        this.h = itemstack == null ? 0 : Item.getId(itemstack.getItem());
        this.i = entityhuman.getDataWatcher();
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e();
        this.b = packetdataserializer.g();
        this.c = packetdataserializer.readInt();
        this.d = packetdataserializer.readInt();
        this.e = packetdataserializer.readInt();
        this.f = packetdataserializer.readByte();
        this.g = packetdataserializer.readByte();
        this.h = packetdataserializer.readShort();
        this.j = DataWatcher.b(packetdataserializer);
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.b(this.a);
        packetdataserializer.a(this.b);
        if (packetdataserializer.version != 47){
            packetdataserializer.writeDouble(this.c/32D);
            packetdataserializer.writeDouble(this.d/32D);
            packetdataserializer.writeDouble(this.e/32D);
        }else {
            packetdataserializer.writeInt(this.c);
            packetdataserializer.writeInt(this.d);
            packetdataserializer.writeInt(this.e);
        }
        packetdataserializer.writeByte(this.f);
        packetdataserializer.writeByte(this.g);
        if (packetdataserializer.version == 47)
            packetdataserializer.writeShort(this.h);
        this.i.a(packetdataserializer);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

}