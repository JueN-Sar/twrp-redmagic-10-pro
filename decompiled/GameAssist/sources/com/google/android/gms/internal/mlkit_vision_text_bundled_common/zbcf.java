package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbcf extends zbuf implements zbvn {
    private static final zbcf zbb;
    private int zbd;
    private float zbf;
    private int zbi;
    private float zbj;
    private zbun zbe = zbuf.C();
    private boolean zbg = true;
    private float zbh = 0.8f;

    static {
        zbcf zbcfVar = new zbcf();
        zbb = zbcfVar;
        zbuf.m(zbcf.class, zbcfVar);
    }

    private zbcf() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001\u001a\u0002ခ\u0000\u0003ဇ\u0001\u0004ခ\u0002\u0005င\u0003\u0006ခ\u0004", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbi", "zbj"});
        }
        if (i3 == 3) {
            return new zbcf();
        }
        zbcd zbcdVar = null;
        if (i3 == 4) {
            return new zbce(zbcdVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
