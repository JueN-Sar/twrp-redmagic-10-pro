package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbacr extends zbuf implements zbvn {
    private static final zbacr zbb;
    private int zbd;
    private int zbe;
    private String zbf = "";
    private float zbg;
    private float zbh;

    static {
        zbacr zbacrVar = new zbacr();
        zbb = zbacrVar;
        zbuf.m(zbacr.class, zbacrVar);
    }

    private zbacr() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0002\u0005\u0004\u0000\u0000\u0000\u0002ဈ\u0001\u0003ခ\u0002\u0004ခ\u0003\u0005᠌\u0000", new Object[]{"zbd", "zbf", "zbg", "zbh", "zbe", zbadc.f12666a});
        }
        if (i3 == 3) {
            return new zbacr();
        }
        zbacp zbacpVar = null;
        if (i3 == 4) {
            return new zbacq(zbacpVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
