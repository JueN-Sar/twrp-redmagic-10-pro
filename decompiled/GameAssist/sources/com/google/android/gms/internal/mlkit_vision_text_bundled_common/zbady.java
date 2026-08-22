package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbady extends zbuf implements zbvn {
    private static final zbady zbb;
    private int zbd;
    private Object zbf;
    private int zbe = 0;
    private String zbg = "";

    static {
        zbady zbadyVar = new zbady();
        zbb = zbadyVar;
        zbuf.m(zbady.class, zbadyVar);
    }

    private zbady() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0005\u0001\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဈ\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000", new Object[]{"zbf", "zbe", "zbd", "zbg", zbadp.class, zbadr.class, zbaee.class, zbadv.class});
        }
        if (i3 == 3) {
            return new zbady();
        }
        zbadn zbadnVar = null;
        if (i3 == 4) {
            return new zbadx(zbadnVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
