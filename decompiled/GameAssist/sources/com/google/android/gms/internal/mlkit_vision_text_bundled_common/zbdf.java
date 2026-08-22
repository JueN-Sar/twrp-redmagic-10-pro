package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbdf extends zbuf implements zbvn {
    private static final zbdf zbb;
    private int zbd;
    private int zbe = -1;
    private float zbf = 1.0f;
    private int zbg;

    static {
        zbdf zbdfVar = new zbdf();
        zbb = zbdfVar;
        zbuf.m(zbdf.class, zbdfVar);
    }

    private zbdf() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002ခ\u0001\u0003င\u0002", new Object[]{"zbd", "zbe", "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbdf();
        }
        zbda zbdaVar = null;
        if (i3 == 4) {
            return new zbde(zbdaVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
