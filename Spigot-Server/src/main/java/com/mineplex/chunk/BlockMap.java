package com.mineplex.chunk;

import net.minecraft.server.MathHelper;

import org.apache.commons.lang3.Validate;

public class BlockMap
{
	private final long[] a;
	private final int b;
	private final long c;
	private final int d;

	public BlockMap(int n2, int n3)
	{
		Validate.inclusiveBetween(1, 32, n2);
		this.d = n3;
		this.b = n2;
		this.c = (1 << n2) - 1;
		this.a = new long[MathHelper.c(n3 * n2, 64) / 64];
	}

	public void a(int n2, int n3)
	{
		Validate.inclusiveBetween(0, this.d - 1, n2);
		Validate.inclusiveBetween(0, this.c, n3);
		int n4 = n2 * this.b;
		int n5 = n4 / 64;
		int n6 = ((n2 + 1) * this.b - 1) / 64;
		int n7 = n4 % 64;
		this.a[n5] = this.a[n5] & (this.c << n7 ^ -1) | ((long) n3 & this.c) << n7;
		if (n5 != n6)
		{
			int n8 = 64 - n7;
			int n9 = this.b - n8;
			this.a[n6] = this.a[n6] >>> n9 << n9 | ((long) n3 & this.c) >> n8;
		}
	}

	public int a(int n2)
	{
		Validate.inclusiveBetween(0, this.d - 1, n2);
		int n3 = n2 * this.b;
		int n4 = n3 / 64;
		int n5 = ((n2 + 1) * this.b - 1) / 64;
		int n6 = n3 % 64;
		if (n4 == n5)
		{
			return (int) (this.a[n4] >>> n6 & this.c);
		}
		int n7 = 64 - n6;
		return (int) ((this.a[n4] >>> n6 | this.a[n5] << n7) & this.c);
	}

	public long[] a()
	{
		return this.a;
	}

	public int b()
	{
		return this.d;
	}
}