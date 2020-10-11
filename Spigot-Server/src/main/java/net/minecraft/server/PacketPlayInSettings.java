package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInSettings
  implements Packet<PacketListenerPlayIn>
{
  public String a;
  public int b;
  public EntityHuman.EnumChatVisibility c;
  public boolean d;
  public int e;

  public void a(PacketDataSerializer paramPacketDataSerializer)
    throws IOException
  {
    this.a = paramPacketDataSerializer.c(7);
    this.b = paramPacketDataSerializer.readByte();

    if (paramPacketDataSerializer.version == 47)
        this.c = EntityHuman.EnumChatVisibility.a(paramPacketDataSerializer.readByte());
    else
        this.c = EntityHuman.EnumChatVisibility.a(paramPacketDataSerializer.e());

    this.d = paramPacketDataSerializer.readBoolean();

    this.e = paramPacketDataSerializer.readUnsignedByte();

    if (paramPacketDataSerializer.version != 47)
        paramPacketDataSerializer.e();
  }

  public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    paramPacketDataSerializer.a(this.a);
    paramPacketDataSerializer.writeByte(this.b);
    paramPacketDataSerializer.writeByte(this.c.a());
    paramPacketDataSerializer.writeBoolean(this.d);
    paramPacketDataSerializer.writeByte(this.e);
  }

  public void a(PacketListenerPlayIn paramPacketListenerPlayIn)
  {
    paramPacketListenerPlayIn.a(this);
  }

  public String a() {
    return this.a;
  }

  public EntityHuman.EnumChatVisibility c()
  {
    return this.c;
  }

  public boolean d() {
    return this.d;
  }

  public int e() {
    return this.e;
  }
}