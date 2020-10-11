package com.mineplex.spigot;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import io.netty.util.concurrent.GenericFutureListener;

import net.minecraft.server.NetworkManager;
import net.minecraft.server.IChatBaseComponent;

import net.minecraft.server.Packet;
import net.minecraft.server.PacketListenerPlayIn;
import net.minecraft.server.IUpdatePlayerListBox;
import net.minecraft.server.PlayerConnection;
import net.minecraft.server.PacketPlayInChat;
import net.minecraft.server.PacketPlayInTabComplete;
import net.minecraft.server.PacketPlayInClientCommand;
import net.minecraft.server.PacketPlayInSettings;
import net.minecraft.server.PacketPlayInTransaction;
import net.minecraft.server.PacketPlayInEnchantItem;
import net.minecraft.server.PacketPlayInWindowClick;
import net.minecraft.server.PacketPlayInCloseWindow;
import net.minecraft.server.PacketPlayInCustomPayload;
import net.minecraft.server.PacketPlayInUseEntity;
import net.minecraft.server.PacketPlayInKeepAlive;
import net.minecraft.server.PacketPlayInFlying;
import net.minecraft.server.PacketPlayInAbilities;
import net.minecraft.server.PacketPlayInBlockDig;
import net.minecraft.server.PacketPlayInEntityAction;
import net.minecraft.server.PacketPlayInSteerVehicle;
import net.minecraft.server.PacketPlayInHeldItemSlot;
import net.minecraft.server.PacketPlayInSetCreativeSlot;
import net.minecraft.server.PacketPlayInUpdateSign;
import net.minecraft.server.PacketPlayInBlockPlace;
import net.minecraft.server.PacketPlayInSpectate;
import net.minecraft.server.PacketPlayInResourcePackStatus;
import net.minecraft.server.PacketPlayInArmAnimation;
import net.minecraft.server.PacketPlayInLeftClick;
import net.minecraft.server.PacketPlayInRightClick;
import net.minecraft.server.PacketPlayInUnknownPosition;
import net.minecraft.server.PacketPlayInUnknownFloats;

public class PacketProcessor implements PacketListenerPlayIn, IUpdatePlayerListBox
{
    private IPacketVerifier _packetVerifier;
    private PlayerConnection _packetListener;
    private static volatile HashMap<Class, Boolean> _listenedPackets = new HashMap<Class, Boolean>();

    public PacketProcessor(PlayerConnection packetListener)
    {
        _packetListener = packetListener;
    }

    public void setPacketVerifier(IPacketVerifier verifier)
    {
        _packetVerifier = verifier;
    }

    public void c()
    {
        _packetListener.c();
    }
    
    public static void addPacket(Class packetClass, boolean forceMainThread)
    {
        _listenedPackets.put(packetClass, forceMainThread);
    }

    public static void removePacket(Class packetClass)
    {
        _listenedPackets.remove(packetClass);
    }

    public void processOutgoingPacket(Packet packet, NetworkManager networkManager)
    {
        Boolean isMainThread = _listenedPackets.get(packet.getClass());
        if (isMainThread == null)
        {
            networkManager.handle(packet);
            return;
        }
 
        boolean addDefaultPacket = true;

        if (_packetVerifier != null)
        {
            if (!_packetVerifier.handlePacket(packet))
            {
                addDefaultPacket = false;
            }
        }

        if (addDefaultPacket)
        {
            networkManager.handle(packet);
        }
    }

    public void processIncomingPacket(final Packet packet)
    {
        Boolean isMainThread = _listenedPackets.get(packet.getClass());
        if (isMainThread == null)
        {
            packet.a(_packetListener);
            return;
        }

        if (!_packetListener.player.u().isMainThread() && isMainThread)
        {
            _packetListener.player.u().postToMainThread(new Runnable()
            {
                public void run()
                {
                    processIncomingPacket(packet);
                }
            });

            return;
        }

        boolean addDefaultPacket = true;

        if (_packetVerifier != null)
        {
            if (!_packetVerifier.handlePacket(packet))
            {
                addDefaultPacket = false;
            }
        }

        if (addDefaultPacket)
        {
            packet.a(_packetListener);
        }
    }

    public void a(PacketPlayInChat packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInTabComplete packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInClientCommand packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInSettings packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInTransaction packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInEnchantItem packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInWindowClick packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInCloseWindow packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInCustomPayload packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInUseEntity packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInKeepAlive packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInFlying packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInAbilities packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInBlockDig packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInEntityAction packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInSteerVehicle packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInHeldItemSlot packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInSetCreativeSlot packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInUpdateSign packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInBlockPlace packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInSpectate packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInResourcePackStatus packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInArmAnimation packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInUnknownFloats packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInUnknownPosition packet)
    {
        processIncomingPacket(packet);
    }

    public void a(IChatBaseComponent packet)
    {
        _packetListener.a(packet);
    }

    public void a(PacketPlayInLeftClick packet)
    {
        processIncomingPacket(packet);
    }

    public void a(PacketPlayInRightClick packet)
    {
        processIncomingPacket(packet);
    }
}