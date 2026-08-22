package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbjj extends zbuf implements zbvn {
    private static final zbjj zbb;
    private int zbd;
    private zbuk zbe = zbuf.z();
    private zbtc zbf = zbtc.zbb;

    static {
        zbjj zbjjVar = new zbjj();
        zbb = zbjjVar;
        zbuf.m(zbjj.class, zbjjVar);
    }

    private zbjj() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001$\u0002ည\u0000", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbjj();
        }
        zbjh zbjhVar = null;
        if (i3 == 4) {
            return new zbji(zbjhVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
