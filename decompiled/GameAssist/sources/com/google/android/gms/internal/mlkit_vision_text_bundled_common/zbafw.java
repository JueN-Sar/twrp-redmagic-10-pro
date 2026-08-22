package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbafw extends zbuf implements zbvn {
    private static final zbafw zbb;
    private int zbd = 0;
    private Object zbe;

    static {
        zbafw zbafwVar = new zbafw();
        zbb = zbafwVar;
        zbuf.m(zbafw.class, zbafwVar);
    }

    private zbafw() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001;\u0000\u0002=\u0000", new Object[]{"zbe", "zbd"});
        }
        if (i3 == 3) {
            return new zbafw();
        }
        zbafu zbafuVar = null;
        if (i3 == 4) {
            return new zbafv(zbafuVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
