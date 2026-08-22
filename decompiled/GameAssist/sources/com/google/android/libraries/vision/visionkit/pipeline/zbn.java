package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbail;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbix;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbn extends zbuf implements zbvn {
    private static final zbn zbb;
    private int zbd;
    private int zbe = 0;
    private Object zbf;
    private zbix zbg;

    static {
        zbn zbnVar = new zbn();
        zbb = zbnVar;
        zbuf.m(zbn.class, zbnVar);
    }

    private zbn() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\u0003\u0001\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002<\u0000\u0003<\u0000", new Object[]{"zbf", "zbe", "zbd", "zbg", com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbbq.class, zbail.class});
        }
        if (i3 == 3) {
            return new zbn();
        }
        zbl zblVar = null;
        if (i3 == 4) {
            return new zbm(zblVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
