package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbyy extends zbuf implements zbvn {
    private static final zbyy zbb;
    private int zbd;
    private zbun zbe = zbuf.C();
    private zbza zbf;
    private zbxh zbg;

    static {
        zbyy zbyyVar = new zbyy();
        zbb = zbyyVar;
        zbuf.m(zbyy.class, zbyyVar);
    }

    private zbyy() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\u001b\u0002ဉ\u0000\u0003ဉ\u0001", new Object[]{"zbd", "zbe", zbzo.class, "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbyy();
        }
        zbwz zbwzVar = null;
        if (i3 == 4) {
            return new zbyx(zbwzVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
