package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutMultiBlockChange
  implements Packet<PacketListenerPlayOut>
{
  public ChunkCoordIntPair a;
  public MultiBlockChangeInfo[] b;

  public PacketPlayOutMultiBlockChange()
  {
  }

  public PacketPlayOutMultiBlockChange(int paramInt, short[] paramArrayOfShort, Chunk paramChunk)
  {
    this.a = new ChunkCoordIntPair(paramChunk.locX, paramChunk.locZ);

    this.b = new MultiBlockChangeInfo[paramInt];
    for (int i = 0; i < this.b.length; i++)
      this.b[i] = new MultiBlockChangeInfo(paramArrayOfShort[i], paramChunk);
  }

  public void a(PacketDataSerializer paramPacketDataSerializer)
    throws IOException
  {
    this.a = new ChunkCoordIntPair(paramPacketDataSerializer.readInt(), paramPacketDataSerializer.readInt());
    this.b = new MultiBlockChangeInfo[paramPacketDataSerializer.e()];

    for (int i = 0; i < this.b.length; i++)
      this.b[i] = new MultiBlockChangeInfo(paramPacketDataSerializer.readShort(), (IBlockData)Block.d.a(paramPacketDataSerializer.e()));
  }

  public void b(PacketDataSerializer paramPacketDataSerializer)
    throws IOException
  {
    paramPacketDataSerializer.writeInt(this.a.x);
    paramPacketDataSerializer.writeInt(this.a.z);
    paramPacketDataSerializer.b(this.b.length);
    for (MultiBlockChangeInfo localMultiBlockChangeInfo : this.b) {
      paramPacketDataSerializer.writeShort(localMultiBlockChangeInfo.b());
      paramPacketDataSerializer.b(Block.d.b(localMultiBlockChangeInfo.c()));
    }
  }

  public void a(PacketListenerPlayOut paramPacketListenerPlayOut)
  {
    paramPacketListenerPlayOut.a(this);
  }

  public class MultiBlockChangeInfo
  {
    public final short b;
    public final IBlockData c;

    public MultiBlockChangeInfo(short paramIBlockData, IBlockData arg3)
    {
      this.b = paramIBlockData;
      this.c = arg3;
    }

    public MultiBlockChangeInfo(short paramChunk, Chunk arg3) {
      this.b = paramChunk;
      this.c = arg3.getBlockData(a());
    }

    public BlockPosition a() {
            return new BlockPosition(b >> 12 & 0xf, b & 0xff, b >> 8 & 0xf);
    }

    public short b() {
      return this.b;
    }

    public IBlockData c() {
      return this.c;
    }
  }
}