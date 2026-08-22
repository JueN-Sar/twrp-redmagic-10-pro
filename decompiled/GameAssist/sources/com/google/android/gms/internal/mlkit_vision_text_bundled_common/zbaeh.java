package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaeh extends zbuf implements zbvn {
    private static final zbaeh zbb;
    private int zbd;
    private float zbe;
    private float zbf;
    private float zbg;
    private float zbh;

    static {
        zbaeh zbaehVar = new zbaeh();
        zbb = zbaehVar;
        zbuf.m(zbaeh.class, zbaehVar);
    }

    private zbaeh() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ခ\u0000\u0002ခ\u0001\u0003ခ\u0002\u0004ခ\u0003", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbaeh();
        }
        zbaef zbaefVar = null;
        if (i3 == 4) {
            return new zbaeg(zbaefVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
