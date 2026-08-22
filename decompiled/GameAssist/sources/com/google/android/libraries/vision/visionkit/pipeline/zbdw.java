package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbdw extends zbuf implements zbvn {
    private static final zbdw zbb;
    private int zbd;
    private zbco zbe;
    private zbat zbf;
    private zbag zbg;
    private zbez zbh;
    private boolean zbi;
    private zbaw zbj;
    private zbcr zbk;
    private zbcf zbl;

    static {
        zbdw zbdwVar = new zbdw();
        zbb = zbdwVar;
        zbuf.m(zbdw.class, zbdwVar);
    }

    private zbdw() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\b\u0000\u0001\u0001\t\b\u0000\u0000\u0000\u0001ဉ\u0001\u0003ဉ\u0005\u0004ဉ\u0000\u0005ဉ\u0002\u0006ဉ\u0003\u0007ဇ\u0004\bဉ\u0006\tဉ\u0007", new Object[]{"zbd", "zbf", "zbj", "zbe", "zbg", "zbh", "zbi", "zbk", "zbl"});
        }
        if (i3 == 3) {
            return new zbdw();
        }
        zbdu zbduVar = null;
        if (i3 == 4) {
            return new zbdv(zbduVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
