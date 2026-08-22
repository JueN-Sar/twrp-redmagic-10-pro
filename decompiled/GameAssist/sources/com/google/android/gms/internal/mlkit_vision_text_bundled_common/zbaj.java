package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaj extends zbuf implements zbvn {
    private static final zbaj zbb;
    private int zbd;
    private String zbe = "";
    private String zbf = "";
    private String zbg = "";
    private int zbh;

    static {
        zbaj zbajVar = new zbaj();
        zbb = zbajVar;
        zbuf.m(zbaj.class, zbajVar);
    }

    private zbaj() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004᠌\u0003", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", zbai.f12696a});
        }
        if (i3 == 3) {
            return new zbaj();
        }
        zbag zbagVar = null;
        if (i3 == 4) {
            return new zbah(zbagVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
