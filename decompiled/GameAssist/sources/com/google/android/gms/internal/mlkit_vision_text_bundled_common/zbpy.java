package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbpy extends zbuf implements zbvn {
    private static final zbpy zbb;
    private int zbd;
    private int zbe = -1;
    private int zbf = -1;
    private zbuk zbg = zbuf.z();
    private zbul zbh = zbuf.A();
    private zbuk zbi = zbuf.z();

    static {
        zbpy zbpyVar = new zbpy();
        zbb = zbpyVar;
        zbuf.m(zbpy.class, zbpyVar);
    }

    private zbpy() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0005\u0000\u0001\u0001\u0006\u0005\u0000\u0003\u0000\u0001င\u0000\u0002င\u0001\u0003\u0013\u0004\u0016\u0006\u0013", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbi"});
        }
        if (i3 == 3) {
            return new zbpy();
        }
        zbpu zbpuVar = null;
        if (i3 == 4) {
            return new zbpx(zbpuVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
