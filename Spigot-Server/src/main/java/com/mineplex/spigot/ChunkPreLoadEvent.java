package com.mineplex.spigot;

import org.bukkit.World;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ChunkPreLoadEvent extends Event implements Cancellable
{
    private static final HandlerList handlers = new HandlerList();
    private boolean _cancelled;
    private World _world;
    private int _x;
    private int _z;

    public ChunkPreLoadEvent(World world, int x, int z)
    {
        _world = world;
        _x = x;
        _z = z;
    }

    public World getWorld()
    {
        return _world;
    }

    public int getX()
    {
        return _x;
    }

    public int getZ()
    {
        return _z;
    }

    @Override
    public boolean isCancelled()
    {
        return _cancelled;
    }

    @Override
    public void setCancelled(boolean cancel)
    {
        _cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }
}
