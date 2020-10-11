package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutScoreboardObjective implements Packet<PacketListenerPlayOut> {
  public String a;
  public String b;
  public IScoreboardCriteria.EnumScoreboardHealthDisplay c;
  public int d;

  public PacketPlayOutScoreboardObjective() {}

  public PacketPlayOutScoreboardObjective(ScoreboardObjective paramScoreboardObjective, int paramInt)
  {
    this.a = paramScoreboardObjective.getName();
    this.b = paramScoreboardObjective.getDisplayName();
    this.c = paramScoreboardObjective.getCriteria().c();
    this.d = paramInt;
  }

  public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    this.a = paramPacketDataSerializer.c(16);
    this.d = paramPacketDataSerializer.readByte();

    if ((this.d == 0) || (this.d == 2)) {
      this.b = paramPacketDataSerializer.c(32);
      this.c = IScoreboardCriteria.EnumScoreboardHealthDisplay.a(paramPacketDataSerializer.c(16));
    }
  }

  public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    paramPacketDataSerializer.a(this.a);
    paramPacketDataSerializer.writeByte(this.d);

    if ((this.d == 0) || (this.d == 2)) {
      paramPacketDataSerializer.a(this.b);
      paramPacketDataSerializer.a(this.c.a());
    }
  }

  public void a(PacketListenerPlayOut paramPacketListenerPlayOut)
  {
    paramPacketListenerPlayOut.a(this);
  }
}