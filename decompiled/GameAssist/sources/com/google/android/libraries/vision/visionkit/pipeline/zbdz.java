package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbafq;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbix;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbdz extends zbuf implements zbvn {
    private static final zbdz zbb;
    private int zbd;
    private com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbbk zbe;
    private zbix zbf;
    private zbafq zbg;
    private zbix zbh;
    private byte zbi = 2;

    static {
        zbdz zbdzVar = new zbdz();
        zbb = zbdzVar;
        zbuf.m(zbdz.class, zbdzVar);
    }

    private zbdz() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbi);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0001\u0001ဉ\u0000\u0002ဉ\u0001\u0003ᐉ\u0002\u0004ဉ\u0003", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbdz();
        }
        zbdx zbdxVar = null;
        if (i3 == 4) {
            return new zbdy(zbdxVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbi = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
