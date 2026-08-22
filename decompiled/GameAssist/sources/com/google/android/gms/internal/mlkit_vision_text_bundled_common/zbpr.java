package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbpr extends zbuf implements zbvn {
    private static final zbpr zbb;
    private int zbd;
    private int zbe;
    private int zbg;
    private boolean zbh;
    private int zbi;
    private boolean zbk;
    private zbxb zbl;
    private zbun zbf = zbuf.C();
    private int zbj = 1;

    static {
        zbpr zbprVar = new zbpr();
        zbb = zbprVar;
        zbuf.m(zbpr.class, zbprVar);
    }

    private zbpr() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001င\u0000\u0002\u001b\u0003င\u0001\u0004ဇ\u0002\u0005င\u0003\u0006င\u0004\u0007ဇ\u0005\bဉ\u0006", new Object[]{"zbd", "zbe", "zbf", zbpp.class, "zbg", "zbh", "zbi", "zbj", "zbk", "zbl"});
        }
        if (i3 == 3) {
            return new zbpr();
        }
        zbpn zbpnVar = null;
        if (i3 == 4) {
            return new zbpq(zbpnVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
