package net.minecraft.server;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class EntityWitch extends EntityMonster
  implements IRangedEntity
{
  private static final UUID a = UUID.fromString("5CD17E52-A79A-43D3-A529-90FDE04B181E");
  private static final AttributeModifier b = new AttributeModifier(a, "Drinking speed penalty", -0.25D, 0).a(false);

  private static final Item[] c = { Items.GLOWSTONE_DUST, Items.SUGAR, Items.REDSTONE, Items.SPIDER_EYE, Items.GLASS_BOTTLE, Items.GUNPOWDER, Items.STICK, Items.STICK };
  public static DataIndex<Boolean> META_AGGRESSIVE = DataWatcher.getIndex(EntityWitch.class, DataType.BOOLEAN);
  private int bm;

  public EntityWitch(World paramWorld)
  {
    super(paramWorld);
    setSize(0.6F, 1.95F);

    this.goalSelector.a(1, new PathfinderGoalFloat(this));
    this.goalSelector.a(2, new PathfinderGoalArrowAttack(this, 1.0D, 60, 10.0F));
    this.goalSelector.a(2, new PathfinderGoalRandomStroll(this, 1.0D));
    this.goalSelector.a(3, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
    this.goalSelector.a(3, new PathfinderGoalRandomLookaround(this));

    this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false, new Class[0]));
    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
  }

  protected void h()
  {
    super.h();

    getDataWatcher().a(21, Byte.valueOf((byte)0), META_AGGRESSIVE, false);
  }

  protected String z()
  {
    return null;
  }

  protected String bo()
  {
    return null;
  }

  protected String bp()
  {
    return null;
  }

  public void a(boolean paramBoolean) {
    getDataWatcher().watch(21, Byte.valueOf((byte)(paramBoolean ? 1 : 0)), META_AGGRESSIVE, paramBoolean);
  }

  public boolean n() {
    return getDataWatcher().getByte(21) == 1;
  }

  protected void initAttributes()
  {
    super.initAttributes();

    getAttributeInstance(GenericAttributes.maxHealth).setValue(26.0D);
    getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.25D);
  }

  public void m()
  {
    if (!this.world.isClientSide)
    {
      Object localObject;
      if (n()) {
        if (this.bm-- <= 0) {
          a(false);
          ItemStack localItemStack = bA();
          setEquipment(0, null);

          if ((localItemStack != null) && (localItemStack.getItem() == Items.POTION)) {
            localObject = Items.POTION.h(localItemStack);
            if (localObject != null) {
              for (MobEffect localMobEffect : (List<MobEffect>)localObject) {
                addEffect(new MobEffect(localMobEffect));
              }
            }
          }

          getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).c(b);
        }
      } else {
        int i = -1;

        if ((this.random.nextFloat() < 0.15F) && (a(Material.WATER)) && (!hasEffect(MobEffectList.WATER_BREATHING)))
          i = 8237;
        else if ((this.random.nextFloat() < 0.15F) && (isBurning()) && (!hasEffect(MobEffectList.FIRE_RESISTANCE)))
          i = 16307;
        else if ((this.random.nextFloat() < 0.05F) && (getHealth() < getMaxHealth()))
          i = 16341;
        else if ((this.random.nextFloat() < 0.25F) && (getGoalTarget() != null) && (!hasEffect(MobEffectList.FASTER_MOVEMENT)) && (getGoalTarget().h(this) > 121.0D))
          i = 16274;
        else if ((this.random.nextFloat() < 0.25F) && (getGoalTarget() != null) && (!hasEffect(MobEffectList.FASTER_MOVEMENT)) && (getGoalTarget().h(this) > 121.0D)) {
          i = 16274;
        }

        if (i > -1) {
          setEquipment(0, new ItemStack(Items.POTION, 1, i));
          this.bm = bA().l();
          a(true);
          localObject = getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);
          ((AttributeInstance)localObject).c(b);
          ((AttributeInstance)localObject).b(b);
        }
      }

      if (this.random.nextFloat() < 0.00075F) {
        this.world.broadcastEntityEffect(this, (byte)15);
      }
    }

    super.m();
  }

  protected float applyMagicModifier(DamageSource paramDamageSource, float paramFloat)
  {
    paramFloat = super.applyMagicModifier(paramDamageSource, paramFloat);

    if (paramDamageSource.getEntity() == this) {
      paramFloat = 0.0F;
    }
    if (paramDamageSource.isMagic()) {
      paramFloat = (float)(paramFloat * 0.15D);
    }

    return paramFloat;
  }

  protected void dropDeathLoot(boolean paramBoolean, int paramInt)
  {
    int i = this.random.nextInt(3) + 1;
    for (int j = 0; j < i; j++) {
      int k = this.random.nextInt(3);
      Item localItem = c[this.random.nextInt(c.length)];
      if (paramInt > 0) {
        k += this.random.nextInt(paramInt + 1);
      }

      for (int m = 0; m < k; m++)
        a(localItem, 1);
    }
  }

  public void a(EntityLiving paramEntityLiving, float paramFloat)
  {
    if (n()) {
      return;
    }

    EntityPotion localEntityPotion = new EntityPotion(this.world, this, 32732);
    double d1 = paramEntityLiving.locY + paramEntityLiving.getHeadHeight() - 1.100000023841858D;
    localEntityPotion.pitch -= -20.0F;
    double d2 = paramEntityLiving.locX + paramEntityLiving.motX - this.locX;
    double d3 = d1 - this.locY;
    double d4 = paramEntityLiving.locZ + paramEntityLiving.motZ - this.locZ;
    float f = MathHelper.sqrt(d2 * d2 + d4 * d4);

    if ((f >= 8.0F) && (!paramEntityLiving.hasEffect(MobEffectList.SLOWER_MOVEMENT)))
      localEntityPotion.setPotionValue(32698);
    else if ((paramEntityLiving.getHealth() >= 8.0F) && (!paramEntityLiving.hasEffect(MobEffectList.POISON)))
      localEntityPotion.setPotionValue(32660);
    else if ((f <= 3.0F) && (!paramEntityLiving.hasEffect(MobEffectList.WEAKNESS)) && (this.random.nextFloat() < 0.25F)) {
      localEntityPotion.setPotionValue(32696);
    }

    localEntityPotion.shoot(d2, d3 + f * 0.2F, d4, 0.75F, 8.0F);

    this.world.addEntity(localEntityPotion);
  }

  public float getHeadHeight()
  {
    return 1.62F;
  }
}