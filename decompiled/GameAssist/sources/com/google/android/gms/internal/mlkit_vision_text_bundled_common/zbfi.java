package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbfi extends zbuf implements zbvn {
    private static final zbfi zbb;
    private int zbd;
    private boolean zbe;
    private String zbf = "";
    private String zbg = "";

    static {
        zbfi zbfiVar = new zbfi();
        zbb = zbfiVar;
        zbuf.m(zbfi.class, zbfiVar);
    }

    private zbfi() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဈ\u0001\u0003ဈ\u0002", new Object[]{"zbd", "zbe", "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbfi();
        }
        zbfg zbfgVar = null;
        if (i3 == 4) {
            return new zbfh(zbfgVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
