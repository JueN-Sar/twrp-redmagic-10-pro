package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbgo extends zbuf implements zbvn {
    private static final zbgo zbb;
    private int zbd;
    private int zbe;
    private int zbf;
    private int zbg;
    private int zbh;

    static {
        zbgo zbgoVar = new zbgo();
        zbb = zbgoVar;
        zbuf.m(zbgo.class, zbgoVar);
    }

    private zbgo() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbgo();
        }
        zbgm zbgmVar = null;
        if (i3 == 4) {
            return new zbgn(zbgmVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
