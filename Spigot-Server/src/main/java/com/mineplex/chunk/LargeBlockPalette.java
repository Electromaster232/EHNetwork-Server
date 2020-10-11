package com.mineplex.chunk;

import net.minecraft.server.Block;
import net.minecraft.server.Blocks;
import net.minecraft.server.IBlockData;
import net.minecraft.server.PacketDataSerializer;

public class LargeBlockPalette implements BlockPalette
{

	// Get index
	@Override
	public int addGetIndex(IBlockData aqt2)
	{
		int n2 = Block.d.b(aqt2);
		return n2 == -1 ? 0 : n2;
	}

	// Get from index
	@Override
	public IBlockData getBlock(int index)
	{
		IBlockData aqt2 = (IBlockData) Block.d.a(index);
		return aqt2 == null ? Blocks.AIR.getBlockData() : aqt2;
	}

	@Override
	public void writeSerializer(PacketDataSerializer em2)
	{
		em2.b(0);
	}

	@Override
	public int getLength()
	{
		return PacketDataSerializer.a(0);
	}

}