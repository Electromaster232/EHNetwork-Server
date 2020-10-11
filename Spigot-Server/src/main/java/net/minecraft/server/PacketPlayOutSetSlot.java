package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutSetSlot implements Packet<PacketListenerPlayOut> {
  public int a;
  public int b;
  public ItemStack c;

  public PacketPlayOutSetSlot() {}

  public PacketPlayOutSetSlot(int paramInt1, int paramInt2, ItemStack paramItemStack)
  {
    this.a = paramInt1;
    this.b = paramInt2;
    this.c = (paramItemStack == null ? null : paramItemStack.cloneItemStack());
  }

  public void a(PacketListenerPlayOut paramPacketListenerPlayOut)
  {
    paramPacketListenerPlayOut.a(this);
  }

  public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    this.a = paramPacketDataSerializer.readByte();
    this.b = paramPacketDataSerializer.readShort();
    this.c = paramPacketDataSerializer.i();
  }

  public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    paramPacketDataSerializer.writeByte(this.a);
    paramPacketDataSerializer.writeShort(this.b);
    paramPacketDataSerializer.a(this.c);
  }
}