package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PotionBrewer {
    public static final String a;
    public static final String b;
    public static final String c;
    public static final String d;
    public static final String e;
    public static final String f;
    public static final String g;
    public static final String h;
    public static final String i;
    public static final String j;
    public static final String k;
    public static final String l;
    public static final String m;
    public static final String n;
    private static final Map<Integer, String> effectDurations;
    private static final Map<Integer, String> effectAmplifiers;
    private static final Map<Integer, Integer> q;
    private static final String[] appearances;

    public static boolean a(int n, int n2) {
        return (n & 1 << n2) != 0;
    }

    private static int c(int n, int n2) {
        return PotionBrewer.a(n, n2) ? 1 : 0;
    }

    private static int d(int n, int n2) {
        return PotionBrewer.a(n, n2) ? 0 : 1;
    }

    public static int a(int n) {
        return PotionBrewer.a(n, 5, 4, 3, 2, 1);
    }

    public static int a(Collection<MobEffect> collection) {
        int n = 3694022;
        if (collection == null || collection.isEmpty()) {
            return n;
        }
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        for (MobEffect mobEffect : collection) {
            if (!mobEffect.isShowParticles()) continue;
            int n2 = MobEffectList.byId[mobEffect.getEffectId()].k();
            for (int i = 0; i <= mobEffect.getAmplifier(); ++i) {
                f += (float)(n2 >> 16 & 255) / 255.0f;
                f2 += (float)(n2 >> 8 & 255) / 255.0f;
                f3 += (float)(n2 >> 0 & 255) / 255.0f;
                f4 += 1.0f;
            }
        }
        if (f4 == 0.0f) {
            return 0;
        }
        f = f / f4 * 255.0f;
        f2 = f2 / f4 * 255.0f;
        f3 = f3 / f4 * 255.0f;
        return (int)f << 16 | (int)f2 << 8 | (int)f3;
    }

    public static boolean b(Collection<MobEffect> collection) {
        for (MobEffect mobEffect : collection) {
            if (mobEffect.isAmbient()) continue;
            return false;
        }
        return true;
    }

    public static String c(int n) {
        int n2 = PotionBrewer.a(n);
        return appearances[n2];
    }

    private static int a(boolean bl, boolean bl2, boolean bl3, int n, int n2, int n3, int n4) {
        int n5 = 0;
        if (bl) {
            n5 = PotionBrewer.d(n4, n2);
        } else if (n != -1) {
            if (n == 0 && PotionBrewer.h(n4) == n2) {
                n5 = 1;
            } else if (n == 1 && PotionBrewer.h(n4) > n2) {
                n5 = 1;
            } else if (n == 2 && PotionBrewer.h(n4) < n2) {
                n5 = 1;
            }
        } else {
            n5 = PotionBrewer.c(n4, n2);
        }
        if (bl2) {
            n5 *= n3;
        }
        if (bl3) {
            n5 *= -1;
        }
        return n5;
   }

    private static int h(int n) {
        int n2 = 0;
        while (n > 0) {
            n &= n - 1;
            ++n2;
        }
        return n2;
    }

    private static int a(String string, int n, int n2, int n3) {
        if (n >= string.length() || n2 < 0 || n >= n2) {
            return 0;
        }
        int n4 = string.indexOf(124, n);
        if (n4 >= 0 && n4 < n2) {
            int n5 = PotionBrewer.a(string, n, n4 - 1, n3);
            if (n5 > 0) {
                return n5;
            }
            int n6 = PotionBrewer.a(string, n4 + 1, n2, n3);
            if (n6 > 0) {
                return n6;
            }
            return 0;
        }
        int n7 = string.indexOf(38, n);
        if (n7 >= 0 && n7 < n2) {
            int n8 = PotionBrewer.a(string, n, n7 - 1, n3);
            if (n8 <= 0) {
                return 0;
            }
            int n9 = PotionBrewer.a(string, n7 + 1, n2, n3);
            if (n9 <= 0) {
                return 0;
            }
            if (n8 > n9) {
                return n8;
            }
            return n9;
        }
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        boolean bl4 = false;
        boolean bl5 = false;
        int n10 = -1;
        int n11 = 0;
        int n12 = 0;
        int n13 = 0;
        for (int i = n; i < n2; ++i) {
            char c = string.charAt(i);
            if (c >= '0' && c <= '9') {
                if (bl) {
                    n12 = c - 48;
                    bl2 = true;
                    continue;
                }
                n11 *= 10;
                n11 += c - 48;
                bl3 = true;
                continue;
            }
            if (c == '*') {
                bl = true;
                continue;
            }
            if (c == '!') {
                if (bl3) {
                    n13 += PotionBrewer.a(bl4, bl2, bl5, n10, n11, n12, n3);
                    bl4 = false;
                    bl5 = false;
                    bl = false;
                    bl2 = false;
                    bl3 = false;
                    n12 = 0;
                    n11 = 0;
                    n10 = -1;
                }
                bl4 = true;
                continue;
            }
            if (c == '-') {
                if (bl3) {
                    n13 += PotionBrewer.a(bl4, bl2, bl5, n10, n11, n12, n3);
                    bl4 = false;
                    bl5 = false;
                    bl = false;
                    bl2 = false;
                    bl3 = false;
                    n12 = 0;
                    n11 = 0;
                    n10 = -1;
                }
                bl5 = true;
                continue;
            }
            if (c == '=' || c == '<' || c == '>') {
                if (bl3) {
                    n13 += PotionBrewer.a(bl4, bl2, bl5, n10, n11, n12, n3);
                    bl4 = false;
                    bl5 = false;
                    bl = false;
                    bl2 = false;
                    bl3 = false;
                    n12 = 0;
                    n11 = 0;
                    n10 = -1;
                }
                if (c == '=') {
                    n10 = 0;
                    continue;
                }
                if (c == '<') {
                    n10 = 2;
                    continue;
                }
                if (c != '>') continue;
                n10 = 1;
                continue;
            }
            if (c != '+' || !bl3) continue;
            n13 += PotionBrewer.a(bl4, bl2, bl5, n10, n11, n12, n3);
            bl4 = false;
            bl5 = false;
            bl = false;
            bl2 = false;
            bl3 = false;
            n12 = 0;
            n11 = 0;
            n10 = -1;
       }
        if (bl3) {
            n13 += PotionBrewer.a(bl4, bl2, bl5, n10, n11, n12, n3);
        }
        return n13;
    }

    public static List<MobEffect> getEffects(int n, boolean bl) {
        ArrayList arrayList = null;
        for (MobEffectList mobEffectList : MobEffectList.byId) {
           int n2;
            String string;
            if (mobEffectList == null || mobEffectList.j() && !bl || (string = effectDurations.get(mobEffectList.getId())) == null || (n2 = PotionBrewer.a(string, 0, string.length(), n)) <= 0) continue;
            int n3 = 0;
            String string2 = effectAmplifiers.get(mobEffectList.getId());
            if (string2 != null && (n3 = PotionBrewer.a(string2, 0, string2.length(), n)) < 0) {
                n3 = 0;
            }
            if (mobEffectList.isInstant()) {
                n2 = 1;
            } else {
                n2 = 1200 * (n2 * 3 + (n2 - 1) * 2);
                n2 >>= n3;
                n2 = (int)Math.round((double)n2 * mobEffectList.getDurationModifier());
                if ((n & 16384) != 0) {
                    n2 = (int)Math.round((double)n2 * 0.75 + 0.5);
                }
            }
            if (arrayList == null) {
                arrayList = Lists.newArrayList();
            }
            MobEffect mobEffect = new MobEffect(mobEffectList.getId(), n2, n3);
            if ((n & 16384) != 0) {
                mobEffect.setSplash(true);
            }
            arrayList.add(mobEffect);
        }
        return arrayList;
    }

    private static int a(int n, int n2, boolean bl, boolean bl2, boolean bl3) {
        if (bl3) {
            if (!PotionBrewer.a(n, n2)) {
                return 0;
            }
        } else {
            n = bl ? (n &= ~ (1 << n2)) : (bl2 ? ((n & 1 << n2) == 0 ? (n |= 1 << n2) : (n &= ~ (1 << n2))) : (n |= 1 << n2));
        }
        return n;
    }

    public static int a(int n, String string) {
        int n2 = 0;
        int n3 = string.length();
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        boolean bl4 = false;
        int n4 = 0;
        for (int i = n2; i < n3; ++i) {
            char c = string.charAt(i);
            if (c >= '0' && c <= '9') {
                n4 *= 10;
                n4 += c - 48;
                bl = true;
                continue;
            }
            if (c == '!') {
                if (bl) {
                    n = PotionBrewer.a(n, n4, bl3, bl2, bl4);
                    bl4 = false;
                    bl2 = false;
                    bl3 = false;
                    bl = false;
                    n4 = 0;
                }
                bl2 = true;
                continue;
            }
            if (c == '-') {
                if (bl) {
                    n = PotionBrewer.a(n, n4, bl3, bl2, bl4);
                    bl4 = false;
                    bl2 = false;
                    bl3 = false;
                    bl = false;
                    n4 = 0;
               }
                bl3 = true;
                continue;
            }
            if (c == '+') {
                if (!bl) continue;
                n = PotionBrewer.a(n, n4, bl3, bl2, bl4);
                bl4 = false;
                bl2 = false;
                bl3 = false;
                bl = false;
                n4 = 0;
                continue;
            }
            if (c != '&') continue;
            if (bl) {
                n = PotionBrewer.a(n, n4, bl3, bl2, bl4);
                bl4 = false;
                bl2 = false;
                bl3 = false;
                bl = false;
                n4 = 0;
            }
            bl4 = true;
        }
        if (bl) {
            n = PotionBrewer.a(n, n4, bl3, bl2, bl4);
        }
        return n & 32767;
    }

    public static int a(int n, int n2, int n3, int n4, int n5, int n6) {
        return (PotionBrewer.a(n, n2) ? 16 : 0) | (PotionBrewer.a(n, n3) ? 8 : 0) | (PotionBrewer.a(n, n4) ? 4 : 0) | (PotionBrewer.a(n, n5) ? 2 : 0) | (PotionBrewer.a(n, n6) ? 1 : 0);
    }

    static {
        effectDurations = Maps.newHashMap();
        effectAmplifiers = Maps.newHashMap();
        a = null;
        c = "+0-1-2-3&4-4+13";
        effectDurations.put(MobEffectList.REGENERATION.getId(), "0 & !1 & !2 & !3 & 0+6");
        b = "-0+1-2-3&4-4+13";
        effectDurations.put(MobEffectList.FASTER_MOVEMENT.getId(), "!0 & 1 & !2 & !3 & 1+6");
        h = "+0+1-2-3&4-4+13";
        effectDurations.put(MobEffectList.FIRE_RESISTANCE.getId(), "0 & 1 & !2 & !3 & 0+6");
        f = "+0-1+2-3&4-4+13";
        effectDurations.put(MobEffectList.HEAL.getId(), "0 & !1 & 2 & !3");
        d = "-0-1+2-3&4-4+13";
        effectDurations.put(MobEffectList.POISON.getId(), "!0 & !1 & 2 & !3 & 2+6");
        e = "-0+3-4+13";
        effectDurations.put(MobEffectList.WEAKNESS.getId(), "!0 & !1 & !2 & 3 & 3+6");
        effectDurations.put(MobEffectList.HARM.getId(), "!0 & !1 & 2 & 3");
        effectDurations.put(MobEffectList.SLOWER_MOVEMENT.getId(), "!0 & 1 & !2 & 3 & 3+6");
        g = "+0-1-2+3&4-4+13";
        effectDurations.put(MobEffectList.INCREASE_DAMAGE.getId(), "0 & !1 & !2 & 3 & 3+6");
        l = "-0+1+2-3+13&4-4";
        effectDurations.put(MobEffectList.NIGHT_VISION.getId(), "!0 & 1 & 2 & !3 & 2+6");
        effectDurations.put(MobEffectList.INVISIBILITY.getId(), "!0 & 1 & 2 & 3 & 2+6");
        m = "+0-1+2+3+13&4-4";
        effectDurations.put(MobEffectList.WATER_BREATHING.getId(), "0 & !1 & 2 & 3 & 2+6");
        n = "+0+1-2+3&4-4+13";
        effectDurations.put(MobEffectList.JUMP.getId(), "0 & 1 & !2 & 3 & 3+6");
        effectDurations.put(MobEffectList.LEVITATION.getId(), "0 & 1 & !2 & 3 & 3+6");
        j = "+5-6-7";
        effectAmplifiers.put(MobEffectList.FASTER_MOVEMENT.getId(), "5");
        effectAmplifiers.put(MobEffectList.FASTER_DIG.getId(), "5");
        effectAmplifiers.put(MobEffectList.INCREASE_DAMAGE.getId(), "5");
        effectAmplifiers.put(MobEffectList.REGENERATION.getId(), "5");
        effectAmplifiers.put(MobEffectList.HARM.getId(), "5");
        effectAmplifiers.put(MobEffectList.HEAL.getId(), "5");
        effectAmplifiers.put(MobEffectList.RESISTANCE.getId(), "5");
        effectAmplifiers.put(MobEffectList.POISON.getId(), "5");
        effectAmplifiers.put(MobEffectList.JUMP.getId(), "5");
        i = "-5+6-7";
        k = "+14&13-13";
        q = Maps.newHashMap();
        appearances = new String[]{"potion.prefix.mundane", "potion.prefix.uninteresting", "potion.prefix.bland", "potion.prefix.clear", "potion.prefix.milky", "potion.prefix.diffuse", "potion.prefix.artless", "potion.prefix.thin", "potion.prefix.awkward", "potion.prefix.flat", "potion.prefix.bulky", "potion.prefix.bungling", "potion.prefix.buttered", "potion.prefix.smooth", "potion.prefix.suave", "potion.prefix.debonair", "potion.prefix.thick", "potion.prefix.elegant", "potion.prefix.fancy", "potion.prefix.charming", "potion.prefix.dashing", "potion.prefix.refined", "potion.prefix.cordial", "potion.prefix.sparkling", "potion.prefix.potent", "potion.prefix.foul", "potion.prefix.odorless", "potion.prefix.rank", "potion.prefix.harsh", "potion.prefix.acrid", "potion.prefix.gross", "potion.prefix.stinky"};
    }
}