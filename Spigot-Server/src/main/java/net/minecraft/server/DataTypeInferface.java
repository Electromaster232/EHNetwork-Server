package net.minecraft.server;

public interface DataTypeInferface<T>
{
	public void write(PacketDataSerializer var1, T var2);

	public DataIndex<T> fromIndex(int var1);
}