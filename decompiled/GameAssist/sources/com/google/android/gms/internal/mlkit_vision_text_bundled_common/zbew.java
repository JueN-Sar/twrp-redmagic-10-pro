package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbew extends zbuf implements zbvn {
    private static final zbew zbb;
    private int zbd;
    private boolean zbe;
    private float zbf = 0.2f;
    private zbun zbg = zbuf.C();

    static {
        zbew zbewVar = new zbew();
        zbb = zbewVar;
        zbuf.m(zbew.class, zbewVar);
    }

    private zbew() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0004\u0003\u0000\u0001\u0000\u0001ဇ\u0000\u0002ခ\u0001\u0004\u001b", new Object[]{"zbd", "zbe", "zbf", "zbg", zbez.class});
        }
        if (i3 == 3) {
            return new zbew();
        }
        zbeu zbeuVar = null;
        if (i3 == 4) {
            return new zbev(zbeuVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
