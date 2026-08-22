package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbat extends zbuf implements zbvn {
    private static final zbat zbb;
    private int zbd;
    private float zbe;
    private boolean zbf;

    static {
        zbat zbatVar = new zbat();
        zbb = zbatVar;
        zbuf.m(zbat.class, zbatVar);
    }

    private zbat() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ခ\u0000\u0002ဇ\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbat();
        }
        zbar zbarVar = null;
        if (i3 == 4) {
            return new zbas(zbarVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
