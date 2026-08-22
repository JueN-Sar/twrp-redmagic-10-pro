package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbj extends zbuf implements zbvn {
    private static final zbj zbb;
    private int zbd;
    private zbeo zbe;

    static {
        zbj zbjVar = new zbj();
        zbb = zbjVar;
        zbuf.m(zbj.class, zbjVar);
    }

    private zbj() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"zbd", "zbe"});
        }
        if (i3 == 3) {
            return new zbj();
        }
        zbh zbhVar = null;
        if (i3 == 4) {
            return new zbi(zbhVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
