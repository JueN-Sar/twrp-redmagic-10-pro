package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbho extends zbuf implements zbvn {
    private static final zbho zbb;
    private int zbd;
    private int zbe;
    private long zbf;
    private long zbg;

    static {
        zbho zbhoVar = new zbho();
        zbb = zbhoVar;
        zbuf.m(zbho.class, zbhoVar);
    }

    private zbho() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002ဂ\u0001\u0003ဂ\u0002", new Object[]{"zbd", "zbe", "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbho();
        }
        zbhm zbhmVar = null;
        if (i3 == 4) {
            return new zbhn(zbhmVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
