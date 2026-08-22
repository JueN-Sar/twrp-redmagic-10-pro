package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbag extends zbuf implements zbvn {
    private static final zbag zbb;
    private int zbd;
    private long zbe;
    private float zbf = 0.5f;

    static {
        zbag zbagVar = new zbag();
        zbb = zbagVar;
        zbuf.m(zbag.class, zbagVar);
    }

    private zbag() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဂ\u0000\u0002ခ\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbag();
        }
        zbae zbaeVar = null;
        if (i3 == 4) {
            return new zbaf(zbaeVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
