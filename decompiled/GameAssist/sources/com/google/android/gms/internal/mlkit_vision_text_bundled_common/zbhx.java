package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbhx extends zbuf implements zbvn {
    private static final zbhx zbb;
    private zbuk zbd = zbuf.z();

    static {
        zbhx zbhxVar = new zbhx();
        zbb = zbhxVar;
        zbuf.m(zbhx.class, zbhxVar);
    }

    private zbhx() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001$", new Object[]{"zbd"});
        }
        if (i3 == 3) {
            return new zbhx();
        }
        zbhs zbhsVar = null;
        if (i3 == 4) {
            return new zbhw(zbhsVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
