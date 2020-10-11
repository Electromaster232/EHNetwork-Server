package net.minecraft.server;

import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

public class PacketPlayInTabComplete implements Packet<PacketListenerPlayIn>
{
  private String a;
  private BlockPosition b;

  public PacketPlayInTabComplete() {}

  public PacketPlayInTabComplete(String paramString)
  {
    this(paramString, null);
  }

  public PacketPlayInTabComplete(String paramString, BlockPosition paramBlockPosition) {
    this.a = paramString;
    this.b = paramBlockPosition;
  }

  public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    this.a = paramPacketDataSerializer.c(32767);
    if (paramPacketDataSerializer.version > 47)
        paramPacketDataSerializer.readBoolean();
    boolean bool = paramPacketDataSerializer.readBoolean();
    if (bool)
      this.b = paramPacketDataSerializer.c();
  }

  public void b(PacketDataSerializer paramPacketDataSerializer)
    throws IOException
  {
    paramPacketDataSerializer.a(StringUtils.substring(this.a, 0, 32767));
    boolean bool = this.b != null;
    paramPacketDataSerializer.writeBoolean(bool);
    if (bool)
      paramPacketDataSerializer.a(this.b);
  }

  public void a(PacketListenerPlayIn paramPacketListenerPlayIn)
  {
    paramPacketListenerPlayIn.a(this);
  }

  public String a() {
    return this.a;
  }

  public BlockPosition b() {
    return this.b;
  }
}