package com.mineplex.spigot;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ChunkAddEntityEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();
    private Entity _entity;

    public ChunkAddEntityEvent(Entity _entity)
    {
        this._entity = _entity;
    }

    public Entity getEntity()
    {
        return _entity;
    }

    public HandlerList getHandlers()
    {
        return handlers;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }
}
