package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbxo extends zbuf implements zbvn {
    private static final zbxo zbb;
    private int zbd;
    private String zbe = "";
    private int zbf = 1;
    private boolean zbg;
    private int zbh;

    static {
        zbxo zbxoVar = new zbxo();
        zbb = zbxoVar;
        zbuf.m(zbxo.class, zbxoVar);
    }

    private zbxo() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002᠌\u0001\u0003ဇ\u0002\u0004င\u0003", new Object[]{"zbd", "zbe", "zbf", zbxn.f13067a, "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbxo();
        }
        zbwz zbwzVar = null;
        if (i3 == 4) {
            return new zbxm(zbwzVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
