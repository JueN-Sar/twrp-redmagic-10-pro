package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbxv extends zbuf implements zbvn {
    private static final zbxv zbb;
    private int zbd;
    private int zbe;
    private int zbf;
    private zbun zbg = zbuf.C();
    private int zbh;

    static {
        zbxv zbxvVar = new zbxv();
        zbb = zbxvVar;
        zbuf.m(zbxv.class, zbxvVar);
    }

    private zbxv() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001᠌\u0000\u0002င\u0001\u0003\u001a\u0004င\u0002", new Object[]{"zbd", "zbe", zbxu.f13070a, "zbf", "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbxv();
        }
        zbwz zbwzVar = null;
        if (i3 == 4) {
            return new zbxt(zbwzVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
