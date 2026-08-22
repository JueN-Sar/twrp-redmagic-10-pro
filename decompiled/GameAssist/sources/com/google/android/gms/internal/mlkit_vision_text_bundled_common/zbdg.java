package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbdg extends zbuf implements zbvn {
    private static final zbdg zbb;
    private int zbd;
    private zbdd zbe;
    private zbdf zbf;
    private zbhl zbg;
    private zbhl zbh;

    static {
        zbdg zbdgVar = new zbdg();
        zbb = zbdgVar;
        zbuf.m(zbdg.class, zbdgVar);
    }

    private zbdg() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbdg();
        }
        zbda zbdaVar = null;
        if (i3 == 4) {
            return new zbdb(zbdaVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
