package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaia extends zbuf implements zbvn {
    private static final zbaia zbb;
    private zbun zbd = zbuf.C();
    private zbun zbe = zbuf.C();
    private zbun zbf = zbuf.C();
    private zbun zbg = zbuf.C();

    static {
        zbaia zbaiaVar = new zbaia();
        zbb = zbaiaVar;
        zbuf.m(zbaia.class, zbaiaVar);
    }

    private zbaia() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0004\u0000\u0001\u001b\u0002\u001b\u0003\u001b\u0004\u001b", new Object[]{"zbd", zbahm.class, "zbe", zbahb.class, "zbf", zbaif.class, "zbg", zbahy.class});
        }
        if (i3 == 3) {
            return new zbaia();
        }
        zbagx zbagxVar = null;
        if (i3 == 4) {
            return new zbahz(zbagxVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
