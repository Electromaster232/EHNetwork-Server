package com.mineplex.spigot;

import net.minecraft.server.Packet;

public interface IPacketVerifier
{
    public boolean handlePacket(Packet packet);
}