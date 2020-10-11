package net.minecraft.server;

import java.io.IOException;
import java.util.Collection;

public class PacketPlayOutMap
  implements Packet<PacketListenerPlayOut>
{
  public int a;
  public byte b;
  public MapIcon[] c;
  public int d;
  public int e;
  public int f;
  public int g;
  public byte[] h;

  public PacketPlayOutMap()
  {
  }

  public PacketPlayOutMap(int paramInt1, byte paramByte, Collection<MapIcon> paramCollection, byte[] paramArrayOfByte, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    this.a = paramInt1;
    this.b = paramByte;
    this.c = ((MapIcon[])paramCollection.toArray(new MapIcon[paramCollection.size()]));
    this.d = paramInt2;
    this.e = paramInt3;
    this.f = paramInt4;
    this.g = paramInt5;

    this.h = new byte[paramInt4 * paramInt5];
    for (int i = 0; i < paramInt4; i++)
      for (int j = 0; j < paramInt5; j++)
        this.h[(i + j * paramInt4)] = paramArrayOfByte[(paramInt2 + i + (paramInt3 + j) * 128)];
  }

  public void a(PacketDataSerializer paramPacketDataSerializer)
    throws IOException
  {
    this.a = paramPacketDataSerializer.e();
    this.b = paramPacketDataSerializer.readByte();
    this.c = new MapIcon[paramPacketDataSerializer.e()];
    for (int i = 0; i < this.c.length; i++) {
      int j = (short)paramPacketDataSerializer.readByte();
      this.c[i] = new MapIcon((byte)(j >> 4 & 0xF), paramPacketDataSerializer.readByte(), paramPacketDataSerializer.readByte(), (byte)(j & 0xF));
    }
    this.f = paramPacketDataSerializer.readUnsignedByte();
    if (this.f > 0) {
      this.g = paramPacketDataSerializer.readUnsignedByte();
      this.d = paramPacketDataSerializer.readUnsignedByte();
      this.e = paramPacketDataSerializer.readUnsignedByte();
      this.h = paramPacketDataSerializer.a();
    }
  }

  public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    paramPacketDataSerializer.b(this.a);
    paramPacketDataSerializer.writeByte(this.b);
    paramPacketDataSerializer.b(this.c.length);
    if (paramPacketDataSerializer.version != 47)
    {
        paramPacketDataSerializer.writeBoolean(this.c.length > 0);
    }
    for (MapIcon localMapIcon : this.c) {
      paramPacketDataSerializer.writeByte((localMapIcon.getType() & 0xF) << 4 | localMapIcon.getRotation() & 0xF);
      paramPacketDataSerializer.writeByte(localMapIcon.getX());
      paramPacketDataSerializer.writeByte(localMapIcon.getY());
    }
    paramPacketDataSerializer.writeByte(this.f);
    if (this.f > 0) {
      paramPacketDataSerializer.writeByte(this.g);
      paramPacketDataSerializer.writeByte(this.d);
      paramPacketDataSerializer.writeByte(this.e);
      paramPacketDataSerializer.a(this.h);
    }
  }

  public void a(PacketListenerPlayOut paramPacketListenerPlayOut)
  {
    paramPacketListenerPlayOut.a(this);
  }
}