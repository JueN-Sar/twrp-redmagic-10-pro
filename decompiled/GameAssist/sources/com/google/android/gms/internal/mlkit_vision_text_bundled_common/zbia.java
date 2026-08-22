package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbia extends zbuf implements zbvn {
    private static final zbia zbb;
    private int zbd;
    private Object zbf;
    private int zbg;
    private int zbh;
    private int zbe = 0;
    private zbun zbi = zbuf.C();

    static {
        zbia zbiaVar = new zbia();
        zbb = zbiaVar;
        zbuf.m(zbia.class, zbiaVar);
    }

    private zbia() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0005\u0001\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001=\u0000\u0002င\u0000\u0003င\u0001\u0004<\u0000\u0005\u001b", new Object[]{"zbf", "zbe", "zbd", "zbg", "zbh", zbhz.class, "zbi", zbhv.class});
        }
        if (i3 == 3) {
            return new zbia();
        }
        zbhs zbhsVar = null;
        if (i3 == 4) {
            return new zbht(zbhsVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
