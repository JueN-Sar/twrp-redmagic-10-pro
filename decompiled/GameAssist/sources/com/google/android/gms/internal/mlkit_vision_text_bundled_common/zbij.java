package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbij extends zbuf implements zbvn {
    private static final zbij zbb;
    private int zbd;
    private zbih zbf;
    private zbhl zbg;
    private zbalp zbh;
    private zbxb zbk;
    private String zbe = "";
    private String zbi = "en";
    private int zbj = 1;
    private int zbl = -1;
    private String zbm = "";

    static {
        zbij zbijVar = new zbij();
        zbb = zbijVar;
        zbuf.m(zbij.class, zbijVar);
    }

    private zbij() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဉ\u0001\u0003᠌\u0005\u0004ဉ\u0006\u0005ဉ\u0002\u0006ဈ\u0004\u0007င\u0007\bဉ\u0003\tဈ\b", new Object[]{"zbd", "zbe", "zbf", "zbj", zbii.f12816a, "zbk", "zbg", "zbi", "zbl", "zbh", "zbm"});
        }
        if (i3 == 3) {
            return new zbij();
        }
        zbie zbieVar = null;
        if (i3 == 4) {
            return new zbif(zbieVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
