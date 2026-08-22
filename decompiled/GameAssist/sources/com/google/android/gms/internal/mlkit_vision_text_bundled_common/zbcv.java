package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbcv extends zbuf implements zbvn {
    private static final zbcv zbb;
    private int zbd;
    private zbtc zbe = zbtc.zbb;
    private float zbf;
    private zbgw zbg;
    private long zbh;

    static {
        zbcv zbcvVar = new zbcv();
        zbb = zbcvVar;
        zbuf.m(zbcv.class, zbcvVar);
    }

    private zbcv() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ည\u0000\u0002ခ\u0001\u0003ဉ\u0002\u0004ဂ\u0003", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbcv();
        }
        zbcs zbcsVar = null;
        if (i3 == 4) {
            return new zbcu(zbcsVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
