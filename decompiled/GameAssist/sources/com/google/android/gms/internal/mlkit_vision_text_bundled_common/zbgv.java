package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbgv extends zbuf implements zbvn {
    private static final zbgv zbb;
    private int zbd;
    private float zbe;
    private float zbf;

    static {
        zbgv zbgvVar = new zbgv();
        zbb = zbgvVar;
        zbuf.m(zbgv.class, zbgvVar);
    }

    private zbgv() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ခ\u0000\u0002ခ\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbgv();
        }
        zbgs zbgsVar = null;
        if (i3 == 4) {
            return new zbgu(zbgsVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
