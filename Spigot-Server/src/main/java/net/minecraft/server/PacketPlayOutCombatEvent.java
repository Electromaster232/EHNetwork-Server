package net.minecraft.server;

import java.io.IOException;
import org.bukkit.craftbukkit.util.CraftChatMessage;

public class PacketPlayOutCombatEvent
  implements Packet<PacketListenerPlayOut>
{
  public EnumCombatEventType a;
  public int b;
  public int c;
  public int d;
  public String e;

  public PacketPlayOutCombatEvent()
  {
  }

  public PacketPlayOutCombatEvent(CombatTracker paramCombatTracker, EnumCombatEventType paramEnumCombatEventType)
  {
    this.a = paramEnumCombatEventType;

    EntityLiving localEntityLiving = paramCombatTracker.c();

    switch (paramEnumCombatEventType.ordinal()) {
    case 1:
      this.d = paramCombatTracker.f();
      this.c = (localEntityLiving == null ? -1 : localEntityLiving.getId());
      break;
    case 2:
      this.b = paramCombatTracker.h().getId();
      this.c = (localEntityLiving == null ? -1 : localEntityLiving.getId());
      this.e = paramCombatTracker.b().c();
    }
  }

  public void a(PacketDataSerializer paramPacketDataSerializer)
    throws IOException
  {
    this.a = ((EnumCombatEventType)paramPacketDataSerializer.a(EnumCombatEventType.class));

    if (this.a == EnumCombatEventType.END_COMBAT) {
      this.d = paramPacketDataSerializer.e();
      this.c = paramPacketDataSerializer.readInt();
    } else if (this.a == EnumCombatEventType.ENTITY_DIED) {
      this.b = paramPacketDataSerializer.e();
      this.c = paramPacketDataSerializer.readInt();
      this.e = paramPacketDataSerializer.c(32767);
    }
  }

  public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    paramPacketDataSerializer.a(this.a);

    if (this.a == EnumCombatEventType.END_COMBAT) {
      paramPacketDataSerializer.b(this.d);
      paramPacketDataSerializer.writeInt(this.c);
    } else if (this.a == EnumCombatEventType.ENTITY_DIED) {
      paramPacketDataSerializer.b(this.b);
      paramPacketDataSerializer.writeInt(this.c);
      if (paramPacketDataSerializer.version == 47)
      paramPacketDataSerializer.a(this.e);
      else
      {
          paramPacketDataSerializer.a(CraftChatMessage.fromString(this.e)[0]);
      }
    }
  }

  public void a(PacketListenerPlayOut paramPacketListenerPlayOut)
  {
    paramPacketListenerPlayOut.a(this);
  }

  public static enum EnumCombatEventType
  {
      ENTER_COMBAT, END_COMBAT, ENTITY_DIED;
  }
}