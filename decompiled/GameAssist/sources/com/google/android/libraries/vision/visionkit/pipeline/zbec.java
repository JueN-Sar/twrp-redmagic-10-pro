package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbec extends zbuf implements zbvn {
    private static final zbec zbb;
    private int zbd;
    private com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbbe zbe;
    private com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbbk zbf;
    private zbdz zbg;
    private boolean zbh;
    private byte zbi = 2;

    static {
        zbec zbecVar = new zbec();
        zbb = zbecVar;
        zbuf.m(zbec.class, zbecVar);
    }

    private zbec() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbi);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0001\u0001ဉ\u0000\u0002ဇ\u0003\u0003ᐉ\u0002\u0004ဉ\u0001", new Object[]{"zbd", "zbe", "zbh", "zbg", "zbf"});
        }
        if (i3 == 3) {
            return new zbec();
        }
        zbea zbeaVar = null;
        if (i3 == 4) {
            return new zbeb(zbeaVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbi = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
