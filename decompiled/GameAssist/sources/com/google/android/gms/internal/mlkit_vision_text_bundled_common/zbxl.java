package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbxl extends zbuf implements zbvn {
    private static final zbxl zbb;
    private int zbd;
    private String zbe = "";
    private String zbf = "";

    static {
        zbxl zbxlVar = new zbxl();
        zbb = zbxlVar;
        zbuf.m(zbxl.class, zbxlVar);
    }

    private zbxl() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbxl();
        }
        zbwz zbwzVar = null;
        if (i3 == 4) {
            return new zbxk(zbwzVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
