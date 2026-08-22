package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaem extends zbuf implements zbvn {
    private static final zbaem zbb;
    private int zbd;
    private float zbe;
    private float zbf;
    private float zbg;
    private int zbh;
    private int zbi;
    private float zbj;

    static {
        zbaem zbaemVar = new zbaem();
        zbb = zbaemVar;
        zbuf.m(zbaem.class, zbaemVar);
    }

    private zbaem() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ခ\u0000\u0002ခ\u0001\u0003ခ\u0002\u0004᠌\u0003\u0005᠌\u0004\u0006ခ\u0005", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", zbaek.f12674a, "zbi", zbael.f12675a, "zbj"});
        }
        if (i3 == 3) {
            return new zbaem();
        }
        zbaef zbaefVar = null;
        if (i3 == 4) {
            return new zbaej(zbaefVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
