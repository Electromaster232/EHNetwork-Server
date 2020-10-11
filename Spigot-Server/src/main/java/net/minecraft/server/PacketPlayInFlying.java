package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInFlying
  implements Packet<PacketListenerPlayIn>
{
  public double x;
  public double y;
  public double z;
  public float yaw;
  public float pitch;
  public boolean f;
  public boolean hasPos;
  public boolean hasLook;

  public void a(PacketListenerPlayIn paramPacketListenerPlayIn)
  {
    paramPacketListenerPlayIn.a(this);
  }

  public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    this.f = (paramPacketDataSerializer.readUnsignedByte() != 0);
  }

  public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    paramPacketDataSerializer.writeByte(this.f ? 1 : 0);
  }

  public double a() {
    return this.x;
  }

  public double b() {
    return this.y;
  }

  public double c() {
    return this.z;
  }

  public float d() {
    return this.yaw;
  }

  public float e() {
    return this.pitch;
  }

  public boolean f() {
    return this.f;
  }

  public boolean g() {
    return this.hasPos;
  }

  public boolean h() {
    return this.hasLook;
  }

  public void a(boolean paramBoolean) {
    this.hasPos = paramBoolean;
  }

  public static class PacketPlayInLook extends PacketPlayInFlying
  {
    public PacketPlayInLook()
    {
      this.hasLook = true;
    }

    public void a(PacketDataSerializer paramPacketDataSerializer)
      throws IOException
    {
      this.yaw = paramPacketDataSerializer.readFloat();
      this.pitch = paramPacketDataSerializer.readFloat();
      super.a(paramPacketDataSerializer);
    }

    public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException
    {
      paramPacketDataSerializer.writeFloat(this.yaw);
      paramPacketDataSerializer.writeFloat(this.pitch);
      super.b(paramPacketDataSerializer);
    }
  }

  public static class PacketPlayInPosition extends PacketPlayInFlying
  {
    public PacketPlayInPosition()
    {
      this.hasPos = true;
    }

    public void a(PacketDataSerializer paramPacketDataSerializer)
      throws IOException
    {
      this.x = paramPacketDataSerializer.readDouble();
      this.y = paramPacketDataSerializer.readDouble();
      this.z = paramPacketDataSerializer.readDouble();
      super.a(paramPacketDataSerializer);
    }

    public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException
    {
      paramPacketDataSerializer.writeDouble(this.x);
      paramPacketDataSerializer.writeDouble(this.y);
      paramPacketDataSerializer.writeDouble(this.z);
      super.b(paramPacketDataSerializer);
    }
  }

  public static class PacketPlayInPositionLook extends PacketPlayInFlying
  {
    public PacketPlayInPositionLook()
    {
      this.hasPos = true;
      this.hasLook = true;
    }

    public void a(PacketDataSerializer paramPacketDataSerializer)
      throws IOException
    {
      this.x = paramPacketDataSerializer.readDouble();
      this.y = paramPacketDataSerializer.readDouble();
      this.z = paramPacketDataSerializer.readDouble();
      this.yaw = paramPacketDataSerializer.readFloat();
      this.pitch = paramPacketDataSerializer.readFloat();
      super.a(paramPacketDataSerializer);
    }

    public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException
    {
      paramPacketDataSerializer.writeDouble(this.x);
      paramPacketDataSerializer.writeDouble(this.y);
      paramPacketDataSerializer.writeDouble(this.z);
      paramPacketDataSerializer.writeFloat(this.yaw);
      paramPacketDataSerializer.writeFloat(this.pitch);
      super.b(paramPacketDataSerializer);
    }
  }
}