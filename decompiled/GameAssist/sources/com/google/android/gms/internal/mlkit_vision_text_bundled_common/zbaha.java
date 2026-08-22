package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaha extends zbuf implements zbvn {
    private static final zbaha zbb;
    private int zbd;
    private zbul zbe = zbuf.A();

    static {
        zbaha zbahaVar = new zbaha();
        zbb = zbahaVar;
        zbuf.m(zbaha.class, zbahaVar);
    }

    private zbaha() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u0004\u0002'", new Object[]{"zbd", "zbe"});
        }
        if (i3 == 3) {
            return new zbaha();
        }
        zbagx zbagxVar = null;
        if (i3 == 4) {
            return new zbagz(zbagxVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
