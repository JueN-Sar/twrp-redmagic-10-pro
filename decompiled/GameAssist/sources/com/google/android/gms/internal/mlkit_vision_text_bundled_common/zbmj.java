package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbmj extends zbuf implements zbvn {
    private static final zbmj zbb;
    private int zbd;
    private String zbe = "";
    private zbmg zbf;

    static {
        zbmj zbmjVar = new zbmj();
        zbb = zbmjVar;
        zbuf.m(zbmj.class, zbmjVar);
    }

    private zbmj() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဉ\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbmj();
        }
        zbme zbmeVar = null;
        if (i3 == 4) {
            return new zbmi(zbmeVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
