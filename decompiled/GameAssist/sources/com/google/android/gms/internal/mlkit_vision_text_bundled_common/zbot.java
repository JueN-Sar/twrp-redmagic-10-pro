package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbot extends zbuf implements zbvn {
    private static final zbot zbb;
    private int zbd;
    private double zbe;
    private double zbf;

    static {
        zbot zbotVar = new zbot();
        zbb = zbotVar;
        zbuf.m(zbot.class, zbotVar);
    }

    private zbot() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001က\u0000\u0002က\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbot();
        }
        zboq zboqVar = null;
        if (i3 == 4) {
            return new zbos(zboqVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
