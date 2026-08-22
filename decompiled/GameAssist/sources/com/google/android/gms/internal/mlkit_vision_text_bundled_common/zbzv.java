package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbzv extends zbuf implements zbvn {
    private static final zbzv zbb;
    private int zbd;
    private int zbe;
    private int zbf;
    private zbuk zbg = zbuf.z();
    private int zbh;

    static {
        zbzv zbzvVar = new zbzv();
        zbb = zbzvVar;
        zbuf.m(zbzv.class, zbzvVar);
    }

    private zbzv() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001င\u0000\u0002င\u0001\u0003$\u0004᠌\u0002", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", zbzu.f13086a});
        }
        if (i3 == 3) {
            return new zbzv();
        }
        zbzs zbzsVar = null;
        if (i3 == 4) {
            return new zbzt(zbzsVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
