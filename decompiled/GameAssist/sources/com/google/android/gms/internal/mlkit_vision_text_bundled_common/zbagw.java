package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbagw extends zbuf implements zbvn {
    private static final zbagw zbb;
    private int zbd;
    private int zbe;
    private int zbf;
    private int zbg;
    private int zbh;
    private byte zbi = 2;

    static {
        zbagw zbagwVar = new zbagw();
        zbb = zbagwVar;
        zbuf.m(zbagw.class, zbagwVar);
    }

    private zbagw() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbi);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0004\u0001ᔄ\u0000\u0002ᔄ\u0001\u0003ᔄ\u0002\u0004ᔄ\u0003", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbagw();
        }
        zbagu zbaguVar = null;
        if (i3 == 4) {
            return new zbagv(zbaguVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbi = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
