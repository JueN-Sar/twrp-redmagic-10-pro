package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbahy extends zbuf implements zbvn {
    private static final zbahy zbb;
    private int zbd;
    private long zbh;
    private zbahr zbl;
    private zbahf zbm;
    private int zbo;
    private zbun zbe = zbuf.C();
    private zbun zbf = zbuf.C();
    private zbun zbg = zbuf.C();
    private String zbi = "";
    private zbun zbj = zbuf.C();
    private String zbk = "";
    private zbum zbn = zbuf.B();

    static {
        zbahy zbahyVar = new zbahy();
        zbb = zbahyVar;
        zbuf.m(zbahy.class, zbahyVar);
    }

    private zbahy() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0005\u0000\u0001\u001b\u0002\u001b\u0003\u0002\u0004Ȉ\u0005Ț\u0006Ȉ\u0007ဉ\u0001\b%\t\u0004\n\u001b\u000bဉ\u0000", new Object[]{"zbd", "zbe", zbaht.class, "zbf", zbahv.class, "zbh", "zbi", "zbj", "zbk", "zbm", "zbn", "zbo", "zbg", zbahp.class, "zbl"});
        }
        if (i3 == 3) {
            return new zbahy();
        }
        zbagx zbagxVar = null;
        if (i3 == 4) {
            return new zbahn(zbagxVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
