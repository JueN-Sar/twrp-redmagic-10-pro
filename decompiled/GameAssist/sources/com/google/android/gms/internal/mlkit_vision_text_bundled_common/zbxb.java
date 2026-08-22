package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbxb extends zbuf implements zbvn {
    private static final zbxb zbb;
    private int zbd;
    private int zbe;
    private zbzo zbf;
    private zbyw zbg;
    private zbyy zbh;

    static {
        zbxb zbxbVar = new zbxb();
        zbb = zbxbVar;
        zbuf.m(zbxb.class, zbxbVar);
    }

    private zbxb() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0005\u0004\u0000\u0000\u0000\u0001᠌\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0005ဉ\u0003", new Object[]{"zbd", "zbe", zbye.f13075a, "zbf", "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbxb();
        }
        zbwz zbwzVar = null;
        if (i3 == 4) {
            return new zbxa(zbwzVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
