package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbaiv;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbu extends zbuf implements zbvn {
    private static final zbu zbb;
    private int zbd;
    private zbaiv zbe;
    private int zbg;
    private String zbf = "";
    private int zbh = 93;

    static {
        zbu zbuVar = new zbu();
        zbb = zbuVar;
        zbuf.m(zbu.class, zbuVar);
    }

    private zbu() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဈ\u0001\u0003င\u0002\u0004င\u0003", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbu();
        }
        zbs zbsVar = null;
        if (i3 == 4) {
            return new zbt(zbsVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
