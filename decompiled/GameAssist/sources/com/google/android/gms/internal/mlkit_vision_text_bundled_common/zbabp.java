package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbabp extends zbuf implements zbvn {
    private static final zbabp zbb;
    private zbvg zbd = zbvg.a();

    static {
        zbabp zbabpVar = new zbabp();
        zbb = zbabpVar;
        zbuf.m(zbabp.class, zbabpVar);
    }

    private zbabp() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"zbd", zbabo.f12654a});
        }
        if (i3 == 3) {
            return new zbabp();
        }
        zbabm zbabmVar = null;
        if (i3 == 4) {
            return new zbabn(zbabmVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
