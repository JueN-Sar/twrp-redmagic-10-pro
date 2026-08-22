package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbcz extends zbuf implements zbvn {
    private static final zbcz zbb;
    private int zbd;
    private zbalp zbe;
    private zbhl zbf;
    private float zbi;
    private zbxb zbm;
    private String zbg = "en";
    private int zbh = -1;
    private zbun zbj = zbuf.C();
    private zbun zbk = zbuf.C();
    private int zbl = -1;

    static {
        zbcz zbczVar = new zbcz();
        zbb = zbczVar;
        zbuf.m(zbcz.class, zbczVar);
    }

    private zbcz() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\t\u0000\u0001\u0001\t\t\u0000\u0002\u0000\u0001ဉ\u0001\u0002ဈ\u0002\u0003င\u0003\u0004ခ\u0004\u0005\u001a\u0006\u001a\u0007င\u0005\bဉ\u0006\tဉ\u0000", new Object[]{"zbd", "zbf", "zbg", "zbh", "zbi", "zbj", "zbk", "zbl", "zbm", "zbe"});
        }
        if (i3 == 3) {
            return new zbcz();
        }
        zbcx zbcxVar = null;
        if (i3 == 4) {
            return new zbcy(zbcxVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
