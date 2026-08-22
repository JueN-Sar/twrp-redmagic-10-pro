package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbabc extends zbuf implements zbvn {
    private static final zbabc zbb;
    private int zbd;
    private int zbe;
    private int zbf;
    private int zbg;
    private int zbh;
    private int zbi;
    private zbun zbj = zbuf.C();
    private zbun zbk = zbuf.C();

    static {
        zbabc zbabcVar = new zbabc();
        zbb = zbabcVar;
        zbuf.m(zbabc.class, zbabcVar);
    }

    private zbabc() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0002\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005င\u0004\u0006\u001b\u0007\u001b", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbi", "zbj", zbabb.class, "zbk", zbabb.class});
        }
        if (i3 == 3) {
            return new zbabc();
        }
        zbaad zbaadVar = null;
        if (i3 == 4) {
            return new zbaaz(zbaadVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
