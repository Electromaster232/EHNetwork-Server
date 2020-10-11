package net.minecraft.server;

import java.util.Random;
import java.util.UUID;

public class MathHelper {
    public static final float a;
    private static final float[] b;
    private static final int[] c;
    private static final double d;
    private static final double[] e;
    private static final double[] f;

    public static float sin(float f) {
        return b[(int)(f * 10430.378f) & 65535];
    }

    public static float cos(float f) {
        return b[(int)(f * 10430.378f + 16384.0f) & 65535];
    }

    public static float c(float f) {
        return (float)Math.sqrt(f);
    }

    public static float sqrt(double d) {
        return (float)Math.sqrt(d);
    }

    public static int d(float f) {
        int n = (int)f;
        return f < (float)n ? n - 1 : n;
    }

    public static int floor(double d) {
        int n = (int)d;
        return d < (double)n ? n - 1 : n;
    }

    public static long d(double d) {
        long l = (long)d;
        return d < (double)l ? l - 1 : l;
    }

    public static float e(float f) {
        return f >= 0.0f ? f : - f;
    }

    public static int a(int n) {
        return n >= 0 ? n : - n;
    }

    public static int f(float f) {
        int n = (int)f;
        return f > (float)n ? n + 1 : n;
    }

    public static int f(double d) {
        int n = (int)d;
        return d > (double)n ? n + 1 : n;
    }

    public static int clamp(int n, int n2, int n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }

    public static float a(float f, float f2, float f3) {
        if (f < f2) {
            return f2;
        }
        if (f > f3) {
            return f3;
        }
        return f;
    }

    public static double a(double d, double d2, double d3) {
        if (d < d2) {
            return d2;
        }
        if (d > d3) {
            return d3;
        }
        return d;
    }

    public static double b(double d, double d2, double d3) {
        if (d3 < 0.0) {
            return d;
        }
        if (d3 > 1.0) {
            return d2;
        }
        return d + (d2 - d) * d3;
    }

    public static double a(double d, double d2) {
        if (d < 0.0) {
            d = - d;
        }
        if (d2 < 0.0) {
            d2 = - d2;
        }
        return d > d2 ? d : d2;
    }

    public static int nextInt(Random random, int n, int n2) {
        if (n >= n2) {
            return n;
        }
        return random.nextInt(n2 - n + 1) + n;
    }

    public static float a(Random random, float f, float f2) {
        if (f >= f2) {
            return f;
        }
        return random.nextFloat() * (f2 - f) + f;
    }

    public static double a(Random random, double d, double d2) {
        if (d >= d2) {
            return d;
        }
        return random.nextDouble() * (d2 - d) + d;
    }

    public static double a(long[] arrl) {
        long l = 0;
        for (long l2 : arrl) {
            l += l2;
        }
        return (double)l / (double)arrl.length;
    }

    public static float g(float f) {
        if ((f %= 360.0f) >= 180.0f) {
            f -= 360.0f;
        }
        if (f < -180.0f) {
            f += 360.0f;
        }
        return f;
    }

    public static double g(double d) {
        if ((d %= 360.0) >= 180.0) {
            d -= 360.0;
        }
        if (d < -180.0) {
            d += 360.0;
        }
        return d;
    }

    public static int a(String string, int n) {
        try {
            return Integer.parseInt(string);
        }
        catch (Throwable var2_2) {
            return n;
        }
    }

    public static int a(String string, int n, int n2) {
        return Math.max(n2, MathHelper.a(string, n));
    }

    public static double a(String string, double d) {
        try {
            return Double.parseDouble(string);
        }
        catch (Throwable var3_2) {
            return d;
        }
    }

    public static double a(String string, double d, double d2) {
        return Math.max(d2, MathHelper.a(string, d));
    }

    public static int b(int n) {
        int n2 = n - 1;
        n2 |= n2 >> 1;
        n2 |= n2 >> 2;
        n2 |= n2 >> 4;
        n2 |= n2 >> 8;
        n2 |= n2 >> 16;
        return n2 + 1;
    }

    private static boolean d(int n) {
        return n != 0 && (n & n - 1) == 0;
    }

    public static int e(int n) {
        n = MathHelper.d(n) ? n : MathHelper.b(n);
        return c[(int)((long)n * 125613361 >> 27) & 31];
    }

    public static int c(int n) {
        return MathHelper.e(n) - (MathHelper.d(n) ? 0 : 1);
    }

    public static int c(int n, int n2) {
        int n3;
        if (n2 == 0) {
            return 0;
        }
        if (n == 0) {
            return n2;
        }
        if (n < 0) {
            n2 *= -1;
        }
        if ((n3 = n % n2) == 0) {
            return n;
        }
        return n + n2 - n3;
    }

    public static UUID a(Random random) {
        long l = random.nextLong() & -61441 | 16384;
        long l2 = random.nextLong() & 0x3FFFFFFFFFFFFFFFL | Long.MIN_VALUE;
        return new UUID(l, l2);
    }

    public static double c(double d, double d2, double d3) {
        return (d - d2) / (d3 - d2);
    }

    public static double b(double d, double d2) {
        boolean bl;
        double d3;
        boolean bl2;
        boolean bl3;
        double d4 = d2 * d2 + d * d;
        if (Double.isNaN(d4)) {
            return Double.NaN;
        }
        boolean bl4 = bl2 = d < 0.0;
        if (bl2) {
            d = - d;
        }
        boolean bl5 = bl3 = d2 < 0.0;
        if (bl3) {
            d2 = - d2;
        }
        boolean bl6 = bl = d > d2;
        if (bl) {
            d3 = d2;
            d2 = d;
            d = d3;
        }
        d3 = MathHelper.i(d4);
        double d5 = MathHelper.d + (d *= d3);
        int n = (int)Double.doubleToRawLongBits(d5);
        double d6 = e[n];
        double d7 = f[n];
        double d8 = d5 - MathHelper.d;
        double d9 = d * d7 - (d2 *= d3) * d8;
        double d10 = (6.0 + d9 * d9) * d9 * 0.16666666666666666;
        double d11 = d6 + d10;
        if (bl) {
            d11 = 1.5707963267948966 - d11;
        }
        if (bl3) {
            d11 = 3.141592653589793 - d11;
        }
        if (bl2) {
            d11 = - d11;
        }
        return d11;
    }

    public static double i(double d) {
        double d2 = 0.5 * d;
        long l = Double.doubleToRawLongBits(d);
        l = 6910469410427058090L - (l >> 1);
        d = Double.longBitsToDouble(l);
        d *= 1.5 - d2 * d * d;
        return d;
    }

    static {
        int n;
        a = MathHelper.c(2.0f);
        b = new float[65536];
        for (n = 0; n < 65536; ++n) {
            MathHelper.b[n] = (float)Math.sin((double)n * 3.141592653589793 * 2.0 / 65536.0);
        }
        c = new int[]{0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9};
        d = Double.longBitsToDouble(4805340802404319232L);
        e = new double[257];
        f = new double[257];
        for (n = 0; n < 257; ++n) {
            double d = (double)n / 256.0;
            double d2 = Math.asin(d);
            MathHelper.f[n] = Math.cos(d2);
            MathHelper.e[n] = d2;
        }
    }
}