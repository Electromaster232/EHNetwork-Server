package com.mineplex.chunk;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import net.minecraft.server.Block;
import net.minecraft.server.IBlockData;
import net.minecraft.server.PacketDataSerializer;
import net.minecraft.server.ChunkSection;

public class MediumBlockPalette implements BlockPalette
{
	private PaletteMediumList<IBlockData> a;
	private ChunkSection b;
	private int bitShift;

	public MediumBlockPalette(int bitShift, ChunkSection section)
	{
		this.bitShift = bitShift;
		b = section;
		this.a = new PaletteMediumList<IBlockData>(1 << bitShift);
	}

	@Override
	public int addGetIndex(IBlockData data)
	{
		int n2 = this.a.a(data);
		if (n2 == -1 && (n2 = this.a.c(data)) >= 1 << this.bitShift)
		{
			n2 = this.b.resizePalette(this.bitShift + 1, data);
		}
		return n2;
	}

	@Override
	public IBlockData getBlock(int index)
	{
		return a.get(index);
	}

	@Override
	public void writeSerializer(PacketDataSerializer dataSerializer)
	{
		int n2 = this.a.b();
		dataSerializer.b(n2);
		for (int i2 = 0; i2 < n2; ++i2)
		{
			dataSerializer.b(Block.d.b(this.a.get(i2)));
		}
	}

	@Override
	public int getLength()
	{
		int n2 = PacketDataSerializer.a(this.a.b());
		for (int i2 = 0; i2 < this.a.b(); ++i2)
		{
			n2 += PacketDataSerializer.a(Block.d.b(this.a.get(i2)));
		}
		return n2;
	}

}