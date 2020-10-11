package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInAbilities implements Packet<PacketListenerPlayIn>
{
  private boolean a;
  private boolean b;
  private boolean c;
  private boolean d;
  private float e;
  private float f;

  public PacketPlayInAbilities() { }

  public PacketPlayInAbilities(PlayerAbilities paramPlayerAbilities)
  {
    a(paramPlayerAbilities.isInvulnerable);
    b(paramPlayerAbilities.isFlying);
    c(paramPlayerAbilities.canFly);
    d(paramPlayerAbilities.canInstantlyBuild);
    a(paramPlayerAbilities.a());
    b(paramPlayerAbilities.b());
  }

  public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    int i = paramPacketDataSerializer.readByte();

    a((i & 0x1) > 0);
    b((i & 0x2) > 0);
    c((i & 0x4) > 0);
    d((i & 0x8) > 0);
    a(paramPacketDataSerializer.readFloat());
    b(paramPacketDataSerializer.readFloat());
  }

  public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    int i = 0;

    if (a()) {
      i = (byte)(i | 0x1);
    }
    if (isFlying()) {
      i = (byte)(i | 0x2);
    }
    if (c()) {
      i = (byte)(i | 0x4);
    }
    if (d()) {
      i = (byte)(i | 0x8);
    }

    paramPacketDataSerializer.writeByte(i);
    paramPacketDataSerializer.writeFloat(this.e);
    paramPacketDataSerializer.writeFloat(this.f);
  }

  public void a(PacketListenerPlayIn paramPacketListenerPlayIn)
  {
    paramPacketListenerPlayIn.a(this);
  }

  public boolean a() {
    return this.a;
  }

  public void a(boolean paramBoolean) {
    this.a = paramBoolean;
  }

  public boolean isFlying() {
    return this.b;
  }

  public void b(boolean paramBoolean) {
    this.b = paramBoolean;
  }

  public boolean c() {
    return this.c;
  }

  public void c(boolean paramBoolean) {
    this.c = paramBoolean;
  }

  public boolean d() {
    return this.d;
  }

  public void d(boolean paramBoolean) {
    this.d = paramBoolean;
  }

  public void a(float paramFloat)
  {
    this.e = paramFloat;
  }

  public void b(float paramFloat)
  {
    this.f = paramFloat;
  }
}