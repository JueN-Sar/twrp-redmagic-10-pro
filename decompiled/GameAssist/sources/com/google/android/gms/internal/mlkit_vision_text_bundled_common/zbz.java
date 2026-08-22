package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbz extends zbuf implements zbvn {
    private static final zbz zbb;
    private zbun zbd = zbuf.C();

    static {
        zbz zbzVar = new zbz();
        zbb = zbzVar;
        zbuf.m(zbz.class, zbzVar);
    }

    private zbz() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zbd", zbac.class});
        }
        if (i3 == 3) {
            return new zbz();
        }
        zbx zbxVar = null;
        if (i3 == 4) {
            return new zby(zbxVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
