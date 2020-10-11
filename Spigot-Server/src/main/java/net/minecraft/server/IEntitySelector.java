package net.minecraft.server;

import com.google.common.base.Predicate;

public final class IEntitySelector
{
    public static class EntitySelectorEquipable
        implements Predicate
    {

        public boolean a(Entity entity)
        {
            if(!entity.isAlive())
                return false;
            if(!(entity instanceof EntityLiving))
                return false;
            EntityLiving entityliving = (EntityLiving)entity;
            if(entityliving.getEquipment(EntityInsentient.c(a)) != null)
                return false;
            if(entityliving instanceof EntityInsentient)
                return ((EntityInsentient)entityliving).bY();
            if(entityliving instanceof EntityArmorStand)
                return true;
            return entityliving instanceof EntityHuman;
        }

        public boolean apply(Object obj)
        {
            return a((Entity)obj);
        }

        private final ItemStack a;

        public EntitySelectorEquipable(ItemStack itemstack)
        {
            a = itemstack;
        }
    }


    public static final Predicate a = new Predicate() {

        public boolean a(Entity entity)
        {
            return entity.isAlive();
        }

        public boolean apply(Object obj)
        {
            return a((Entity)obj);
        }

    }
;
    public static final Predicate b = new Predicate() {

        public boolean a(Entity entity)
        {
            return entity.isAlive() && entity.passenger == null && entity.vehicle == null;
        }

        public boolean apply(Object obj)
        {
            return a((Entity)obj);
        }

    }
;
    public static final Predicate c = new Predicate() {

        public boolean a(Entity entity)
        {
            return (entity instanceof IInventory) && entity.isAlive();
        }

        public boolean apply(Object obj)
        {
            return a((Entity)obj);
        }

    }
;
    public static final Predicate d = new Predicate() {

        public boolean a(Entity entity)
        {
            if (entity instanceof EntityLiving && ((EntityLiving) entity).isGhost())
            {
                return false;
            }

            return !(entity instanceof EntityHuman) || !(((EntityHuman)entity).isSpectator() || ((EntityPlayer)entity).spectating);
        }

        public boolean apply(Object obj)
        {
            return a((Entity)obj);
        }

    }
;

}
