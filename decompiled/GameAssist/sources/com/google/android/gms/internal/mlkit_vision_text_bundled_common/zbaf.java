package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaf extends zbuf implements zbvn {
    private static final zbaf zbb;
    private int zbd;
    private zbaw zbe;
    private zbsh zbf;
    private String zbg = "";

    static {
        zbaf zbafVar = new zbaf();
        zbb = zbafVar;
        zbuf.m(zbaf.class, zbafVar);
    }

    private zbaf() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဈ\u0002\u0003ဉ\u0001", new Object[]{"zbd", "zbe", "zbg", "zbf"});
        }
        if (i3 == 3) {
            return new zbaf();
        }
        zbad zbadVar = null;
        if (i3 == 4) {
            return new zbae(zbadVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
