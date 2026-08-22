package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbahd extends zbuf implements zbvn {
    private static final zbahd zbb;
    private zbun zbd = zbuf.C();

    static {
        zbahd zbahdVar = new zbahd();
        zbb = zbahdVar;
        zbuf.m(zbahd.class, zbahdVar);
    }

    private zbahd() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zbd", zbaid.class});
        }
        if (i3 == 3) {
            return new zbahd();
        }
        zbagx zbagxVar = null;
        if (i3 == 4) {
            return new zbahc(zbagxVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
