package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbfl extends zbuf implements zbvn {
    private static final zbfl zbb;
    private int zbd;
    private float zbe;
    private boolean zbf;
    private zbtc zbg = zbtc.zbb;

    static {
        zbfl zbflVar = new zbfl();
        zbb = zbflVar;
        zbuf.m(zbfl.class, zbflVar);
    }

    private zbfl() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ခ\u0000\u0002ဇ\u0001\u0003ည\u0002", new Object[]{"zbd", "zbe", "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbfl();
        }
        zbfj zbfjVar = null;
        if (i3 == 4) {
            return new zbfk(zbfjVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
