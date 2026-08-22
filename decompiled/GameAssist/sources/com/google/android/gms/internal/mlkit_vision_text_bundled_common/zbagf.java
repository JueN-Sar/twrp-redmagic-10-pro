package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbagf extends zbuf implements zbvn {
    private static final zbagf zbb;
    private int zbd;
    private Object zbf;
    private int zbe = 0;
    private String zbg = "";
    private String zbh = "";
    private zbtc zbi = zbtc.zbb;

    static {
        zbagf zbagfVar = new zbagf();
        zbb = zbagfVar;
        zbuf.m(zbagf.class, zbagfVar);
    }

    private zbagf() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0005\u0001\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003<\u0000\u0004<\u0000\u0005ည\u0002", new Object[]{"zbf", "zbe", "zbd", "zbg", "zbh", zbafz.class, zbagt.class, "zbi"});
        }
        if (i3 == 3) {
            return new zbagf();
        }
        zbagd zbagdVar = null;
        if (i3 == 4) {
            return new zbage(zbagdVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
