package net.minecraft.server;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collection;

public class PacketPlayOutScoreboardTeam implements Packet<PacketListenerPlayOut> {
  public String a = "";
  public String b = "";
  public String c = "";
  public String d = "";
  public String e = ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS.e;
  public String collision = "never";
  public int f = -1;
  public Collection<String> g = Lists.newArrayList();
  public int h;
  public int i;

  public PacketPlayOutScoreboardTeam() {}

  public PacketPlayOutScoreboardTeam(ScoreboardTeam paramScoreboardTeam, int paramInt)
  {
    this.a = paramScoreboardTeam.getName();
    this.h = paramInt;

    if ((paramInt == 0) || (paramInt == 2)) {
      this.b = paramScoreboardTeam.getDisplayName();
      this.c = paramScoreboardTeam.getPrefix();
      this.d = paramScoreboardTeam.getSuffix();
      this.i = paramScoreboardTeam.packOptionData();
      this.e = paramScoreboardTeam.getNameTagVisibility().e;
      this.f = paramScoreboardTeam.l().b();
    }
    if (paramInt == 0)
      this.g.addAll(paramScoreboardTeam.getPlayerNameSet());
  }

  public PacketPlayOutScoreboardTeam(ScoreboardTeam paramScoreboardTeam, Collection<String> paramCollection, int paramInt)
  {
    if ((paramInt != 3) && (paramInt != 4)) {
      throw new IllegalArgumentException("Method must be join or leave for player constructor");
    }
    if ((paramCollection == null) || (paramCollection.isEmpty())) {
      throw new IllegalArgumentException("Players cannot be null/empty");
    }

    this.h = paramInt;
    this.a = paramScoreboardTeam.getName();
    this.g.addAll(paramCollection);
  }

  public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    this.a = paramPacketDataSerializer.c(16);
    this.h = paramPacketDataSerializer.readByte();

    if ((this.h == 0) || (this.h == 2)) {
      this.b = paramPacketDataSerializer.c(32);
      this.c = paramPacketDataSerializer.c(16);
      this.d = paramPacketDataSerializer.c(16);
      this.i = paramPacketDataSerializer.readByte();
      this.e = paramPacketDataSerializer.c(32);
      this.f = paramPacketDataSerializer.readByte();
    }

    if ((this.h == 0) || (this.h == 3) || (this.h == 4)) {
      int j = paramPacketDataSerializer.e();

      for (int k = 0; k < j; k++)
        this.g.add(paramPacketDataSerializer.c(40));
    }
  }

  public void b(PacketDataSerializer paramPacketDataSerializer)
    throws IOException
  {
    paramPacketDataSerializer.a(this.a);
    paramPacketDataSerializer.writeByte(this.h);

    if ((this.h == 0) || (this.h == 2)) {
      paramPacketDataSerializer.a(this.b);
      paramPacketDataSerializer.a(this.c);
      paramPacketDataSerializer.a(this.d);
      paramPacketDataSerializer.writeByte(this.i);
      paramPacketDataSerializer.a(this.e);
      if (paramPacketDataSerializer.version != 47)
      {
          paramPacketDataSerializer.a(collision);
      }
      paramPacketDataSerializer.writeByte(this.f);
    }

    if ((this.h == 0) || (this.h == 3) || (this.h == 4)) {
      paramPacketDataSerializer.b(this.g.size());

      for (String str : this.g)
        paramPacketDataSerializer.a(str);
    }
  }

  public void a(PacketListenerPlayOut paramPacketListenerPlayOut)
  {
    paramPacketListenerPlayOut.a(this);
  }
}