package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbun;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbaq extends zbuf implements zbvn {
    private static final zbaq zbb;
    private int zbd;
    private String zbe = "";
    private zbun zbf = zbuf.C();
    private zbun zbg = zbuf.C();
    private long zbh;

    static {
        zbaq zbaqVar = new zbaq();
        zbb = zbaqVar;
        zbuf.m(zbaq.class, zbaqVar);
    }

    private zbaq() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0002\u0000\u0001ဈ\u0000\u0002\u001a\u0003\u001b\u0004ဂ\u0001", new Object[]{"zbd", "zbe", "zbf", "zbg", zbas.class, "zbh"});
        }
        if (i3 == 3) {
            return new zbaq();
        }
        zban zbanVar = null;
        if (i3 == 4) {
            return new zbap(zbanVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
