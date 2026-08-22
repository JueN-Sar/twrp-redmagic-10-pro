package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbqt extends zbuf implements zbvn {
    private static final zbqt zbb;
    private int zbd;
    private int zbe;
    private String zbf = "";
    private zbul zbg = zbuf.A();

    static {
        zbqt zbqtVar = new zbqt();
        zbb = zbqtVar;
        zbuf.m(zbqt.class, zbqtVar);
    }

    private zbqt() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဈ\u0001\u0002င\u0000\u0003ࠞ", new Object[]{"zbd", "zbf", "zbe", "zbg", zbqu.f12927a});
        }
        if (i3 == 3) {
            return new zbqt();
        }
        zbpu zbpuVar = null;
        if (i3 == 4) {
            return new zbqs(zbpuVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
