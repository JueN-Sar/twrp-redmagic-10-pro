package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbail extends zbuf implements zbvn {
    private static final zbail zbb;
    private int zbd;
    private zbhl zbe;
    private String zbf = "";

    static {
        zbail zbailVar = new zbail();
        zbb = zbailVar;
        zbuf.m(zbail.class, zbailVar);
    }

    private zbail() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဈ\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbail();
        }
        zbaij zbaijVar = null;
        if (i3 == 4) {
            return new zbaik(zbaijVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
