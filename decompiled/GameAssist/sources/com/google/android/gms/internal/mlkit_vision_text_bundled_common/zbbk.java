package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbbk extends zbuf implements zbvn {
    private static final zbbk zbb;
    private int zbd;
    private int zbe;
    private int zbf;
    private int zbg;
    private boolean zbh;
    private int zbi;
    private boolean zbj;
    private int zbk;

    static {
        zbbk zbbkVar = new zbbk();
        zbb = zbbkVar;
        zbuf.m(zbbk.class, zbbkVar);
    }

    private zbbk() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004ဇ\u0003\u0005င\u0004\u0006ဇ\u0005\u0007င\u0006", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbi", "zbj", "zbk"});
        }
        if (i3 == 3) {
            return new zbbk();
        }
        zbbi zbbiVar = null;
        if (i3 == 4) {
            return new zbbj(zbbiVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
