package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbgi extends zbuf implements zbvn {
    private static final zbgi zbb;
    private int zbd;
    private float zbf;
    private boolean zbg;
    private zbxb zbi;
    private boolean zbj;
    private boolean zbk;
    private zbul zbe = zbuf.A();
    private int zbh = 1;

    static {
        zbgi zbgiVar = new zbgi();
        zbb = zbgiVar;
        zbuf.m(zbgi.class, zbgiVar);
    }

    private zbgi() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0007\u0000\u0001\u0001\b\u0007\u0000\u0001\u0000\u0001ࠞ\u0002ခ\u0000\u0003ဇ\u0001\u0004᠌\u0002\u0005ဉ\u0003\u0007ဇ\u0004\bဇ\u0005", new Object[]{"zbd", "zbe", zbge.f12800a, "zbf", "zbg", "zbh", zbgh.f12801a, "zbi", "zbj", "zbk"});
        }
        if (i3 == 3) {
            return new zbgi();
        }
        zbgf zbgfVar = null;
        if (i3 == 4) {
            return new zbgg(zbgfVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
