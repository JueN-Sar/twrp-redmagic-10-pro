package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbdj extends zbuf implements zbvn {
    private static final zbdj zbb;
    private int zbd;
    private float zbe = 1.0f;
    private int zbf;

    static {
        zbdj zbdjVar = new zbdj();
        zbb = zbdjVar;
        zbuf.m(zbdj.class, zbdjVar);
    }

    private zbdj() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ခ\u0000\u0002င\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbdj();
        }
        zbdh zbdhVar = null;
        if (i3 == 4) {
            return new zbdi(zbdhVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
