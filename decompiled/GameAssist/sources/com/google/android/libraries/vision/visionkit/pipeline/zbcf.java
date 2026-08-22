package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbcf extends zbuf implements zbvn {
    private static final zbcf zbb;
    private int zbd;
    private boolean zbe;
    private String zbf = "";

    static {
        zbcf zbcfVar = new zbcf();
        zbb = zbcfVar;
        zbuf.m(zbcf.class, zbcfVar);
    }

    private zbcf() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဈ\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbcf();
        }
        zbcd zbcdVar = null;
        if (i3 == 4) {
            return new zbce(zbcdVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
