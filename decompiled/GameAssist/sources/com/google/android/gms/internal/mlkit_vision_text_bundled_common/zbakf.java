package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbakf extends zbuf implements zbvn {
    private static final zbakf zbb;
    private zbum zbd = zbuf.B();

    static {
        zbakf zbakfVar = new zbakf();
        zbb = zbakfVar;
        zbuf.m(zbakf.class, zbakfVar);
    }

    private zbakf() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001%", new Object[]{"zbd"});
        }
        if (i3 == 3) {
            return new zbakf();
        }
        zbakd zbakdVar = null;
        if (i3 == 4) {
            return new zbake(zbakdVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
