package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbis extends zbuf implements zbvn {
    private static final zbis zbb;
    private int zbd;
    private zbun zbe = zbuf.C();
    private int zbf;

    static {
        zbis zbisVar = new zbis();
        zbb = zbisVar;
        zbuf.m(zbis.class, zbisVar);
    }

    private zbis() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002င\u0000", new Object[]{"zbd", "zbe", zbgz.class, "zbf"});
        }
        if (i3 == 3) {
            return new zbis();
        }
        zbiq zbiqVar = null;
        if (i3 == 4) {
            return new zbir(zbiqVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
