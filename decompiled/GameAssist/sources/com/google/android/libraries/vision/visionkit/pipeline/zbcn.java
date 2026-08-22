package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbcn extends zbuf implements zbvn {
    private static final zbcn zbb;
    private int zbd;
    private Object zbf;
    private int zbe = 0;
    private String zbg = "";

    static {
        zbcn zbcnVar = new zbcn();
        zbb = zbcnVar;
        zbuf.m(zbcn.class, zbcnVar);
    }

    private zbcn() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0001\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u00025\u0000\u0003<\u0000", new Object[]{"zbf", "zbe", "zbd", "zbg", zbcm.class});
        }
        if (i3 == 3) {
            return new zbcn();
        }
        zbcg zbcgVar = null;
        if (i3 == 4) {
            return new zbci(zbcgVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
