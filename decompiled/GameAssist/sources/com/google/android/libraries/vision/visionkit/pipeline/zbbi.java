package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbbi extends zbuf implements zbvn {
    private static final zbbi zbb;
    private int zbd;
    private com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbee zbe;
    private com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbek zbf;
    private com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbeh zbg;

    static {
        zbbi zbbiVar = new zbbi();
        zbb = zbbiVar;
        zbuf.m(zbbi.class, zbbiVar);
    }

    private zbbi() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\u0003\u0000\u0001\u0001\u0004\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0004ဉ\u0002", new Object[]{"zbd", "zbe", "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbbi();
        }
        zbbg zbbgVar = null;
        if (i3 == 4) {
            return new zbbh(zbbgVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
