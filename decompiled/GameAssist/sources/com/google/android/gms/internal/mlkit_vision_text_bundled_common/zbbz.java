package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbbz extends zbuf implements zbvn {
    private static final zbbz zbb;
    private int zbd;
    private zbun zbe = zbuf.C();
    private zbbw zbf;

    static {
        zbbz zbbzVar = new zbbz();
        zbb = zbbzVar;
        zbuf.m(zbbz.class, zbbzVar);
    }

    private zbbz() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002ဉ\u0000", new Object[]{"zbd", "zbe", zbgr.class, "zbf"});
        }
        if (i3 == 3) {
            return new zbbz();
        }
        zbbx zbbxVar = null;
        if (i3 == 4) {
            return new zbby(zbbxVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
