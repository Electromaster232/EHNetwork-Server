package net.minecraft.server;

public class ItemBlock extends Item
{
  protected final Block a;

  public ItemBlock(Block paramBlock)
  {
    this.a = paramBlock;
  }

  public ItemBlock b(String paramString)
  {
    super.c(paramString);
    return this;
  }

  public boolean interactWith(ItemStack paramItemStack, EntityHuman paramEntityHuman, World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    IBlockData localIBlockData1 = paramWorld.getType(paramBlockPosition);
    Block localBlock = localIBlockData1.getBlock();
    if (!localBlock.a(paramWorld, paramBlockPosition)) {
      paramBlockPosition = paramBlockPosition.shift(paramEnumDirection);
    }

    if (paramItemStack.count == 0) {
      return false;
    }
    if (!paramEntityHuman.a(paramBlockPosition, paramEnumDirection, paramItemStack)) {
      return false;
    }

    if (paramWorld.a(this.a, paramBlockPosition, false, paramEnumDirection, null, paramItemStack)) {
      int i = filterData(paramItemStack.getData());
      IBlockData localIBlockData2 = this.a.getPlacedState(paramWorld, paramBlockPosition, paramEnumDirection, paramFloat1, paramFloat2, paramFloat3, i, paramEntityHuman);
      if (paramWorld.setTypeAndData(paramBlockPosition, localIBlockData2, 3)) {
        localIBlockData2 = paramWorld.getType(paramBlockPosition);

        if (localIBlockData2.getBlock() == this.a) {
          a(paramWorld, paramEntityHuman, paramBlockPosition, paramItemStack);
          this.a.postPlace(paramWorld, paramBlockPosition, localIBlockData2, paramEntityHuman, paramItemStack);
        }

        for (IWorldAccess access : paramWorld.u)
        {
            if (access instanceof WorldManager)
            ((WorldManager) access).a(this.a.stepSound.getPlaceSound(), paramBlockPosition.getX() + 0.5F, paramBlockPosition.getY() + 0.5F, paramBlockPosition.getZ() + 0.5F, (this.a.stepSound.getVolume1() + 1.0F) / 2.0F, this.a.stepSound.getVolume2() * 0.8F, paramEntityHuman);
        }
        paramItemStack.count -= 1;
      }
      return true;
    }
    return false;
  }

  public static boolean a(World paramWorld, EntityHuman paramEntityHuman, BlockPosition paramBlockPosition, ItemStack paramItemStack) {
    MinecraftServer localMinecraftServer = MinecraftServer.getServer();
    if (localMinecraftServer == null) {
      return false;
    }
    if ((paramItemStack.hasTag()) && (paramItemStack.getTag().hasKeyOfType("BlockEntityTag", 10))) {
      TileEntity localTileEntity = paramWorld.getTileEntity(paramBlockPosition);

      if (localTileEntity != null) {
        if ((!paramWorld.isClientSide) && (localTileEntity.F()) && (!localMinecraftServer.getPlayerList().isOp(paramEntityHuman.getProfile()))) {
          return false;
        }
        NBTTagCompound localNBTTagCompound1 = new NBTTagCompound();
        NBTTagCompound localNBTTagCompound2 = (NBTTagCompound)localNBTTagCompound1.clone();
        localTileEntity.b(localNBTTagCompound1);

        NBTTagCompound localNBTTagCompound3 = (NBTTagCompound)paramItemStack.getTag().get("BlockEntityTag");
        localNBTTagCompound1.a(localNBTTagCompound3);
        localNBTTagCompound1.setInt("x", paramBlockPosition.getX());
        localNBTTagCompound1.setInt("y", paramBlockPosition.getY());
        localNBTTagCompound1.setInt("z", paramBlockPosition.getZ());

        if (!localNBTTagCompound1.equals(localNBTTagCompound2)) {
          localTileEntity.a(localNBTTagCompound1);
          localTileEntity.update();
          return true;
        }
      }
    }
    return false;
  }

  public String e_(ItemStack paramItemStack)
  {
    return this.a.a();
  }

  public String getName()
  {
    return this.a.a();
  }

  public Block d()
  {
    return this.a;
  }
}