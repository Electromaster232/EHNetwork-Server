package net.minecraft.server;

import java.io.IOException;
import java.util.UUID;

public class PacketPlayOutSpawnEntity implements Packet<PacketListenerPlayOut> {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public UUID uuid;

    public PacketPlayOutSpawnEntity() {}

    public PacketPlayOutSpawnEntity(Entity entity, int i) {
        this(entity, i, 0);
    }

    public PacketPlayOutSpawnEntity(Entity entity, int i, int j) {
        this.a = entity.getId();
        this.b = MathHelper.floor(entity.locX * 32.0D);
        this.c = MathHelper.floor(entity.locY * 32.0D);
        this.d = MathHelper.floor(entity.locZ * 32.0D);
        this.h = MathHelper.d((entity.isFakeHead() ? entity.fakePitch : entity.pitch) * 256.0F / 360.0F);
        this.i = MathHelper.d((entity.isFakeHead() ? entity.fakeYaw : entity.yaw) * 256.0F / 360.0F);
        this.j = i;
        this.k = j;
        this.uuid = entity.getUniqueID();

        if (j > 0) {
            double d0 = entity.motX;
            double d1 = entity.motY;
            double d2 = entity.motZ;
            double d3 = 3.9D;

            if (d0 < -d3) {
                d0 = -d3;
            }

            if (d1 < -d3) {
                d1 = -d3;
            }

            if (d2 < -d3) {
                d2 = -d3;
            }

            if (d0 > d3) {
                d0 = d3;
            }

            if (d1 > d3) {
                d1 = d3;
            }

            if (d2 > d3) {
                d2 = d3;
            }

            this.e = (int) (d0 * 8000.0D);
            this.f = (int) (d1 * 8000.0D);
            this.g = (int) (d2 * 8000.0D);
        }
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e();
        this.j = packetdataserializer.readByte();
        this.b = packetdataserializer.readInt();
        this.c = packetdataserializer.readInt();
        this.d = packetdataserializer.readInt();
        this.h = packetdataserializer.readByte();
        this.i = packetdataserializer.readByte();
        this.k = packetdataserializer.readInt();
        if (this.k > 0) {
            this.e = packetdataserializer.readShort();
            this.f = packetdataserializer.readShort();
            this.g = packetdataserializer.readShort();
        }

    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.b(this.a);
        if (packetdataserializer.version != 47)
            packetdataserializer.a(this.uuid);
        packetdataserializer.writeByte(this.j);
        if (packetdataserializer.version != 47){
            packetdataserializer.writeDouble(this.b/32D);
            packetdataserializer.writeDouble(this.c/32D);
            packetdataserializer.writeDouble(this.d/32D);
        }else {
            packetdataserializer.writeInt(this.b);
            packetdataserializer.writeInt(this.c);
            packetdataserializer.writeInt(this.d);
        }
        packetdataserializer.writeByte(this.h);
        packetdataserializer.writeByte(this.i);
        packetdataserializer.writeInt(this.k);
        if (packetdataserializer.version != 47 || this.k > 0) {
            packetdataserializer.writeShort(this.e);
            packetdataserializer.writeShort(this.f);
            packetdataserializer.writeShort(this.g);
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(int i) {
        this.b = i;
    }

    public void b(int i) {
        this.c = i;
    }

    public void c(int i) {
        this.d = i;
    }

    public void d(int i) {
        this.e = i;
    }

    public void e(int i) {
        this.f = i;
    }

    public void f(int i) {
        this.g = i;
    }

}