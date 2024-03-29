package net.minecraft.server;

import com.google.common.base.Predicate;
import java.util.*;

public class EntityGuardian extends EntityMonster
{
    private float a;
    private float b;
    private float c;
    private float bm;
    private float bn;
    private EntityLiving bo;
    private int bp;
    private boolean bq;
    public PathfinderGoalRandomStroll goalRandomStroll;
    public static DataIndex<Byte> META_ELDER = DataWatcher.getIndex(EntityGuardian.class, DataType.BYTE);
    public static DataIndex<Integer> META_TARGET = DataWatcher.getIndex(EntityGuardian.class, DataType.INTEGER);
    static class ControllerMoveGuardian extends ControllerMove
    {

        public void c()
        {
            if(!this.f || g.getNavigation().m())
            {
                g.k(0.0F);
                EntityGuardian.a(g, false);
                return;
            }
            double d = b - g.locX;
            double d1 = c - g.locY;
            double d2 = this.d - g.locZ;
            double d3 = d * d + d1 * d1 + d2 * d2;
            d3 = MathHelper.sqrt(d3);
            d1 /= d3;
            float f = (float)((MathHelper.b(d2, d) * 180D) / 3.1415927410125732D) - 90F;
            g.yaw = a(g.yaw, f, 30F);
            g.aI = g.yaw;
            float f1 = (float)(e * g.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).getValue());
            g.k(g.bI() + (f1 - g.bI()) * 0.125F);
            double d4 = Math.sin((double)(g.ticksLived + g.getId()) * 0.5D) * 0.050000000000000003D;
            double d5 = Math.cos((g.yaw * 3.141593F) / 180F);
            double d6 = Math.sin((g.yaw * 3.141593F) / 180F);
            g.motX += d4 * d5;
            g.motZ += d4 * d6;
            d4 = Math.sin((double)(g.ticksLived + g.getId()) * 0.75D) * 0.050000000000000003D;
            g.motY += d4 * (d6 + d5) * 0.25D;
            g.motY += (double)g.bI() * d1 * 0.10000000000000001D;
            ControllerLook controllerlook = g.getControllerLook();
            double d7 = g.locX + (d / d3) * 2D;
            double d8 = (double)g.getHeadHeight() + g.locY + (d1 / d3) * 1.0D;
            double d9 = g.locZ + (d2 / d3) * 2D;
            double d10 = controllerlook.e();
            double d11 = controllerlook.f();
            double d12 = controllerlook.g();
            if(!controllerlook.b())
            {
                d10 = d7;
                d11 = d8;
                d12 = d9;
            }
            g.getControllerLook().a(d10 + (d7 - d10) * 0.125D, d11 + (d8 - d11) * 0.125D, d12 + (d9 - d12) * 0.125D, 10F, 40F);
            EntityGuardian.a(g, true);
        }

        private EntityGuardian g;

