package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutScoreboardScore implements Packet<PacketListenerPlayOut> {
  public String a = "";
  public String b = "";
  public int c;
  public EnumScoreboardAction d;

  public PacketPlayOutScoreboardScore() {}

  public PacketPlayOutScoreboardScore(ScoreboardScore paramScoreboardScore)
  {
    this.a = paramScoreboardScore.getPlayerName();
    this.b = paramScoreboardScore.getObjective().getName();
    this.c = paramScoreboardScore.getScore();
    this.d = EnumScoreboardAction.CHANGE;
  }

  public PacketPlayOutScoreboardScore(String paramString) {
    this.a = paramString;
    this.b = "";
    this.c = 0;
    this.d = EnumScoreboardAction.REMOVE;
  }

  public PacketPlayOutScoreboardScore(String paramString, ScoreboardObjective paramScoreboardObjective) {
    this.a = paramString;
    this.b = paramScoreboardObjective.getName();
    this.c = 0;
    this.d = EnumScoreboardAction.REMOVE;
  }

  public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    this.a = paramPacketDataSerializer.c(40);
    this.d = ((EnumScoreboardAction)paramPacketDataSerializer.a(EnumScoreboardAction.class));
    this.b = paramPacketDataSerializer.c(16);

    if (this.d != EnumScoreboardAction.REMOVE)
      this.c = paramPacketDataSerializer.e();
  }

  public void b(PacketDataSerializer paramPacketDataSerializer)
    throws IOException
  {
    paramPacketDataSerializer.a(this.a);
    paramPacketDataSerializer.a(this.d);
    paramPacketDataSerializer.a(this.b);

    if (this.d != EnumScoreboardAction.REMOVE)
      paramPacketDataSerializer.b(this.c);
  }

  public void a(PacketListenerPlayOut paramPacketListenerPlayOut)
  {
    paramPacketListenerPlayOut.a(this);
  }

  public static enum EnumScoreboardAction
  {
      CHANGE, REMOVE;
  }
}