package net.minecraft.server;

import java.util.Random;

public class EntityMinecartFurnace extends EntityMinecartAbstract
{
  private int c;
  public double a;
  public double b;
  public static DataIndex<Boolean> META_BURNING = DataWatcher.getIndex(EntityMinecartFurnace.class, DataType.BOOLEAN);

  public EntityMinecartFurnace(World paramWorld)
  {
    super(paramWorld);
  }

  public EntityMinecartFurnace(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3) {
    super(paramWorld, paramDouble1, paramDouble2, paramDouble3);
  }

  public EntityMinecartAbstract.EnumMinecartType s()
  {
    return EntityMinecartAbstract.EnumMinecartType.FURNACE;
  }

  protected void h()
  {
    super.h();
    this.datawatcher.a(16, new Byte((byte)0), META_BURNING, false);
  }

  public void t_()
  {
    super.t_();

    if (this.c > 0) {
      this.c -= 1;
    }
    if (this.c <= 0) {
      this.a = (this.b = 0.0D);
    }
    i(this.c > 0);

    if ((j()) && (this.random.nextInt(4) == 0))
      this.world.addParticle(EnumParticle.SMOKE_LARGE, this.locX, this.locY + 0.8D, this.locZ, 0.0D, 0.0D, 0.0D, new int[0]);
  }

  protected double m()
  {
    return 0.2D;
  }

  public void a(DamageSource paramDamageSource)
  {
    super.a(paramDamageSource);

    if ((!paramDamageSource.isExplosion()) && (this.world.getGameRules().getBoolean("doEntityDrops")))
      a(new ItemStack(Blocks.FURNACE, 1), 0.0F);
  }

  protected void a(BlockPosition paramBlockPosition, IBlockData paramIBlockData)
  {
    super.a(paramBlockPosition, paramIBlockData);

    double d1 = this.a * this.a + this.b * this.b;
    if ((d1 > 0.0001D) && (this.motX * this.motX + this.motZ * this.motZ > 0.001D)) {
      d1 = MathHelper.sqrt(d1);
      this.a /= d1;
      this.b /= d1;

      if (this.a * this.motX + this.b * this.motZ < 0.0D) {
        this.a = 0.0D;
        this.b = 0.0D;
      } else {
        double d2 = d1 / m();
        this.a *= d2;
        this.b *= d2;
      }
    }
  }

  protected void o()
  {
    double d1 = this.a * this.a + this.b * this.b;

    if (d1 > 0.0001D) {
      d1 = MathHelper.sqrt(d1);
      this.a /= d1;
      this.b /= d1;
      double d2 = 1.0D;
      this.motX *= 0.800000011920929D;
      this.motY *= 0.0D;
      this.motZ *= 0.800000011920929D;
      this.motX += this.a * d2;
      this.motZ += this.b * d2;
    } else {
      this.motX *= 0.9800000190734863D;
      this.motY *= 0.0D;
      this.motZ *= 0.9800000190734863D;
    }

    super.o();
  }

  public boolean e(EntityHuman paramEntityHuman)
  {
    ItemStack localItemStack = paramEntityHuman.inventory.getItemInHand();
    if ((localItemStack != null) && (localItemStack.getItem() == Items.COAL)) {
      if (!paramEntityHuman.abilities.canInstantlyBuild) if (--localItemStack.count == 0) {
          paramEntityHuman.inventory.setItem(paramEntityHuman.inventory.itemInHandIndex, null);
        }
      this.c += 3600;
    }
    this.a = (this.locX - paramEntityHuman.locX);
    this.b = (this.locZ - paramEntityHuman.locZ);

    return true;
  }

  protected void b(NBTTagCompound paramNBTTagCompound)
  {
    super.b(paramNBTTagCompound);
    paramNBTTagCompound.setDouble("PushX", this.a);
    paramNBTTagCompound.setDouble("PushZ", this.b);
    paramNBTTagCompound.setShort("Fuel", (short)this.c);
  }

  protected void a(NBTTagCompound paramNBTTagCompound)
  {
    super.a(paramNBTTagCompound);
    this.a = paramNBTTagCompound.getDouble("PushX");
    this.b = paramNBTTagCompound.getDouble("PushZ");
    this.c = paramNBTTagCompound.getShort("Fuel");
  }

  protected boolean j() {
    return (this.datawatcher.getByte(16) & 0x1) != 0;
  }

  protected void i(boolean paramBoolean) {
    if (paramBoolean)
      this.datawatcher.watch(16, Byte.valueOf((byte)(this.datawatcher.getByte(16) | 0x1)), META_BURNING, paramBoolean);
    else
      this.datawatcher.watch(16, Byte.valueOf((byte)(this.datawatcher.getByte(16) & 0xFFFFFFFE)), META_BURNING, paramBoolean);
  }

  public IBlockData u()
  {
    return (j() ? Blocks.LIT_FURNACE : Blocks.FURNACE).getBlockData().set(BlockFurnace.FACING, EnumDirection.NORTH);
  }
}