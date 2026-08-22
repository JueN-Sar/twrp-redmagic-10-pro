package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbmk;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbun;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbeu extends zbuf implements zbvn {
    private static final zbeu zbb;
    private int zbd;
    private String zbe = "";
    private zbun zbf = zbuf.C();
    private zbun zbg = zbuf.C();
    private zbun zbh = zbuf.C();
    private com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbbe zbi;
    private zbmk zbj;

    static {
        zbeu zbeuVar = new zbeu();
        zbb = zbeuVar;
        zbuf.m(zbeu.class, zbeuVar);
    }

    private zbeu() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0003\u0000\u0001ဈ\u0000\u0002\u001a\u0003ဉ\u0001\u0004\u001a\u0005ဉ\u0002\u0006\u001a", new Object[]{"zbd", "zbe", "zbf", "zbi", "zbh", "zbj", "zbg"});
        }
        if (i3 == 3) {
            return new zbeu();
        }
        zbes zbesVar = null;
        if (i3 == 4) {
            return new zbet(zbesVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
