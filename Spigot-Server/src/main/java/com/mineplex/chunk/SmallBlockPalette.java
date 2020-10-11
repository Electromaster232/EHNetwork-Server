package com.mineplex.chunk;

import net.minecraft.server.Block;
import net.minecraft.server.IBlockData;
import net.minecraft.server.PacketDataSerializer;
import net.minecraft.server.ChunkSection;

public class SmallBlockPalette implements BlockPalette
{
	private final IBlockData[] a;
	private final ChunkSection b;
	private final int bitShift;
	private int d;

	public SmallBlockPalette(int bitShift, ChunkSection section)
	{
		this.a = new IBlockData[1 << bitShift];
		this.bitShift = bitShift;
		this.b = section;
	}

	@Override
	public int addGetIndex(IBlockData data)
	{
		int n2;
		for (n2 = 0; n2 < this.d; ++n2)
		{
			if (this.a[n2] != data)
				continue;
			return n2;
		}

		if ((n2 = this.d++) < this.a.length)
		{
			this.a[n2] = data;
			if (n2 == 16)
			{
				System.out.println("");
			}
			return n2;
		}

		return this.b.resizePalette(this.bitShift + 1, data);
	}

	@Override
	public IBlockData getBlock(int index)
	{
		if (index > 0 && index < this.d)
		{
			return this.a[index];
		}

		return null;
	}

	@Override
	public void writeSerializer(PacketDataSerializer dataSerializer)
	{
		dataSerializer.b(this.d);
		for (int i2 = 0; i2 < this.d; ++i2)
		{
			dataSerializer.b(Block.d.b(this.a[i2]));
		}
	}

	@Override
	public int getLength()
	{
		int n2 = PacketDataSerializer.a(this.d);
		for (int i2 = 0; i2 < this.d; ++i2)
		{
			n2 += PacketDataSerializer.a(Block.d.b(this.a[i2]));
		}
		return n2;
	}

}