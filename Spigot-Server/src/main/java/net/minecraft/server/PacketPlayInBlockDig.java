package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInBlockDig implements Packet<PacketListenerPlayIn>
{
  public BlockPosition a;
  public EnumDirection b;
  public EnumPlayerDigType c;

  public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    this.c = ((EnumPlayerDigType)paramPacketDataSerializer.a(EnumPlayerDigType.class));
    this.a = paramPacketDataSerializer.c();
    this.b = EnumDirection.fromType1(paramPacketDataSerializer.readUnsignedByte());
  }

  public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    paramPacketDataSerializer.a(this.c);
    paramPacketDataSerializer.a(this.a);
    paramPacketDataSerializer.writeByte(this.b.a());
  }

  public void a(PacketListenerPlayIn paramPacketListenerPlayIn)
  {
    paramPacketListenerPlayIn.a(this);
  }

  public BlockPosition a() {
    return this.a;
  }

  public EnumDirection b() {
    return this.b;
  }

  public EnumPlayerDigType c() {
    return this.c;
  }

  public static enum EnumPlayerDigType
  {
      START_DESTROY_BLOCK,
      ABORT_DESTROY_BLOCK,
      STOP_DESTROY_BLOCK, DROP_ALL_ITEMS,
      DROP_ITEM,
      RELEASE_USE_ITEM,
      SWAP_HELD_ITEMS;
  }
}