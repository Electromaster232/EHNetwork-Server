package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutScoreboardDisplayObjective implements Packet<PacketListenerPlayOut> {
  public int a;
  public String b;

  public PacketPlayOutScoreboardDisplayObjective() {}

  public PacketPlayOutScoreboardDisplayObjective(int paramInt, ScoreboardObjective paramScoreboardObjective)
  {
    this.a = paramInt;

    if (paramScoreboardObjective == null)
      this.b = "";
    else
      this.b = paramScoreboardObjective.getName();
  }

  public void a(PacketDataSerializer paramPacketDataSerializer)
    throws IOException
  {
    this.a = paramPacketDataSerializer.readByte();
    this.b = paramPacketDataSerializer.c(16);
  }

  public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    paramPacketDataSerializer.writeByte(this.a);
    paramPacketDataSerializer.a(this.b);
  }

  public void a(PacketListenerPlayOut paramPacketListenerPlayOut)
  {
    paramPacketListenerPlayOut.a(this);
  }
}