package com.mineplex.chunk;

import java.util.Iterator;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;

public class PaletteMediumList<T> implements Iterable<T>
{
	private static final Object a = null;
	private T[] b;
	private int[] c;
	private T[] d;
	private int e;
	private int f;

	public PaletteMediumList(int n2)
	{
		n2 = (int) ((float) n2 / 0.8f);
		this.b = (T[]) new Object[n2];
		this.c = new int[n2];
		this.d = (T[]) new Object[n2];
	}

	public int a(T k2)
	{
		return this.c(this.b(k2, this.d(k2)));
	}

	public T get(int n2)
	{
		if (n2 < 0 || n2 >= this.d.length)
		{
			return null;
		}
		return this.d[n2];
	}

	private int c(int n2)
	{
		if (n2 == -1)
		{
			return -1;
		}
		return this.c[n2];
	}

	public int c(T k2)
	{
		int n2 = this.c();
		this.a(k2, n2);
		return n2;
	}

	private int c()
	{
		while (this.e < this.d.length && this.d[this.e] != null)
		{
			++this.e;
		}
		return this.e++;
	}

	private void d(int n2)
	{
		T[] arrK = this.b;
		int[] arrn = this.c;
		this.b = (T[]) new Object[n2];
		this.c = new int[n2];
		this.d = (T[]) new Object[n2];
		this.e = 0;
		this.f = 0;
		for (int i2 = 0; i2 < arrK.length; ++i2)
		{
			if (arrK[i2] == null)
				continue;
			this.a(arrK[i2], arrn[i2]);
		}
	}

	public void a(T k2, int n2)
	{
		int n3;
		int n4;
		if ((float) (n4 = Math.max(n2, ++this.f)) >= (float) this.b.length * 0.8f)
		{
			for (n3 = this.b.length << 1; n3 < n2; n3 <<= 1)
			{
			}
			this.d(n3);
		}
		n3 = this.e(this.d(k2));
		this.b[n3] = k2;
		this.c[n3] = n2;
		this.d[n2] = k2;
	}

	public static int math(int n2)
	{
		n2 ^= n2 >>> 16;
		n2 *= -2048144789;
		n2 ^= n2 >>> 13;
		n2 *= -1028477387;
		n2 ^= n2 >>> 16;
		return n2;
	}

	private int d(T k2)
	{
		return (math(System.identityHashCode(k2)) & Integer.MAX_VALUE) % this.b.length;
	}

	private int b(T k2, int n2)
	{
		int n3;
		for (n3 = n2; n3 < this.b.length; ++n3)
		{
			if (this.b[n3] == k2)
			{
				return n3;
			}
			if (this.b[n3] != a)
				continue;
			return -1;
		}
		for (n3 = 0; n3 < n2; ++n3)
		{
			if (this.b[n3] == k2)
			{
				return n3;
			}
			if (this.b[n3] != a)
				continue;
			return -1;
		}
		return -1;
	}

	private int e(int n2)
	{
		int n3;
		StringBuilder stringBuilder = new StringBuilder("");
		for (n3 = n2; n3 < this.b.length; ++n3)
		{
			if (this.b[n3] == a)
			{
				return n3;
			}
			stringBuilder.append(n3).append(' ');
		}
		for (n3 = 0; n3 < n2; ++n3)
		{
			if (this.b[n3] == a)
			{
				return n3;
			}
			stringBuilder.append(n3).append(' ');
		}
		throw new RuntimeException("Overflowed :(");
	}

	public Iterator<T> iterator()
	{
		return Iterators.filter(Iterators.forArray(this.d), Predicates.notNull());
	}

	public int b()
	{
		return this.f + 1;
	}
}