package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbg extends zbuf implements zbvn {
    private static final zbg zbb;
    private int zbd;
    private int zbe;
    private zbdd zbf;

    static {
        zbg zbgVar = new zbg();
        zbb = zbgVar;
        zbuf.m(zbg.class, zbgVar);
    }

    private zbg() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002ဉ\u0001", new Object[]{"zbd", "zbe", zbk.f13807a, "zbf"});
        }
        if (i3 == 3) {
            return new zbg();
        }
        zbe zbeVar = null;
        if (i3 == 4) {
            return new zbf(zbeVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
