package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbyd extends zbuf implements zbvn {
    private static final zbyd zbb;
    private int zbd;
    private int zbe;
    private zbxv zbh;
    private int zbj;
    private int zbk;
    private int zbn;
    private zbun zbf = zbuf.C();
    private int zbg = -1;
    private String zbi = "";
    private zbul zbl = zbuf.A();
    private String zbm = "";

    static {
        zbyd zbydVar = new zbyd();
        zbb = zbydVar;
        zbuf.m(zbyd.class, zbydVar);
    }

    private zbyd() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\n\u0000\u0001\u0001\n\n\u0000\u0002\u0000\u0001᠌\u0000\u0002\u001b\u0003င\u0001\u0004ဉ\u0002\u0005ဈ\u0003\u0006᠌\u0004\u0007᠌\u0005\b'\tဈ\u0006\n᠌\u0007", new Object[]{"zbd", "zbe", zbxy.f13071a, "zbf", zbxx.class, "zbg", "zbh", "zbi", "zbj", zbya.f13072a, "zbk", zbyb.f13073a, "zbl", "zbm", "zbn", zbyc.f13074a});
        }
        if (i3 == 3) {
            return new zbyd();
        }
        zbwz zbwzVar = null;
        if (i3 == 4) {
            return new zbxz(zbwzVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
