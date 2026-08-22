package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbfx extends zbuf implements zbvn {
    private static final zbfx zbb;
    private int zbd;
    private float zbe = 0.7f;
    private int zbf = 2;
    private float zbg = 0.2f;

    static {
        zbfx zbfxVar = new zbfx();
        zbb = zbfxVar;
        zbuf.m(zbfx.class, zbfxVar);
    }

    private zbfx() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ခ\u0000\u0002င\u0001\u0003ခ\u0002", new Object[]{"zbd", "zbe", "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbfx();
        }
        zbfv zbfvVar = null;
        if (i3 == 4) {
            return new zbfw(zbfvVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
