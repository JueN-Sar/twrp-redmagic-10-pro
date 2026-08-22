package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbiw extends zbuf implements zbvn {
    private static final zbiw zbb;
    private int zbd;
    private zbhl zbe;
    private zbhl zbf;

    static {
        zbiw zbiwVar = new zbiw();
        zbb = zbiwVar;
        zbuf.m(zbiw.class, zbiwVar);
    }

    private zbiw() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0005\u0006\u0002\u0000\u0000\u0000\u0005ဉ\u0000\u0006ဉ\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbiw();
        }
        zbit zbitVar = null;
        if (i3 == 4) {
            return new zbiv(zbitVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
