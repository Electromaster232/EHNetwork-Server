package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutBlockAction
  implements Packet<PacketListenerPlayOut>
{
  public BlockPosition a;
  public int b;
  public int c;
  public Block d;

  public PacketPlayOutBlockAction()
  {
  }

  public PacketPlayOutBlockAction(BlockPosition paramBlockPosition, Block paramBlock, int paramInt1, int paramInt2)
  {
    this.a = paramBlockPosition;
    this.b = paramInt1;
    this.c = paramInt2;
    this.d = paramBlock;
  }

  public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    this.a = paramPacketDataSerializer.c();
    this.b = paramPacketDataSerializer.readUnsignedByte();
    this.c = paramPacketDataSerializer.readUnsignedByte();
    this.d = Block.getById(paramPacketDataSerializer.e() & 0xFFF);
  }

  public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    paramPacketDataSerializer.a(this.a);
    paramPacketDataSerializer.writeByte(this.b);
    paramPacketDataSerializer.writeByte(this.c);
    paramPacketDataSerializer.b(Block.getId(this.d) & 0xFFF);
  }

  public void a(PacketListenerPlayOut paramPacketListenerPlayOut)
  {
    paramPacketListenerPlayOut.a(this);
  }
}