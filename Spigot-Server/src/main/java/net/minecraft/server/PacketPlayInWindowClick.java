package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInWindowClick
  implements Packet<PacketListenerPlayIn>
{
  private int a;
  private int slot;
  private int button;
  private short d;
  private ItemStack item;
  private int shift;

  public void a(PacketListenerPlayIn paramPacketListenerPlayIn)
  {
    paramPacketListenerPlayIn.a(this);
  }

  public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    this.a = paramPacketDataSerializer.readByte();
    this.slot = paramPacketDataSerializer.readShort();
    this.button = paramPacketDataSerializer.readByte();
    this.d = paramPacketDataSerializer.readShort();
    this.shift = paramPacketDataSerializer.e();

    this.item = paramPacketDataSerializer.i();
  }

  public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    paramPacketDataSerializer.writeByte(this.a);
    paramPacketDataSerializer.writeShort(this.slot);
    paramPacketDataSerializer.writeByte(this.button);
    paramPacketDataSerializer.writeShort(this.d);
    paramPacketDataSerializer.writeByte(this.shift);

    paramPacketDataSerializer.a(this.item);
  }

  public int a() {
    return this.a;
  }

  public int b() {
    return this.slot;
  }

  public int c() {
    return this.button;
  }

  public short d() {
    return this.d;
  }

  public ItemStack e() {
    return this.item;
  }

  public int f() {
    return this.shift;
  }
}