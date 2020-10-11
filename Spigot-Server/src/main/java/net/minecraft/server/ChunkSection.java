package net.minecraft.server;

import java.util.ArrayList;
import org.apache.commons.lang3.Validate;
import org.bukkit.craftbukkit.util.CraftMagicNumbers;
import com.mineplex.chunk.*;

public class ChunkSection {

    private int yPos;
    private int nonEmptyBlockCount;
    private int tickingBlockCount;
    private char[] blockIds;
    private NibbleArray emittedLight;
    private NibbleArray skyLight;
    private static LargeBlockPalette largePalette = new LargeBlockPalette();
	private BlockMap blockMap;
	private int bitshift;
	private BlockPalette blockPalette;

    public ChunkSection(int i, boolean flag) {
        this.yPos = i;
        this.blockIds = new char[4096];
        this.emittedLight = new NibbleArray();
        if (flag) {
            this.skyLight = new NibbleArray();
        }

        setSize(4);
    }

    // CraftBukkit start
    public ChunkSection(int y, boolean flag, char[] blockIds) {
        this.yPos = y;
        this.blockIds = blockIds;
        this.emittedLight = new NibbleArray();
        if (flag) {
            this.skyLight = new NibbleArray();
        }
        setSize(4);
        recalcBlockCounts();
    }
    // CraftBukkit end

    public void setSize(int newBitShift)
	{
		if (newBitShift == this.bitshift)
		{
			return;
		}
		this.bitshift = newBitShift;
		if (this.bitshift <= 4)
		{
			this.bitshift = 4;
			this.blockPalette = new SmallBlockPalette(this.bitshift, this);
		}
		else if (this.bitshift <= 8)
		{
			this.blockPalette = new MediumBlockPalette(this.bitshift, this);
		}
		else
		{
			this.blockPalette = largePalette;
			this.bitshift = MathHelper.e(Block.d.a());
		}
		this.blockPalette.addGetIndex(Blocks.AIR.getBlockData());
		this.blockMap = new BlockMap(this.bitshift, 4096);
	}

	public int resizePalette(int newBitShift, IBlockData aqt2)
	{
		BlockMap blockMap = this.blockMap;
		BlockPalette blockPalette = this.blockPalette;
		this.setSize(newBitShift);
		for (int i2 = 0; i2 < blockMap.b(); ++i2)
		{
			IBlockData aqt3 = blockPalette.getBlock(blockMap.a(i2));
			if (aqt3 == null)
				continue;

			// TODO Set data
			this.setData(i2, aqt3);
		}
		// Return index
		return this.blockPalette.addGetIndex(aqt2);
	}

	public void writeSerializer(PacketDataSerializer em2)
	{
		em2.writeByte(this.bitshift);
		this.blockPalette.writeSerializer(em2);
		em2.b(blockMap.a().length);
		for (long l : blockMap.a())
		{
			em2.writeLong(l);
		}
	}

	// Get dataserializer size
	public int getLength()
	{
		int n2 = this.blockMap.b();
		return 1 + this.blockPalette.getLength() + PacketDataSerializer.a(n2) + n2 * 8;
	}

    public IBlockData getType(int i, int j, int k) {
        IBlockData iblockdata = (IBlockData) Block.d.a(this.blockIds[j << 8 | k << 4 | i]);

        return iblockdata != null ? iblockdata : Blocks.AIR.getBlockData();
    }

    public void setType(int i, int j, int k, IBlockData iblockdata) {
        IBlockData iblockdata1 = this.getType(i, j, k);
        Block block = iblockdata1.getBlock();
        Block block1 = iblockdata.getBlock();

        if (block != Blocks.AIR) {
            --this.nonEmptyBlockCount;
            if (block.isTicking()) {
                --this.tickingBlockCount;
            }
        }

        if (block1 != Blocks.AIR) {
            ++this.nonEmptyBlockCount;
            if (block1.isTicking()) {
                ++this.tickingBlockCount;
            }
        }

        this.blockIds[j << 8 | k << 4 | i] = (char) Block.d.b(iblockdata);
        setData(i, j, k, iblockdata);
    }

    public void setData(int x, int y, int z, IBlockData blockData)
    {
        int bitshift = y << 8 | z << 4 | x;
        setData(bitshift, blockData);
    }

    // Set block bitshift, iblockdata
	protected void setData(int n2, IBlockData aqt2)
	{
		int n3 = this.blockPalette.addGetIndex(aqt2);
		this.blockMap.a(n2, n3);
	}

    public Block b(int i, int j, int k) {
        return this.getType(i, j, k).getBlock();
    }

    public int c(int i, int j, int k) {
        IBlockData iblockdata = this.getType(i, j, k);

        return iblockdata.getBlock().toLegacyData(iblockdata);
    }

    public boolean a() {
        return this.nonEmptyBlockCount == 0;
    }

    public boolean shouldTick() {
        return this.tickingBlockCount > 0;
    }

    public int getYPosition() {
        return this.yPos;
    }

    public void a(int i, int j, int k, int l) {
        this.skyLight.a(i, j, k, l);
    }

    public int d(int i, int j, int k) {
        return this.skyLight.a(i, j, k);
    }

    public void b(int i, int j, int k, int l) {
        this.emittedLight.a(i, j, k, l);
    }

    public int e(int i, int j, int k) {
        return this.emittedLight.a(i, j, k);
    }

    public void recalcBlockCounts() {
        this.nonEmptyBlockCount = 0;
        this.tickingBlockCount = 0;
        setData();

        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                for (int k = 0; k < 16; ++k) {
                    Block block = this.b(i, j, k);

                    if (block != Blocks.AIR) {
                        ++this.nonEmptyBlockCount;
                        if (block.isTicking()) {
                            ++this.tickingBlockCount;
                        }
                    }
                }
            }
        }

    }

    public char[] getIdArray() {
        return this.blockIds;
    }
    private void setData()
    {
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                for (int k = 0; k < 16; ++k) {
                    setData(i, j, k, getType(i, j, k));
                }
            }
        }
    }

    public void a(char[] achar) {
        this.blockIds = achar;
        setData();
    }

    public NibbleArray getEmittedLightArray() {
        return this.emittedLight;
    }

    public NibbleArray getSkyLightArray() {
        return this.skyLight;
    }

    public void a(NibbleArray nibblearray) {
        this.emittedLight = nibblearray;
    }

    public void b(NibbleArray nibblearray) {
        this.skyLight = nibblearray;
    }
}