        public ControllerMoveGuardian(EntityGuardian entityguardian)
        {
            super(entityguardian);
            g = entityguardian;
        }
    }

    static class PathfinderGoalGuardianAttack extends PathfinderGoal
    {

        public boolean a()
        {
            EntityLiving entityliving = a.getGoalTarget();
            return entityliving != null && entityliving.isAlive();
        }

        public boolean b()
        {
            return super.b() && (a.isElder() || a.h(a.getGoalTarget()) > 9D);
        }

        public void c()
        {
            b = -10;
            a.getNavigation().n();
            a.getControllerLook().a(a.getGoalTarget(), 90F, 90F);
            a.ai = true;
        }

        public void d()
        {
            EntityGuardian.a(a, 0);
            a.setGoalTarget(null);
            EntityGuardian.a(a).f();
        }

        public void e()
        {
            EntityLiving entityliving = a.getGoalTarget();
            a.getNavigation().n();
            a.getControllerLook().a(entityliving, 90F, 90F);
            if(!a.hasLineOfSight(entityliving))
            {
                a.setGoalTarget(null);
                return;
            }
            b++;
            if(b == 0)
            {
                EntityGuardian.a(a, a.getGoalTarget().getId());
                a.world.broadcastEntityEffect(a, (byte)21);
            } else
            if(b >= a.cm())
            {
                float f = 1.0F;
                if(a.world.getDifficulty() == EnumDifficulty.HARD)
                    f += 2.0F;
                if(a.isElder())
                    f += 2.0F;
                entityliving.damageEntity(DamageSource.b(a, a), f);
                entityliving.damageEntity(DamageSource.mobAttack(a), (float)a.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).getValue());
                a.setGoalTarget(null);
            } else
            if(b >= 60)
                if(b % 20 != 0);
            super.e();
        }

        private EntityGuardian a;
        private int b;

        public PathfinderGoalGuardianAttack(EntityGuardian entityguardian)
        {
            a = entityguardian;
            a(3);
        }
    }

    static class EntitySelectorGuardianTargetHumanSquid
        implements Predicate
    {

        public boolean a(EntityLiving entityliving)
        {
            return ((entityliving instanceof EntityHuman) || (entityliving instanceof EntitySquid)) && entityliving.h(a) > 9D;
        }

        public boolean apply(Object obj)
        {
            return a((EntityLiving)obj);
        }

        private EntityGuardian a;

        public EntitySelectorGuardianTargetHumanSquid(EntityGuardian entityguardian)
        {
            a = entityguardian;
        }
    }


    public EntityGuardian(World world)
    {
        super(world);
        b_ = 10;
        setSize(0.85F, 0.85F);
        goalSelector.a(4, new PathfinderGoalGuardianAttack(this));
        PathfinderGoalMoveTowardsRestriction pathfindergoalmovetowardsrestriction;
        goalSelector.a(5, pathfindergoalmovetowardsrestriction = new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
        goalSelector.a(7, goalRandomStroll = new PathfinderGoalRandomStroll(this, 1.0D, 80));
        goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8F));
        goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityGuardian.class, 12F, 0.01F));
        goalSelector.a(9, new PathfinderGoalRandomLookaround(this));
        goalRandomStroll.a(3);
        pathfindergoalmovetowardsrestriction.a(3);
        targetSelector.a(1, new PathfinderGoalNearestAttackableTarget(this, EntityLiving.class, 10, true, false, new EntitySelectorGuardianTargetHumanSquid(this)));
        moveController = new ControllerMoveGuardian(this);
        b = a = random.nextFloat();
    }

    public void initAttributes()
    {
        super.initAttributes();
        getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(6D);
        getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.5D);
        getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(16D);
        getAttributeInstance(GenericAttributes.maxHealth).setValue(30D);
    }

    public void a(NBTTagCompound nbttagcompound)
    {
        super.a(nbttagcompound);
        setElder(nbttagcompound.getBoolean("Elder"));
    }

    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
        nbttagcompound.setBoolean("Elder", isElder());
    }

    protected NavigationAbstract b(World world)
    {
        return new NavigationGuardian(this, world);
    }

    protected void h()
    {
        super.h();
        datawatcher.a(16, Integer.valueOf(0), META_ELDER, (byte) 0);
        datawatcher.a(17, Integer.valueOf(0), META_TARGET, 0);
    }

    private boolean a(int j)
    {
        return (datawatcher.getInt(16) & j) != 0;
    }

    private void a(int j, boolean flag)
    {
        int k = datawatcher.getInt(16);
        if(flag)
            datawatcher.watch(16, Integer.valueOf(k | j), META_ELDER, (byte) (k | j));
        else
            datawatcher.watch(16, Integer.valueOf(k & ~j), META_TARGET, Integer.valueOf(k & ~j));
    }

    public boolean n()
    {
        return a(2);
    }

    private void l(boolean flag)
    {
        a(2, flag);
    }

    public int cm()
    {
        return !isElder() ? 80 : 60;
    }

    public boolean isElder()
    {
        return a(4);
    }

    public void setElder(boolean flag)
    {
        a(4, flag);
        if(flag)
        {
            setSize(1.9975F, 1.9975F);
            getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.30000001192092896D);
            getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(8D);
            getAttributeInstance(GenericAttributes.maxHealth).setValue(80D);
            bX();
            goalRandomStroll.setTimeBetweenMovement(400);
        }
    }

    private void b(int j)
    {
        datawatcher.watch(17, Integer.valueOf(j), META_TARGET, j);
    }

    public boolean cp()
    {
        return datawatcher.getInt(17) != 0;
    }

    public EntityLiving cq()
    {
        if(!cp())
            return null;
        if(world.isClientSide)
        {
            if(bo != null)
                return bo;
            Entity entity = world.a(datawatcher.getInt(17));
            if(entity instanceof EntityLiving)
            {
                bo = (EntityLiving)entity;
                return bo;
            } else
            {
                return null;
            }
        } else
        {
            return getGoalTarget();
        }
    }

    public void i(int j)
    {
        super.i(j);
        if(j == 16)
        {
            if(isElder() && width < 1.0F)
                setSize(1.9975F, 1.9975F);
        } else
        if(j == 17)
        {
            bp = 0;
            bo = null;
        }
    }

    public int w()
    {
        return 160;
    }

    protected String z()
    {
        if(!V())
            return "mob.guardian.land.idle";
        if(isElder())
            return "mob.guardian.elder.idle";
        else
            return "mob.guardian.idle";
    }

    protected String bo()
    {
        if(!V())
            return "mob.guardian.land.hit";
        if(isElder())
            return "mob.guardian.elder.hit";
        else
            return "mob.guardian.hit";
    }

    protected String bp()
    {
        if(!V())
            return "mob.guardian.land.death";
        if(isElder())
            return "mob.guardian.elder.death";
        else
            return "mob.guardian.death";
    }

    protected boolean s_()
    {
        return false;
    }

    public float getHeadHeight()
    {
        return length * 0.5F;
    }

    public float a(BlockPosition blockposition)
    {
        if(world.getType(blockposition).getBlock().getMaterial() == Material.WATER)
            return (10F + world.o(blockposition)) - 0.5F;
        else
            return super.a(blockposition);
    }

    public void m()
    {
        if(world.isClientSide)
        {
            b = a;
            if(!V())
            {
                c = 2.0F;
                if(motY > 0.0D && bq && !R())
                    world.a(locX, locY, locZ, "mob.guardian.flop", 1.0F, 1.0F, false);
                bq = motY < 0.0D && world.d((new BlockPosition(this)).down(), false);
            } else
            if(n())
            {
                if(c < 0.5F)
                    c = 4F;
                else
                    c = c + (0.5F - c) * 0.1F;
            } else
            {
                c = c + (0.125F - c) * 0.2F;
            }
            a += c;
            bn = bm;
            if(!V())
                bm = random.nextFloat();
            else
            if(n())
                bm = bm + (0.0F - bm) * 0.25F;
            else
                bm = bm + (1.0F - bm) * 0.06F;
            if(n() && V())
            {
                Vec3D vec3d = d(0.0F);
                for(int j = 0; j < 2; j++)
                    world.addParticle(EnumParticle.WATER_BUBBLE, (locX + (random.nextDouble() - 0.5D) * (double)width) - vec3d.a * 1.5D, (locY + random.nextDouble() * (double)length) - vec3d.b * 1.5D, (locZ + (random.nextDouble() - 0.5D) * (double)width) - vec3d.c * 1.5D, 0.0D, 0.0D, 0.0D, new int[0]);

            }
            if(cp())
            {
                if(bp < cm())
                    bp++;
                EntityLiving entityliving = cq();
                if(entityliving != null)
                {
                    getControllerLook().a(entityliving, 90F, 90F);
                    getControllerLook().a();
                    double d = q(0.0F);
                    double d1 = entityliving.locX - locX;
                    double d2 = (entityliving.locY + (double)(entityliving.length * 0.5F)) - (locY + (double)getHeadHeight());
                    double d3 = entityliving.locZ - locZ;
                    double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
                    d1 /= d4;
                    d2 /= d4;
                    d3 /= d4;
                    for(double d5 = random.nextDouble(); d5 < d4;)
                    {
                        d5 += (1.8D - d) + random.nextDouble() * (1.7D - d);
                        world.addParticle(EnumParticle.WATER_BUBBLE, locX + d1 * d5, locY + d2 * d5 + (double)getHeadHeight(), locZ + d3 * d5, 0.0D, 0.0D, 0.0D, new int[0]);
                    }

                }
            }
        }
        if(isVegetated() || inWater)
            setAirTicks(300);
        else
        if(onGround)
        {
            motY += 0.5D;
            motX += (random.nextFloat() * 2.0F - 1.0F) * 0.4F;
            motZ += (random.nextFloat() * 2.0F - 1.0F) * 0.4F;
            yaw = random.nextFloat() * 360F;
            onGround = false;
            ai = true;
        }
        if(cp())
            yaw = aK;
        super.m();
    }

    public float q(float f)
    {
        return ((float)bp + f) / (float)cm();
    }

    protected void E()
    {
        super.E();
        if(isElder())
        {
             int i = 1200;
      int j = 1200;
      int k = 6000;
      int m = 2;
      
      if ((this.ticksLived + getId()) % 1200 == 0) {
       MobEffectList mobeffectlist = MobEffectList.SLOWER_DIG;
                List list = world.b(EntityPlayer.class, new Predicate() {

                    public boolean a(EntityPlayer entityplayer1)
                    {
                        return h(entityplayer1) < 2500D && entityplayer1.playerInteractManager.c();
                    }

                    public boolean apply(Object obj)
                    {
                        return a((EntityPlayer)obj);
                    }
                }
);
                Iterator iterator = list.iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    EntityPlayer entityplayer = (EntityPlayer)iterator.next();
                    if(!entityplayer.hasEffect(mobeffectlist) || entityplayer.getEffect(mobeffectlist).getAmplifier() < 2 || entityplayer.getEffect(mobeffectlist).getDuration() < 1200)
                    {
                        entityplayer.playerConnection.sendPacket(new PacketPlayOutGameStateChange(10, 0.0F));
                        entityplayer.addEffect(new MobEffect(mobeffectlist.id, 6000, 2));
                    }
                } while(true);
            }
            if(!ck())
                a(new BlockPosition(this), 16);
        }
    }

    protected void dropDeathLoot(boolean flag, int j)
    {
        int k = random.nextInt(3) + random.nextInt(j + 1);
        if(k > 0)
            a(new ItemStack(Items.PRISMARINE_SHARD, k, 0), 1.0F);
        if(random.nextInt(3 + j) > 1)
            a(new ItemStack(Items.FISH, 1, ItemFish.EnumFish.COD.a()), 1.0F);
        else
        if(random.nextInt(3 + j) > 1)
            a(new ItemStack(Items.PRISMARINE_CRYSTALS, 1, 0), 1.0F);
        if(flag && isElder())
            a(new ItemStack(Blocks.SPONGE, 1, 1), 1.0F);
    }

    protected void getRareDrop()
    {
        ItemStack itemstack = ((PossibleFishingResult)WeightedRandom.a(random, EntityFishingHook.j())).a(random);
        a(itemstack, 1.0F);
    }

    protected boolean n_()
    {
        return true;
    }

    public boolean canSpawn()
    {
        return world.a(getBoundingBox(), this) && world.getCubes(this, getBoundingBox()).isEmpty();
    }

    public boolean bR()
    {
        return (random.nextInt(20) == 0 || !world.j(new BlockPosition(this))) && super.bR();
    }

    public boolean damageEntity(DamageSource damagesource, float f)
    {
        if(!n() && !damagesource.isMagic() && (damagesource.i() instanceof EntityLiving))
        {
            EntityLiving entityliving = (EntityLiving)damagesource.i();
            if(!damagesource.isExplosion())
            {
                entityliving.damageEntity(DamageSource.a(this), 2.0F);
                entityliving.makeSound("damage.thorns", 0.5F, 1.0F);
            }
        }
        goalRandomStroll.f();
        return super.damageEntity(damagesource, f);
    }

    public int bQ()
    {
        return 180;
    }

    public void g(float f, float f1)
    {
        if(bM())
        {
            if(V())
            {
                a(f, f1, 0.1F);
                move(motX, motY, motZ);
                motX *= 0.89999997615814209D;
                motY *= 0.89999997615814209D;
                motZ *= 0.89999997615814209D;
                if(!n() && getGoalTarget() == null)
                    motY -= 0.0050000000000000001D;
            } else
            {
                super.g(f, f1);
            }
        } else
        {
            super.g(f, f1);
        }
    }

    static void a(EntityGuardian entityguardian, int j)
    {
        entityguardian.b(j);
    }

    static PathfinderGoalRandomStroll a(EntityGuardian entityguardian)
    {
        return entityguardian.goalRandomStroll;
    }

    static void a(EntityGuardian entityguardian, boolean flag)
    {
        entityguardian.l(flag);
    }
}
