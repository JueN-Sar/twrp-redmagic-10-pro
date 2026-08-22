package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbmx extends zbuf implements zbvn {
    private static final zbmx zbb;
    private int zbd;
    private zbmd zbh;
    private byte zbi = 2;
    private String zbe = "";
    private zbun zbf = zbuf.C();
    private zbun zbg = zbuf.C();

    static {
        zbmx zbmxVar = new zbmx();
        zbb = zbmxVar;
        zbuf.m(zbmx.class, zbmxVar);
    }

    private zbmx() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbi);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001Ϫ\u0004\u0000\u0002\u0001\u0001ဈ\u0000\u0002\u001a\u0003ᐉ\u0001Ϫ\u001a", new Object[]{"zbd", "zbe", "zbf", "zbh", "zbg"});
        }
        if (i3 == 3) {
            return new zbmx();
        }
        zbmv zbmvVar = null;
        if (i3 == 4) {
            return new zbmw(zbmvVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbi = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
