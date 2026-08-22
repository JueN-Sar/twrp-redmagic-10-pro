package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbsh extends zbuf implements zbvn {
    private static final zbsh zbb;
    private int zbd;
    private float zbf;
    private String zbe = "";
    private int zbg = 1;

    static {
        zbsh zbshVar = new zbsh();
        zbb = zbshVar;
        zbuf.m(zbsh.class, zbshVar);
    }

    private zbsh() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ခ\u0001\u0003င\u0002", new Object[]{"zbd", "zbe", "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbsh();
        }
        zbsf zbsfVar = null;
        if (i3 == 4) {
            return new zbsg(zbsfVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
