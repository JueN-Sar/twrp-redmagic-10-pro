package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbpm extends zbuf implements zbvn {
    private static final zbpm zbb;
    private int zbd;
    private zbsp zbe;

    static {
        zbpm zbpmVar = new zbpm();
        zbb = zbpmVar;
        zbuf.m(zbpm.class, zbpmVar);
    }

    private zbpm() {
    }

    public static zbpm F() {
        return zbb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"zbd", "zbe"});
        }
        if (i3 == 3) {
            return new zbpm();
        }
        zbph zbphVar = null;
        if (i3 == 4) {
            return new zbpl(zbphVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
