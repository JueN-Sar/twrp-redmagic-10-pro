package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbafa extends zbuf implements zbvn {
    private static final zbafa zbb;
    private int zbd;
    private float zbe = 10.0f;
    private float zbf = 10.0f;
    private float zbg = 5.0f;
    private float zbh = 5.0f;
    private float zbi = 10.0f;
    private float zbj = 10.0f;

    static {
        zbafa zbafaVar = new zbafa();
        zbb = zbafaVar;
        zbuf.m(zbafa.class, zbafaVar);
    }

    private zbafa() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ခ\u0000\u0002ခ\u0001\u0003ခ\u0002\u0004ခ\u0003\u0005ခ\u0004\u0006ခ\u0005", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbi", "zbj"});
        }
        if (i3 == 3) {
            return new zbafa();
        }
        zbaeu zbaeuVar = null;
        if (i3 == 4) {
            return new zbaez(zbaeuVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
