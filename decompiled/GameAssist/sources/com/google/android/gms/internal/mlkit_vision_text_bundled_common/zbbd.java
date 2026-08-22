package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbbd extends zbuf implements zbvn {
    private static final zbbd zbb;
    private int zbd;
    private String zbe = "";
    private String zbf = "";
    private String zbg = "";

    static {
        zbbd zbbdVar = new zbbd();
        zbb = zbbdVar;
        zbuf.m(zbbd.class, zbbdVar);
    }

    private zbbd() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002", new Object[]{"zbd", "zbe", "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbbd();
        }
        zbba zbbaVar = null;
        if (i3 == 4) {
            return new zbbc(zbbaVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
