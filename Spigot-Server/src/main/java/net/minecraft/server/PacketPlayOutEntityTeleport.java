package net.minecraft.server;

public class PacketPlayOutEntityTeleport implements Packet<PacketListenerPlayOut> {

    public int a;
    public int b;
    public int c;
    public int d;
    public byte e;
    public byte f;
    public boolean g;

    public PacketPlayOutEntityTeleport() {}

    public PacketPlayOutEntityTeleport(Entity entity) {
        this.a = entity.getId();
        this.b = MathHelper.floor(entity.locX * 32.0D);
        this.c = MathHelper.floor(entity.locY * 32.0D);
        this.d = MathHelper.floor(entity.locZ * 32.0D);
        this.e = (byte) ((int) ((entity.isFakeHead() ? entity.fakeYaw : entity.yaw) * 256.0F / 360.0F));
        this.f = (byte) ((int) ((entity.isFakeHead() ? entity.fakePitch : entity.pitch) * 256.0F / 360.0F));
        this.g = entity.onGround;
    }

    public PacketPlayOutEntityTeleport(int i, int j, int k, int l, byte b0, byte b1, boolean flag) {
        this.a = i;
        this.b = j;
        this.c = k;
        this.d = l;
        this.e = b0;
        this.f = b1;
        this.g = flag;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.e();
        this.b = packetdataserializer.readInt();
        this.c = packetdataserializer.readInt();
        this.d = packetdataserializer.readInt();
        this.e = packetdataserializer.readByte();
        this.f = packetdataserializer.readByte();
        this.g = packetdataserializer.readBoolean();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.b(this.a);
        if (packetdataserializer.version == 47){
        packetdataserializer.writeInt(this.b);
        packetdataserializer.writeInt(this.c);
        packetdataserializer.writeInt(this.d);
        } else {
                   packetdataserializer.writeDouble(this.b/32D);
        packetdataserializer.writeDouble(this.c/32D);
        packetdataserializer.writeDouble(this.d/32D);
        }
        packetdataserializer.writeByte(this.e);
        packetdataserializer.writeByte(this.f);
        packetdataserializer.writeBoolean(this.g);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}