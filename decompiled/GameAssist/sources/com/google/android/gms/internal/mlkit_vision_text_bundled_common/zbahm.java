package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbahm extends zbuf implements zbvn {
    private static final zbahm zbb;
    private int zbd;
    private Object zbf;
    private zbahf zbg;
    private int zbe = 0;
    private zbvg zbm = zbvg.a();
    private String zbh = "";
    private String zbi = "";
    private String zbj = "";
    private zbun zbk = zbuf.C();
    private zbun zbl = zbuf.C();
    private zbun zbn = zbuf.C();

    static {
        zbahm zbahmVar = new zbahm();
        zbb = zbahmVar;
        zbuf.m(zbahm.class, zbahmVar);
    }

    private zbahm() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\t\u0001\u0001\u0001\u000b\t\u0001\u0003\u0000\u0001ဉ\u0000\u0002Ȉ\u0003\u001b\u0004\u001b\u0005<\u0000\b2\t\u001b\nȈ\u000bȈ", new Object[]{"zbf", "zbe", "zbd", "zbg", "zbh", "zbk", zbahj.class, "zbl", zbahl.class, zbahd.class, "zbm", zbahg.f12695a, "zbn", zbsp.class, "zbi", "zbj"});
        }
        if (i3 == 3) {
            return new zbahm();
        }
        zbagx zbagxVar = null;
        if (i3 == 4) {
            return new zbahh(zbagxVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
