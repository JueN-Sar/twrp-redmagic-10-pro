package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbxf extends zbuf implements zbvn {
    private static final zbxf zbb;
    private int zbd;
    private zbyw zbe;
    private zbun zbf = zbuf.C();
    private zbun zbg = zbuf.C();

    static {
        zbxf zbxfVar = new zbxf();
        zbb = zbxfVar;
        zbuf.m(zbxf.class, zbxfVar);
    }

    private zbxf() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0002\u0000\u0001ဉ\u0000\u0002\u001b\u0003\u001b", new Object[]{"zbd", "zbe", "zbf", zbzm.class, "zbg", zbxb.class});
        }
        if (i3 == 3) {
            return new zbxf();
        }
        zbwz zbwzVar = null;
        if (i3 == 4) {
            return new zbxe(zbwzVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
