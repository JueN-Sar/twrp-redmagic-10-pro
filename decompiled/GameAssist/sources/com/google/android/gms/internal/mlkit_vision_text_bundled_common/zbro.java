package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbro extends zbuf implements zbvn {
    private static final zbro zbb;
    private int zbd;
    private int zbf;
    private int zbg;
    private zbrq zbj;
    private zbqw zbl;
    private zbqt zbm;
    private byte zbo = 2;
    private zbtc zbe = zbtc.zbb;
    private String zbh = "";
    private zbun zbi = zbuf.C();
    private zbun zbk = zbuf.C();
    private zbun zbn = zbuf.C();

    static {
        zbro zbroVar = new zbro();
        zbb = zbroVar;
        zbuf.m(zbro.class, zbroVar);
    }

    private zbro() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbo);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\n\u0000\u0001\u0001\n\n\u0000\u0003\u0004\u0001ᔊ\u0000\u0002ဈ\u0003\u0003Л\u0004ဉ\u0004\u0005Л\u0006ဉ\u0005\u0007ဉ\u0006\bЛ\tင\u0001\nင\u0002", new Object[]{"zbd", "zbe", "zbh", "zbi", zbqq.class, "zbj", "zbk", zbrm.class, "zbl", "zbm", "zbn", zbrk.class, "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbro();
        }
        zbpu zbpuVar = null;
        if (i3 == 4) {
            return new zbrn(zbpuVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbo = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
