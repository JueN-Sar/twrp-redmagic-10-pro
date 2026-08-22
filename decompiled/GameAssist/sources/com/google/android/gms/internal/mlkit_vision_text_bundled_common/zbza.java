package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbza extends zbuf implements zbvn {
    private static final zbza zbb;
    private int zbd;
    private String zbe = "";
    private long zbf;
    private long zbg;
    private long zbh;

    static {
        zbza zbzaVar = new zbza();
        zbb = zbzaVar;
        zbuf.m(zbza.class, zbzaVar);
    }

    private zbza() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbza();
        }
        zbwz zbwzVar = null;
        if (i3 == 4) {
            return new zbyz(zbwzVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
