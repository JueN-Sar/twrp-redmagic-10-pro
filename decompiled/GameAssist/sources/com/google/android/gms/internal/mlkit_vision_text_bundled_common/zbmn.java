package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbmn extends zbuf implements zbvn {
    private static final zbmn zbb;
    private int zbd;
    private zbmp zbh;
    private byte zbi = 2;
    private String zbe = "";
    private String zbf = "";
    private String zbg = "";

    static {
        zbmn zbmnVar = new zbmn();
        zbb = zbmnVar;
        zbuf.m(zbmn.class, zbmnVar);
    }

    private zbmn() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbi);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001Ϫ\u0004\u0000\u0000\u0001\u0001ဈ\u0000\u0002ဈ\u0001\u0003ᐉ\u0003Ϫဈ\u0002", new Object[]{"zbd", "zbe", "zbf", "zbh", "zbg"});
        }
        if (i3 == 3) {
            return new zbmn();
        }
        zbml zbmlVar = null;
        if (i3 == 4) {
            return new zbmm(zbmlVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbi = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
