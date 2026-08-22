package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbff extends zbuf implements zbvn {
    private static final zbff zbb;
    private int zbd;
    private float zbe;

    static {
        zbff zbffVar = new zbff();
        zbb = zbffVar;
        zbuf.m(zbff.class, zbffVar);
    }

    private zbff() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ခ\u0000", new Object[]{"zbd", "zbe"});
        }
        if (i3 == 3) {
            return new zbff();
        }
        zbfd zbfdVar = null;
        if (i3 == 4) {
            return new zbfe(zbfdVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
