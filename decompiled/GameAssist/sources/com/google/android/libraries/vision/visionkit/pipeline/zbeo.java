package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbeo extends zbuf implements zbvn {
    private static final zbeo zbb;
    private int zbd = 0;
    private Object zbe;

    static {
        zbeo zbeoVar = new zbeo();
        zbb = zbeoVar;
        zbuf.m(zbeo.class, zbeoVar);
    }

    private zbeo() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000", new Object[]{"zbe", "zbd", zbbc.class, zbbw.class});
        }
        if (i3 == 3) {
            return new zbeo();
        }
        zbem zbemVar = null;
        if (i3 == 4) {
            return new zben(zbemVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
