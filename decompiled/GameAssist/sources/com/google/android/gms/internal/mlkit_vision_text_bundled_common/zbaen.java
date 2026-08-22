package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaen extends zbuf implements zbvn {
    private static final zbaen zbb;
    private int zbd;
    private zbaeh zbe;
    private zbun zbf = zbuf.C();
    private float zbg;

    static {
        zbaen zbaenVar = new zbaen();
        zbb = zbaenVar;
        zbuf.m(zbaen.class, zbaenVar);
    }

    private zbaen() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဉ\u0000\u0002\u001b\u0003ခ\u0001", new Object[]{"zbd", "zbe", "zbf", zbaem.class, "zbg"});
        }
        if (i3 == 3) {
            return new zbaen();
        }
        zbaef zbaefVar = null;
        if (i3 == 4) {
            return new zbaei(zbaefVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
