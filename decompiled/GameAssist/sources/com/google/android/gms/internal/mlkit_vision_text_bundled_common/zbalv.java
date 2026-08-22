package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbalv extends zbuf implements zbvn {
    private static final zbalv zbb;
    private int zbd;
    private float zbg;
    private String zbe = "en";
    private int zbf = -1;
    private zbun zbh = zbuf.C();
    private zbun zbi = zbuf.C();

    static {
        zbalv zbalvVar = new zbalv();
        zbb = zbalvVar;
        zbuf.m(zbalv.class, zbalvVar);
    }

    private zbalv() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001ဈ\u0000\u0002င\u0001\u0003ခ\u0002\u0004\u001a\u0005\u001a", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbi"});
        }
        if (i3 == 3) {
            return new zbalv();
        }
        zbalt zbaltVar = null;
        if (i3 == 4) {
            return new zbalu(zbaltVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
