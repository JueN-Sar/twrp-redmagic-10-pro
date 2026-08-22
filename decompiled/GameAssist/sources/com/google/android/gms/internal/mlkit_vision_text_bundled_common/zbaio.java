package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaio extends zbuf implements zbvn {
    private static final zbaio zbb;
    private int zbd;
    private int zbe;
    private boolean zbh;
    private int zbf = 100;
    private int zbg = -100;
    private float zbi = 40.0f;

    static {
        zbaio zbaioVar = new zbaio();
        zbb = zbaioVar;
        zbuf.m(zbaio.class, zbaioVar);
    }

    private zbaio() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004ဇ\u0003\u0005ခ\u0004", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbi"});
        }
        if (i3 == 3) {
            return new zbaio();
        }
        zbaim zbaimVar = null;
        if (i3 == 4) {
            return new zbain(zbaimVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
