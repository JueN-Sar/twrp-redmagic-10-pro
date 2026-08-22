package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbfu extends zbuf implements zbvn {
    private static final zbfu zbb;
    private zbun zbd = zbuf.C();

    static {
        zbfu zbfuVar = new zbfu();
        zbb = zbfuVar;
        zbuf.m(zbfu.class, zbfuVar);
    }

    private zbfu() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zbd", zbfr.class});
        }
        if (i3 == 3) {
            return new zbfu();
        }
        zbfs zbfsVar = null;
        if (i3 == 4) {
            return new zbft(zbfsVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
