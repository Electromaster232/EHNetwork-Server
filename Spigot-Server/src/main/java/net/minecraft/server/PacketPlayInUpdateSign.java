package net.minecraft.server;

import java.io.IOException;
import org.bukkit.craftbukkit.util.CraftChatMessage;

public class PacketPlayInUpdateSign
  implements Packet<PacketListenerPlayIn>
{
  private BlockPosition a;
  private IChatBaseComponent[] b;

  public void a(PacketDataSerializer paramPacketDataSerializer)
    throws IOException
  {
    this.a = paramPacketDataSerializer.c();
    this.b = new IChatBaseComponent[4];
    for (int i = 0; i < 4; i++) {
        if (paramPacketDataSerializer.version == 47) {
            String str = paramPacketDataSerializer.c(384);
            IChatBaseComponent localIChatBaseComponent = IChatBaseComponent.ChatSerializer.a(str);
            this.b[i] = localIChatBaseComponent;
        }
        else
        {
        b[i] = CraftChatMessage.fromString(paramPacketDataSerializer.c(384))[0];
        }
    }
  }

  public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException
  {
    paramPacketDataSerializer.a(this.a);
    for (int i = 0; i < 4; i++) {
      IChatBaseComponent localIChatBaseComponent = this.b[i];
      if (paramPacketDataSerializer.version == 47) {
      String str = IChatBaseComponent.ChatSerializer.a(localIChatBaseComponent);
      paramPacketDataSerializer.a(str);
      }
      else
      {
          paramPacketDataSerializer.a(CraftChatMessage.fromComponent(localIChatBaseComponent));
      }
    }
  }

  public void a(PacketListenerPlayIn paramPacketListenerPlayIn)
  {
    paramPacketListenerPlayIn.a(this);
  }

  public BlockPosition a() {
    return this.a;
  }

  public IChatBaseComponent[] b() {
    return this.b;
  }
}