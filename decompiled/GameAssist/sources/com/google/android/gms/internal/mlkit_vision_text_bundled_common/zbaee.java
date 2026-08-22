package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaee extends zbuf implements zbvn {
    private static final zbaee zbb;
    private int zbd;
    private zbun zbe = zbuf.C();
    private zbaed zbf;
    private float zbg;
    private int zbh;
    private boolean zbi;
    private boolean zbj;

    static {
        zbaee zbaeeVar = new zbaee();
        zbb = zbaeeVar;
        zbuf.m(zbaee.class, zbaeeVar);
        zbuf.v(zbadz.H(), zbaeeVar, zbaeeVar, null, 32149011, zbww.zbk, zbaee.class);
    }

    private zbaee() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001\u001b\u0002ဇ\u0003\u0003ဉ\u0000\u0004ခ\u0001\u0005ဇ\u0004\u0006᠌\u0002", new Object[]{"zbd", "zbe", zbaed.class, "zbi", "zbf", "zbg", "zbj", "zbh", zbaeb.f12672a});
        }
        if (i3 == 3) {
            return new zbaee();
        }
        zbadn zbadnVar = null;
        if (i3 == 4) {
            return new zbaea(zbadnVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
