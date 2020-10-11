package com.mineplex.chunk;

import net.minecraft.server.IBlockData;
import net.minecraft.server.PacketDataSerializer;

public interface BlockPalette
{
	public int addGetIndex(IBlockData data);

	public IBlockData getBlock(int index);

	public void writeSerializer(PacketDataSerializer dataSerializer);

	public int getLength();
}