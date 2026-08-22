package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbcw extends zbuf implements zbvn {
    private static final zbcw zbb;
    private int zbd;
    private int zbe;
    private int zbf = 2;
    private String zbg = "";

    static {
        zbcw zbcwVar = new zbcw();
        zbb = zbcwVar;
        zbuf.m(zbcw.class, zbcwVar);
    }

    private zbcw() {
    }

    public static zbct E() {
        return (zbct) zbb.u();
    }

    static /* synthetic */ void H(zbcw zbcwVar, int i2) {
        zbcwVar.zbe = i2 - 1;
        zbcwVar.zbd |= 1;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001\u0003ဈ\u0002", new Object[]{"zbd", "zbe", zbcu.f13784a, "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbcw();
        }
        zbcs zbcsVar = null;
        if (i3 == 4) {
            return new zbct(zbcsVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
