package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaed extends zbuf implements zbvn {
    private static final zbaed zbb;
    private int zbd;
    private float zbf;
    private float zbg;
    private float zbi;
    private String zbe = "";
    private float zbh = 1.0f;
    private zbun zbj = zbuf.C();

    static {
        zbaed zbaedVar = new zbaed();
        zbb = zbaedVar;
        zbuf.m(zbaed.class, zbaedVar);
    }

    private zbaed() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001ဈ\u0000\u0002ခ\u0001\u0003ခ\u0002\u0004ခ\u0003\u0005ခ\u0004\u0006\u001a", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbi", "zbj"});
        }
        if (i3 == 3) {
            return new zbaed();
        }
        zbadn zbadnVar = null;
        if (i3 == 4) {
            return new zbaec(zbadnVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
