package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbcl extends zbuf implements zbvn {
    private static final zbcl zbb;
    private int zbd;
    private zbbz zbe;
    private zbuk zbf = zbuf.z();

    static {
        zbcl zbclVar = new zbcl();
        zbb = zbclVar;
        zbuf.m(zbcl.class, zbclVar);
    }

    private zbcl() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဉ\u0000\u0002\u0013", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbcl();
        }
        zbcj zbcjVar = null;
        if (i3 == 4) {
            return new zbck(zbcjVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
