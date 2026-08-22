package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbahv extends zbuf implements zbvn {
    private static final zbahv zbb;
    private int zbd = 0;
    private Object zbe;
    private float zbf;

    static {
        zbahv zbahvVar = new zbahv();
        zbb = zbahvVar;
        zbuf.m(zbahv.class, zbahvVar);
    }

    private zbahv() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0004\u0001\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001?\u0000\u0002Ȼ\u0000\u0003\u0001\u0004<\u0000", new Object[]{"zbe", "zbd", "zbf", zbahx.class});
        }
        if (i3 == 3) {
            return new zbahv();
        }
        zbagx zbagxVar = null;
        if (i3 == 4) {
            return new zbahu(zbagxVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
