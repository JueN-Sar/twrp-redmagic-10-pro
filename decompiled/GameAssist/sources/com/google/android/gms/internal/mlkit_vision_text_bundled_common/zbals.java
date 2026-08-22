package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbals extends zbuf implements zbvn {
    private static final zbals zbb;
    private int zbd;
    private zbalp zbe;
    private zbalv zbf;
    private zbaly zbg;

    static {
        zbals zbalsVar = new zbals();
        zbb = zbalsVar;
        zbuf.m(zbals.class, zbalsVar);
    }

    private zbals() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zbd", "zbe", "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbals();
        }
        zbalq zbalqVar = null;
        if (i3 == 4) {
            return new zbalr(zbalqVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
