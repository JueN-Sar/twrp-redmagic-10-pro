package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbqy extends zbuf implements zbvn {
    private static final zbqy zbb;
    private int zbd;
    private float zbf;
    private float zbg;
    private float zbh;
    private float zbi;
    private float zbk;
    private float zbl;
    private boolean zbm;
    private int zbe = 1;
    private float zbj = 1.0f;

    static {
        zbqy zbqyVar = new zbqy();
        zbb = zbqyVar;
        zbuf.m(zbqy.class, zbqyVar);
    }

    private zbqy() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001င\u0000\u0002ခ\u0001\u0003ခ\u0002\u0004ခ\u0003\u0005ခ\u0004\u0006ခ\u0005\u0007ခ\u0006\bခ\u0007\tဇ\b", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbi", "zbj", "zbk", "zbl", "zbm"});
        }
        if (i3 == 3) {
            return new zbqy();
        }
        zbpu zbpuVar = null;
        if (i3 == 4) {
            return new zbqx(zbpuVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
