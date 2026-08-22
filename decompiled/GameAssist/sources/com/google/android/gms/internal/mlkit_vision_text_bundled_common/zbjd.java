package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbjd extends zbuf implements zbvn {
    private static final zbjd zbb;
    private int zbd;
    private zbjj zbe;
    private int zbf;

    static {
        zbjd zbjdVar = new zbjd();
        zbb = zbjdVar;
        zbuf.m(zbjd.class, zbjdVar);
    }

    private zbjd() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002င\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbjd();
        }
        zbjb zbjbVar = null;
        if (i3 == 4) {
            return new zbjc(zbjbVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
