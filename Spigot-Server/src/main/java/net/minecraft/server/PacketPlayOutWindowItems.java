package net.minecraft.server;

import java.io.IOException;
import java.util.List;

public class PacketPlayOutWindowItems implements Packet<PacketListenerPlayOut> {
  public int a;
  public ItemStack[] b;

  public PacketPlayOutWindowItems() {}

  public PacketPlayOutWindowItems(int paramInt, List<ItemStack> paramList)
  {
    this.a = paramInt;
    this.b = new ItemStack[paramList.size()];
    for (int i = 0; i < this.b.length; i++) {
      ItemStack localItemStack = (ItemStack)paramList.get(i);
      this.b[i] = (localItemStack == null ? null : localItemStack.cloneItemStack());
    }
  }

  public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    this.a = paramPacketDataSerializer.readUnsignedByte();
    int i = paramPacketDataSerializer.readShort();
    this.b = new ItemStack[i];
    for (int j = 0; j < i; j++)
      this.b[j] = paramPacketDataSerializer.i();
  }

  public void b(PacketDataSerializer paramPacketDataSerializer)
    throws IOException
  {
    paramPacketDataSerializer.writeByte(this.a);
    paramPacketDataSerializer.writeShort(this.b.length);
    for (ItemStack localItemStack : this.b)
      paramPacketDataSerializer.a(localItemStack);
  }

  public void a(PacketListenerPlayOut paramPacketListenerPlayOut)
  {
    paramPacketListenerPlayOut.a(this);
  }
}