package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbxx extends zbuf implements zbvn {
    private static final zbxx zbb;
    private int zbd;
    private int zbe;
    private long zbf;

    static {
        zbxx zbxxVar = new zbxx();
        zbb = zbxxVar;
        zbuf.m(zbxx.class, zbxxVar);
    }

    private zbxx() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002ဂ\u0001", new Object[]{"zbd", "zbe", zbxy.f13071a, "zbf"});
        }
        if (i3 == 3) {
            return new zbxx();
        }
        zbwz zbwzVar = null;
        if (i3 == 4) {
            return new zbxw(zbwzVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
