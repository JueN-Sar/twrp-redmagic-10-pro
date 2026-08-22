package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbrv extends zbuf implements zbvn {
    private static final zbrv zbb;
    private int zbd;
    private String zbe = "";
    private double zbf = 1.0d;
    private zbun zbg = zbuf.C();

    static {
        zbrv zbrvVar = new zbrv();
        zbb = zbrvVar;
        zbuf.m(zbrv.class, zbrvVar);
    }

    private zbrv() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u000f\u0003\u0000\u0001\u0000\u0001ဈ\u0000\u0002က\u0001\u000f\u001a", new Object[]{"zbd", "zbe", "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbrv();
        }
        zbrt zbrtVar = null;
        if (i3 == 4) {
            return new zbru(zbrtVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
