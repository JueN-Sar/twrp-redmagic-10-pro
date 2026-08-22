package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbahb extends zbuf implements zbvn {
    private static final zbahb zbb;
    private Object zbe;
    private int zbf;
    private int zbg;
    private int zbh;
    private int zbd = 0;
    private zbun zbi = zbuf.C();

    static {
        zbahb zbahbVar = new zbahb();
        zbb = zbahbVar;
        zbuf.m(zbahb.class, zbahbVar);
    }

    private zbahb() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0006\u0001\u0000\u0001\u0006\u0006\u0000\u0001\u0000\u0001\f\u0002<\u0000\u0003\u0004\u0004\u001b\u00057\u0000\u0006\u0004", new Object[]{"zbe", "zbd", "zbf", zbaha.class, "zbg", "zbi", zbsp.class, "zbh"});
        }
        if (i3 == 3) {
            return new zbahb();
        }
        zbagx zbagxVar = null;
        if (i3 == 4) {
            return new zbagy(zbagxVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
