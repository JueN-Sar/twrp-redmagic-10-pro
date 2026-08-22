package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbaiv;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbx extends zbuf implements zbvn {
    private static final zbx zbb;
    private int zbd;
    private zbaiv zbe;
    private String zbf = "";
    private boolean zbg;
    private zbeh zbh;

    static {
        zbx zbxVar = new zbx();
        zbb = zbxVar;
        zbuf.m(zbx.class, zbxVar);
    }

    private zbx() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဈ\u0001\u0003ဇ\u0002\u0004ဉ\u0003", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbx();
        }
        zbv zbvVar = null;
        if (i3 == 4) {
            return new zbw(zbvVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
