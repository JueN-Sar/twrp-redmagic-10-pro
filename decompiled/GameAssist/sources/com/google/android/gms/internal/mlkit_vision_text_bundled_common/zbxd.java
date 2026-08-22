package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbxd extends zbuf implements zbvn {
    private static final zbxd zbb;
    private zbun zbd = zbuf.C();

    static {
        zbxd zbxdVar = new zbxd();
        zbb = zbxdVar;
        zbuf.m(zbxd.class, zbxdVar);
    }

    private zbxd() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zbd", zbxf.class});
        }
        if (i3 == 3) {
            return new zbxd();
        }
        zbwz zbwzVar = null;
        if (i3 == 4) {
            return new zbxc(zbwzVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
