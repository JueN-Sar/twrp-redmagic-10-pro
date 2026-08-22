package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbxj extends zbuf implements zbvn {
    private static final zbxj zbb;
    private int zbd;
    private int zbe = -1;

    static {
        zbxj zbxjVar = new zbxj();
        zbb = zbxjVar;
        zbuf.m(zbxj.class, zbxjVar);
    }

    private zbxj() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001င\u0000", new Object[]{"zbd", "zbe"});
        }
        if (i3 == 3) {
            return new zbxj();
        }
        zbwz zbwzVar = null;
        if (i3 == 4) {
            return new zbxi(zbwzVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
