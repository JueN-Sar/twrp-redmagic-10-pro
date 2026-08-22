package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbas extends zbuf implements zbvn {
    private static final zbas zbb;
    private int zbd;
    private float zbe;
    private long zbf;
    private long zbg;
    private long zbh;

    static {
        zbas zbasVar = new zbas();
        zbb = zbasVar;
        zbuf.m(zbas.class, zbasVar);
    }

    private zbas() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ခ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbas();
        }
        zban zbanVar = null;
        if (i3 == 4) {
            return new zbar(zbanVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
