package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbhi extends zbuf implements zbvn {
    private static final zbhi zbb;
    private int zbd;
    private Object zbf;
    private float zbg;
    private boolean zbi;
    private int zbe = 0;
    private String zbh = "";

    static {
        zbhi zbhiVar = new zbhi();
        zbb = zbhiVar;
        zbuf.m(zbhi.class, zbhiVar);
    }

    private zbhi() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\u0005\u0001\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001;\u0000\u0002ခ\u0000\u0003;\u0000\u0004ဈ\u0001\u0005ဇ\u0002", new Object[]{"zbf", "zbe", "zbd", "zbg", "zbh", "zbi"});
        }
        if (i3 == 3) {
            return new zbhi();
        }
        zbhg zbhgVar = null;
        if (i3 == 4) {
            return new zbhh(zbhgVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
