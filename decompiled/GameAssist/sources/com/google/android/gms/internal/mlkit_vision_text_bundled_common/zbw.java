package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbw extends zbuf implements zbvn {
    private static final zbw zbb;
    private int zbd;
    private int zbe;
    private String zbf = "";
    private float zbg;
    private float zbh;

    static {
        zbw zbwVar = new zbw();
        zbb = zbwVar;
        zbuf.m(zbw.class, zbwVar);
    }

    private zbw() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001\u0003ခ\u0002\u0004ခ\u0003", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbw();
        }
        zbu zbuVar = null;
        if (i3 == 4) {
            return new zbv(zbuVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
