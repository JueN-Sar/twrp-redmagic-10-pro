package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbdj extends zbuf implements zbvn {
    private static final zbdj zbb;
    private int zbd;
    private boolean zbe;

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
            return zbuf.j(zbb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဇ\u0000", new Object[]{"zbd", "zbe"});
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
