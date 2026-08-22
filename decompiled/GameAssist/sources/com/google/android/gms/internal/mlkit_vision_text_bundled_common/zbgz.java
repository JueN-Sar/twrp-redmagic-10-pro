package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbgz extends zbuf implements zbvn {
    private static final zbgz zbb;
    private int zbd;
    private int zbe;
    private float zbf;
    private String zbg = "";
    private String zbh = "";

    static {
        zbgz zbgzVar = new zbgz();
        zbb = zbgzVar;
        zbuf.m(zbgz.class, zbgzVar);
    }

    private zbgz() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ခ\u0001\u0003ဈ\u0002\u0004ဈ\u0003", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbgz();
        }
        zbgx zbgxVar = null;
        if (i3 == 4) {
            return new zbgy(zbgxVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
