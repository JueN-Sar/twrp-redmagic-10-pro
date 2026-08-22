package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbhl extends zbuf implements zbvn {
    private static final zbhl zbb;
    private int zbd;
    private zbtc zbe = zbtc.zbb;
    private String zbf = "";
    private zbho zbg;

    static {
        zbhl zbhlVar = new zbhl();
        zbb = zbhlVar;
        zbuf.m(zbhl.class, zbhlVar);
    }

    private zbhl() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0004\u0003\u0000\u0000\u0000\u0001ဈ\u0001\u0002ည\u0000\u0004ဉ\u0002", new Object[]{"zbd", "zbf", "zbe", "zbg"});
        }
        if (i3 == 3) {
            return new zbhl();
        }
        zbhj zbhjVar = null;
        if (i3 == 4) {
            return new zbhk(zbhjVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
