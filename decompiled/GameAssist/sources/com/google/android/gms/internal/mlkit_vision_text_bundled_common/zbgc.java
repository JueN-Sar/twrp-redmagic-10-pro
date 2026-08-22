package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbgc extends zbuf implements zbvn {
    private static final zbgc zbb;
    private int zbd;
    private int zbe;
    private int zbf;

    static {
        zbgc zbgcVar = new zbgc();
        zbb = zbgcVar;
        zbuf.m(zbgc.class, zbgcVar);
    }

    private zbgc() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbgc();
        }
        zbfy zbfyVar = null;
        if (i3 == 4) {
            return new zbgb(zbfyVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
