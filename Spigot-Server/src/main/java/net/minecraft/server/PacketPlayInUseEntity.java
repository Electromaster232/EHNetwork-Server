package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInUseEntity
  implements Packet<PacketListenerPlayIn>
{
          public int a;
          public EnumEntityUseAction action;
          public Vec3D c;

          public void a(PacketDataSerializer paramPacketDataSerializer)
            throws IOException
          {
            this.a = paramPacketDataSerializer.e();
            this.action = ((EnumEntityUseAction)paramPacketDataSerializer.a(EnumEntityUseAction.class));
            if (this.action == EnumEntityUseAction.INTERACT_AT)
              this.c = new Vec3D(paramPacketDataSerializer.readFloat(), paramPacketDataSerializer.readFloat(), paramPacketDataSerializer.readFloat());
            if (this.action != EnumEntityUseAction.ATTACK && paramPacketDataSerializer.version != 47)
            {
                paramPacketDataSerializer.e();
            }
          }

          public void b(PacketDataSerializer paramPacketDataSerializer)
            throws IOException
          {
            paramPacketDataSerializer.b(this.a);
            paramPacketDataSerializer.a(this.action);
            if (this.action == EnumEntityUseAction.INTERACT_AT) {
              paramPacketDataSerializer.writeFloat((float)this.c.a);
              paramPacketDataSerializer.writeFloat((float)this.c.b);
              paramPacketDataSerializer.writeFloat((float)this.c.c);
            }
          }

          public void a(PacketListenerPlayIn paramPacketListenerPlayIn)
          {
            paramPacketListenerPlayIn.a(this);
          }

          public Entity a(World paramWorld) {
            return paramWorld.a(this.a);
          }

          public EnumEntityUseAction a() {
            return this.action;
          }

          public Vec3D b() {
            return this.c;
          }

          public static enum EnumEntityUseAction {
              INTERACT, ATTACK, INTERACT_AT;
          }
}