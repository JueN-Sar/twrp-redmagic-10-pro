package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbajw extends zbuf implements zbvn {
    private static final zbajw zbb;
    private int zbd = 0;
    private Object zbe;

    static {
        zbajw zbajwVar = new zbajw();
        zbb = zbajwVar;
        zbuf.m(zbajw.class, zbajwVar);
    }

    private zbajw() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000", new Object[]{"zbe", "zbd", zbajz.class, zbakf.class});
        }
        if (i3 == 3) {
            return new zbajw();
        }
        zbaju zbajuVar = null;
        if (i3 == 4) {
            return new zbajv(zbajuVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
