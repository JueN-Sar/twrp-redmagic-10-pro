package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbee extends zbuf implements zbvn {
    private static final zbee zbb;
    private int zbd;
    private int zbe = 3;
    private float zbf = 100000.0f;
    private float zbg;

    static {
        zbee zbeeVar = new zbee();
        zbb = zbeeVar;
        zbuf.m(zbee.class, zbeeVar);
    }

    private zbee() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002ခ\u0001\u0003ခ\u0002", new Object[]{"zbd", "zbe", zbec.f12771a, "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbee();
        }
        zbeb zbebVar = null;
        if (i3 == 4) {
            return new zbed(zbebVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
