package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbajz extends zbuf implements zbvn {
    private static final zbajz zbb;
    private zbuk zbd = zbuf.z();

    static {
        zbajz zbajzVar = new zbajz();
        zbb = zbajzVar;
        zbuf.m(zbajz.class, zbajzVar);
    }

    private zbajz() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001$", new Object[]{"zbd"});
        }
        if (i3 == 3) {
            return new zbajz();
        }
        zbajx zbajxVar = null;
        if (i3 == 4) {
            return new zbajy(zbajxVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
