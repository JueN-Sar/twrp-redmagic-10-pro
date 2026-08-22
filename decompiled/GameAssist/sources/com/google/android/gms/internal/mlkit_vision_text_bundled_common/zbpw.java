package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbpw extends zbuf implements zbvn {
    private static final zbpw zbb;
    private int zbd;
    private int zbe;
    private int zbf;
    private int zbg;
    private int zbh;
    private float zbi;
    private zbqg zbj;
    private byte zbk = 2;

    static {
        zbpw zbpwVar = new zbpw();
        zbb = zbpwVar;
        zbuf.m(zbpw.class, zbpwVar);
    }

    private zbpw() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbk);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0004\u0001ᔄ\u0000\u0002ᔄ\u0001\u0003ᔄ\u0002\u0004ᔄ\u0003\u0005ခ\u0004\u0006ဉ\u0005", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbi", "zbj"});
        }
        if (i3 == 3) {
            return new zbpw();
        }
        zbpu zbpuVar = null;
        if (i3 == 4) {
            return new zbpv(zbpuVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbk = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
