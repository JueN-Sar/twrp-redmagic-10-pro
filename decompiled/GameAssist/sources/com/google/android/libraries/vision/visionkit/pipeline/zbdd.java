package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbdd extends zbuf implements zbvn {
    private static final zbdd zbb;
    private int zbd;
    private int zbe = 1;
    private int zbf = 5;

    static {
        zbdd zbddVar = new zbdd();
        zbb = zbddVar;
        zbuf.m(zbdd.class, zbddVar);
    }

    private zbdd() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001", new Object[]{"zbd", "zbe", zbdc.f13787a, "zbf"});
        }
        if (i3 == 3) {
            return new zbdd();
        }
        zbda zbdaVar = null;
        if (i3 == 4) {
            return new zbdb(zbdaVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
