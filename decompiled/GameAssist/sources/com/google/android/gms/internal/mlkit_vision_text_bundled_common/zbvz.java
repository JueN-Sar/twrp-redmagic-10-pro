package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.List;

/* loaded from: classes.dex */
final class zbvz {

    /* renamed from: a, reason: collision with root package name */
    private static final zbwl f13033a;

    /* renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f13034b = 0;

    static {
        int i2 = zbvu.f13023d;
        f13033a = new zbwn();
    }

    public static void A(int i2, List list, zbwy zbwyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zbwyVar.E(i2, list, z);
    }

    public static void B(int i2, List list, zbwy zbwyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zbwyVar.u(i2, list, z);
    }

    public static void C(int i2, List list, zbwy zbwyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zbwyVar.A(i2, list, z);
    }

    public static void D(int i2, List list, zbwy zbwyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zbwyVar.q(i2, list, z);
    }

    public static void E(int i2, List list, zbwy zbwyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zbwyVar.z(i2, list, z);
    }

    public static void a(int i2, List list, zbwy zbwyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zbwyVar.j(i2, list, z);
    }

    public static void b(int i2, List list, zbwy zbwyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zbwyVar.h(i2, list, z);
    }

    public static void c(int i2, List list, zbwy zbwyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zbwyVar.g(i2, list, z);
    }

    public static void d(int i2, List list, zbwy zbwyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zbwyVar.H(i2, list, z);
    }

    static boolean e(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static int f(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zbug) {
            zbug zbugVar = (zbug) list;
            i2 = 0;
            while (i3 < size) {
                i2 += zbtk.e(zbugVar.d(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += zbtk.e(((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
        return i2;
    }

    static int g(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zbtk.d(i2 << 3) + 4);
    }

    static int h(List list) {
        return list.size() * 4;
    }

    static int i(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zbtk.d(i2 << 3) + 8);
    }

    static int j(List list) {
        return list.size() * 8;
    }

    static int k(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zbug) {
            zbug zbugVar = (zbug) list;
            i2 = 0;
            while (i3 < size) {
                i2 += zbtk.e(zbugVar.d(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += zbtk.e(((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
        return i2;
    }

    static int l(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zbva) {
            zbva zbvaVar = (zbva) list;
            i2 = 0;
            while (i3 < size) {
                i2 += zbtk.e(zbvaVar.d(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += zbtk.e(((Long) list.get(i3)).longValue());
                i3++;
            }
        }
        return i2;
    }

    static int m(int i2, Object obj, zbvx zbvxVar) {
        int i3 = i2 << 3;
        if (!(obj instanceof zbuw)) {
            return zbtk.d(i3) + zbtk.b((zbvm) obj, zbvxVar);
        }
        int d2 = zbtk.d(i3);
        int a2 = ((zbuw) obj).a();
        return d2 + zbtk.d(a2) + a2;
    }

    static int n(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zbug) {
            zbug zbugVar = (zbug) list;
            i2 = 0;
            while (i3 < size) {
                int d2 = zbugVar.d(i3);
                i2 += zbtk.d((d2 >> 31) ^ (d2 + d2));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                int intValue = ((Integer) list.get(i3)).intValue();
                i2 += zbtk.d((intValue >> 31) ^ (intValue + intValue));
                i3++;
            }
        }
        return i2;
    }

    static int o(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zbva) {
            zbva zbvaVar = (zbva) list;
            i2 = 0;
            while (i3 < size) {
                long d2 = zbvaVar.d(i3);
                i2 += zbtk.e((d2 >> 63) ^ (d2 + d2));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                long longValue = ((Long) list.get(i3)).longValue();
                i2 += zbtk.e((longValue >> 63) ^ (longValue + longValue));
                i3++;
            }
        }
        return i2;
    }

    static int p(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zbug) {
            zbug zbugVar = (zbug) list;
            i2 = 0;
            while (i3 < size) {
                i2 += zbtk.d(zbugVar.d(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += zbtk.d(((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
        return i2;
    }

    static int q(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zbva) {
            zbva zbvaVar = (zbva) list;
            i2 = 0;
            while (i3 < size) {
                i2 += zbtk.e(zbvaVar.d(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += zbtk.e(((Long) list.get(i3)).longValue());
                i3++;
            }
        }
        return i2;
    }

    public static zbwl r() {
        return f13033a;
    }

    static Object s(Object obj, int i2, int i3, Object obj2, zbwl zbwlVar) {
        if (obj2 == null) {
            obj2 = zbwlVar.a(obj);
        }
        ((zbwm) obj2).j(i2 << 3, Long.valueOf(i3));
        return obj2;
    }

    static void t(zbtq zbtqVar, Object obj, Object obj2) {
        zbtu zbtuVar = ((zbub) obj2).zbb;
        if (zbtuVar.f12967a.isEmpty()) {
            return;
        }
        ((zbub) obj).E().i(zbtuVar);
    }

    static void u(zbwl zbwlVar, Object obj, Object obj2) {
        zbuf zbufVar = (zbuf) obj;
        zbwm zbwmVar = zbufVar.zbc;
        zbwm zbwmVar2 = ((zbuf) obj2).zbc;
        if (!zbwm.c().equals(zbwmVar2)) {
            if (zbwm.c().equals(zbwmVar)) {
                zbwmVar = zbwm.e(zbwmVar, zbwmVar2);
            } else {
                zbwmVar.d(zbwmVar2);
            }
        }
        zbufVar.zbc = zbwmVar;
    }

    public static void v(int i2, List list, zbwy zbwyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zbwyVar.s(i2, list, z);
    }

    public static void w(int i2, List list, zbwy zbwyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zbwyVar.k(i2, list, z);
    }

    public static void x(int i2, List list, zbwy zbwyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zbwyVar.o(i2, list, z);
    }

    public static void y(int i2, List list, zbwy zbwyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zbwyVar.b(i2, list, z);
    }

    public static void z(int i2, List list, zbwy zbwyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zbwyVar.e(i2, list, z);
    }
}
