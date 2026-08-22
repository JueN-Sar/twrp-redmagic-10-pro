package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbgo;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbjg;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbun;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbdg extends zbuf implements zbvn {
    private static final zbdg zbb;
    private int zbd;
    private zbgo zbe;
    private zbun zbf = zbuf.C();
    private zbun zbg = zbuf.C();

    static {
        zbdg zbdgVar = new zbdg();
        zbb = zbdgVar;
        zbuf.m(zbdg.class, zbdgVar);
    }

    private zbdg() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0002\u0000\u0001ဉ\u0000\u0002\u001b\u0003\u001b", new Object[]{"zbd", "zbe", "zbf", zbjg.class, "zbg", com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbcw.class});
        }
        if (i3 == 3) {
            return new zbdg();
        }
        zbde zbdeVar = null;
        if (i3 == 4) {
            return new zbdf(zbdeVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
