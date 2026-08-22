package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbqg extends zbuf implements zbvn {
    private static final zbqg zbb;
    private int zbd;
    private zbqe zbe;
    private double zbf;
    private boolean zbg;

    static {
        zbqg zbqgVar = new zbqg();
        zbb = zbqgVar;
        zbuf.m(zbqg.class, zbqgVar);
    }

    private zbqg() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002က\u0001\u0003ဇ\u0002", new Object[]{"zbd", "zbe", "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbqg();
        }
        zbpu zbpuVar = null;
        if (i3 == 4) {
            return new zbqf(zbpuVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
