package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbbp extends zbuf implements zbvn {
    private static final zbbp zbb;
    private int zbd;
    private int zbe;
    private com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbdg zbf;
    private zbu zbg;

    static {
        zbbp zbbpVar = new zbbp();
        zbb = zbbpVar;
        zbuf.m(zbbp.class, zbbpVar);
    }

    private zbbp() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zbd", "zbe", zbbo.f13775a, "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbbp();
        }
        zbbm zbbmVar = null;
        if (i3 == 4) {
            return new zbbn(zbbmVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
