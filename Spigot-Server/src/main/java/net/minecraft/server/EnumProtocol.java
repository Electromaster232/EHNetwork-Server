package net.minecraft.server;

import com.google.common.collect.*;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Map.Entry;

public enum EnumProtocol
{
    HANDSHAKING(-1) {


            {
                a(EnumProtocolDirection.SERVERBOUND, PacketHandshakingInSetProtocol.class, 0);
            }
        },


    PLAY(0) {


            {
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutSetCompression.class, 70, -1);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutMapChunkBulk.class, 38, -1);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutUpdateEntityNBT.class, 73, -1);

                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutSpawnEntity.class, 14, 0);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutSpawnEntityExperienceOrb.class, 17, 1);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutSpawnEntityWeather.class, 44, 2);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutSpawnEntityLiving.class, 15, 3);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutSpawnEntityPainting.class, 16, 4);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutNamedEntitySpawn.class, 12, 5);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutAnimation.class, 11, 6);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutStatistic.class, 55, 7);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutBlockBreakAnimation.class, 37, 8);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutTileEntityData.class, 53, 9);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutBlockAction.class, 36, 10);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutBlockChange.class, 35, 11);
                // Boss bar
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutBossBar.class, -1, 12);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutServerDifficulty.class, 65, 13);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutTabComplete.class, 58, 14);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutChat.class, 2, 15);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutMultiBlockChange.class, 34, 16);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutTransaction.class, 50, 17);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutCloseWindow.class, 46, 18);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutOpenWindow.class, 45, 19);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutWindowItems.class, 48, 20);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutWindowData.class, 49, 21);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutSetSlot.class, 47, 22);
                // Set cooldown
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutSetCooldown.class, -1, 23);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutCustomPayload.class, 63, 24);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutNamedSoundEffect.class, 41, 25);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutKickDisconnect.class, 64, 26);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutEntityStatus.class, 26, 27);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutExplosion.class, 39, 28);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutUnloadChunk.class, -1, 29);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutGameStateChange.class, 43, 30);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutKeepAlive.class, 0, 31);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutMapChunk.class, 33, 32);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutWorldEvent.class, 40, 33);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutWorldParticles.class, 42, 34);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutLogin.class, 1, 35);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutMap.class, 52, 36);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutEntity.PacketPlayOutRelEntityMove.class, 21, 37);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook.class, 23, 38);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutEntity.PacketPlayOutEntityLook.class, 22, 39);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutEntity.class, 20, 40);
                // ??? double, double, double, float float
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutOpenSignEditor.class, 54, 42);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutAbilities.class, 57, 43);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutCombatEvent.class, 66, 44);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutPlayerInfo.class, 56, 45);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutPosition.class, 8, 46);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutBed.class, 10, 47);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutEntityDestroy.class, 19, 48);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutRemoveEntityEffect.class, 30, 49);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutResourcePackSend.class, 72, 50);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutRespawn.class, 7, 51);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutEntityHeadRotation.class, 25, 52);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutWorldBorder.class, 68, 53);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutCamera.class, 67, 54);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutHeldItemSlot.class, 9, 55);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutScoreboardDisplayObjective.class, 61, 56);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutEntityMetadata.class, 28, 57);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutAttachEntity.class, 27, 58);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutEntityVelocity.class, 18, 59);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutEntityEquipment.class, 4, 60);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutExperience.class, 31, 61);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutUpdateHealth.class, 6, 62);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutScoreboardObjective.class, 59, 63);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutNewAttachEntity.class, -1, 64);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutScoreboardTeam.class, 62, 65);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutScoreboardScore.class, 60, 66);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutSpawnPosition.class, 5, 67);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutUpdateTime.class, 3, 68);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutTitle.class, 69, 69);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutUpdateSign.class, 51, 70);
                // Subtitle packet
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutPlayerListHeaderFooter.class, 71, 72);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutCollect.class, 13, 73);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutEntityTeleport.class, 24, 74);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutUpdateAttributes.class, 32, 75);
                a(EnumProtocolDirection.CLIENTBOUND, PacketPlayOutEntityEffect.class, 29, 76);



                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInLeftClick.class, -1, 0);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInTabComplete.class, 20, 1);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInChat.class, 1, 2);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInClientCommand.class, 22, 3);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInSettings.class, 21, 4);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInTransaction.class, 15, 5);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInEnchantItem.class, 17, 6);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInWindowClick.class, 14, 7);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInCloseWindow.class, 13, 8);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInCustomPayload.class, 23, 9);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInUseEntity.class, 2, 10);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInKeepAlive.class, 0, 11);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInFlying.PacketPlayInPosition.class, 4, 12);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInFlying.PacketPlayInPositionLook.class, 6, 13);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInFlying.PacketPlayInLook.class, 5, 14);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInFlying.class, 3, 15);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInUnknownPosition.class, -1, 16);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInUnknownFloats.class, -1, 17);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInAbilities.class, 19, 18);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInBlockDig.class, 7, 19);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInEntityAction.class, 11, 20);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInSteerVehicle.class, 12, 21);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInResourcePackStatus.class, 25, 22);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInHeldItemSlot.class, 9, 23);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInSetCreativeSlot.class, 16, 24);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInUpdateSign.class, 18, 25);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInArmAnimation.class, 10, 26);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInSpectate.class, 24, 27);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInBlockPlace.class, 8, 28);
                a(EnumProtocolDirection.SERVERBOUND, PacketPlayInRightClick.class, -1, 29);

            }
        },


    STATUS(1) {


            {
                a(EnumProtocolDirection.SERVERBOUND, PacketStatusInStart.class, 0);
                a(EnumProtocolDirection.CLIENTBOUND, PacketStatusOutServerInfo.class, 0);
                a(EnumProtocolDirection.SERVERBOUND, PacketStatusInPing.class, 1);
                a(EnumProtocolDirection.CLIENTBOUND, PacketStatusOutPong.class, 1);
            }
        },


    LOGIN(2) {


            {
                a(EnumProtocolDirection.CLIENTBOUND, PacketLoginOutDisconnect.class, 0);
                a(EnumProtocolDirection.CLIENTBOUND, PacketLoginOutEncryptionBegin.class, 1);
                a(EnumProtocolDirection.CLIENTBOUND, PacketLoginOutSuccess.class, 2);
                a(EnumProtocolDirection.CLIENTBOUND, PacketLoginOutSetCompression.class, 3);
                a(EnumProtocolDirection.SERVERBOUND, PacketLoginInStart.class, 0);
                a(EnumProtocolDirection.SERVERBOUND, PacketLoginInEncryptionBegin.class, 1);
            }
        };

    private EnumProtocol(int i1)
    {
        j = Maps.newEnumMap(EnumProtocolDirection.class);
        i = i1;
    }

    protected EnumProtocol a(EnumProtocolDirection enumprotocoldirection, Class class1, int id19)
    {
        Object obj = (BiMap)j.get(enumprotocoldirection);
        if(obj == null)
        {
            obj = HashBiMap.create();
            j.put(enumprotocoldirection, obj);
        }
        if(((BiMap) (obj)).containsValue(class1))
        {
            String s = (new StringBuilder()).append(enumprotocoldirection).append(" packet ").append(class1).append(" is already known to ID ").append(((BiMap) (obj)).inverse().get(class1)).toString();
            LogManager.getLogger().fatal(s);
            throw new IllegalArgumentException(s);
        } else
        {
            ((BiMap) (obj)).put(new HashMap.SimpleEntry(((BiMap) (obj)).size(), id19), class1);
            return this;
        }
    }

    protected EnumProtocol a(EnumProtocolDirection enumprotocoldirection, Class class1, int id18, int id19)
    {
        Object obj = (BiMap)j.get(enumprotocoldirection);
        if(obj == null)
        {
            obj = HashBiMap.create();
            j.put(enumprotocoldirection, obj);
        }
        if(((BiMap) (obj)).containsValue(class1))
        {
            String s = (new StringBuilder()).append(enumprotocoldirection).append(" packet ").append(class1).append(" is already known to ID ").append(((BiMap) (obj)).inverse().get(class1)).toString();
            LogManager.getLogger().fatal(s);
            throw new IllegalArgumentException(s);
        } else
        {
            ((BiMap) (obj)).put(new HashMap.SimpleEntry(id18, id19), class1);
            return this;
        }
    }

    public Integer a(EnumProtocolDirection enumprotocoldirection, Packet packet, boolean is1_8)
    {
        Entry entry = (Entry) ((BiMap)j.get(enumprotocoldirection)).inverse().get(packet.getClass());
        if ((Integer) (is1_8 ? entry.getKey() : entry.getValue()) < 0)
        {
            System.out.print("Trying to send unsupported " + packet.getClass().getSimpleName());
            Thread.dumpStack();
        }
        return (Integer) (is1_8 ? entry.getKey() : entry.getValue());
    }

    public Packet a(EnumProtocolDirection enumprotocoldirection, int l, boolean is1_8)
        throws IllegalAccessException, InstantiationException
    {
        for (Entry entry : (Set<Entry>) ((BiMap) j.get(enumprotocoldirection)).entrySet())
        {
            Entry entry1 = (Entry) entry.getKey();
            if (l == (is1_8 ? (Integer) entry1.getKey() : (Integer) entry1.getValue()))
            {
                return (Packet) ((Class) entry.getValue()).newInstance();
            }
        }
        return null;
    }

    public int a()
    {
        return i;
    }

    public static EnumProtocol a(int l)
    {
        if(l < e || l > f)
            return null;
        else
            return g[l - e];
    }

    public static EnumProtocol a(Packet packet)
    {
        return (EnumProtocol)h.get(packet.getClass());
    }

    private static int e;
    private static int f;
    private static final EnumProtocol g[];
    private static final Map h;
    private final int i;
    private final Map j;

    static
    {
        e = -1;
        f = 2;
        g = new EnumProtocol[(f - e) + 1];
        h = Maps.newHashMap();
        EnumProtocol aenumprotocol[] = values();
        int l = aenumprotocol.length;
        for(int i1 = 0; i1 < l; i1++)
        {
            EnumProtocol enumprotocol = aenumprotocol[i1];
            int j1 = enumprotocol.a();
            if(j1 < e || j1 > f)
                throw new Error((new StringBuilder()).append("Invalid protocol ID ").append(Integer.toString(j1)).toString());
            g[j1 - e] = enumprotocol;
            for(Iterator iterator = enumprotocol.j.keySet().iterator(); iterator.hasNext();)
            {
                EnumProtocolDirection enumprotocoldirection = (EnumProtocolDirection)iterator.next();
                Iterator iterator1 = ((BiMap)enumprotocol.j.get(enumprotocoldirection)).values().iterator();
                while(iterator1.hasNext())
                {
                    Class class1 = (Class)iterator1.next();
                    if(h.containsKey(class1) && h.get(class1) != enumprotocol)
                        throw new Error((new StringBuilder()).append("Packet ").append(class1).append(" is already assigned to protocol ").append(h.get(class1)).append(" - can't reassign to ").append(enumprotocol).toString());
                    try
                    {
                        class1.newInstance();
                    }
                    catch(Throwable throwable)
                    {
                        throw new Error((new StringBuilder()).append("Packet ").append(class1).append(" fails instantiation checks! ").append(class1).toString());
                    }
                    h.put(class1, enumprotocol);
                }
            }

        }

    }
}