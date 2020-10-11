package net.minecraft.server;

public class DataIndex<T>
{
	private final int a;
	private final DataTypeInferface<T> b;

	public DataIndex(int n2, DataTypeInferface<T> kf2)
	{
		this.a = n2;
		this.b = kf2;
	}

	public int a()
	{
		return this.a;
	}

	public DataTypeInferface<T> b()
	{
		return this.b;
	}

	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (object == null || this.getClass() != object.getClass())
		{
			return false;
		}
		DataIndex ke2 = (DataIndex) object;
		return this.a == ke2.a;
	}

	public int hashCode()
	{
		return this.a;
	}
}