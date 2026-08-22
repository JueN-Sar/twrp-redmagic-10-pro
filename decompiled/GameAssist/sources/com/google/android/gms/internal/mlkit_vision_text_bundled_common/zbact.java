package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbact extends zbuf implements zbvn {
    private static final zbact zbb;
    private int zbd;
    private float zbe;
    private float zbf;
    private float zbg;
    private float zbh;

    static {
        zbact zbactVar = new zbact();
        zbb = zbactVar;
        zbuf.m(zbact.class, zbactVar);
    }

    private zbact() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ခ\u0000\u0002ခ\u0001\u0003ခ\u0002\u0004ခ\u0003", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbact();
        }
        zbacp zbacpVar = null;
        if (i3 == 4) {
            return new zbacs(zbacpVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
