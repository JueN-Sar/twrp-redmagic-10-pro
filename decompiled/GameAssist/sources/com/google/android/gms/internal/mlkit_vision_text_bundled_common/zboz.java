package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zboz extends zbuf implements zbvn {
    private static final zboz zbb;
    private int zbd;
    private int zbe;
    private int zbf;
    private int zbg = 2;
    private float zbh;
    private boolean zbi;

    static {
        zboz zbozVar = new zboz();
        zbb = zbozVar;
        zbuf.m(zboz.class, zbozVar);
    }

    private zboz() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003᠌\u0002\u0004ခ\u0003\u0005ဇ\u0004", new Object[]{"zbd", "zbe", zbox.f12916a, "zbf", zbpd.f12918a, "zbg", zbpc.f12917a, "zbh", "zbi"});
        }
        if (i3 == 3) {
            return new zboz();
        }
        zboq zboqVar = null;
        if (i3 == 4) {
            return new zboy(zboqVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
