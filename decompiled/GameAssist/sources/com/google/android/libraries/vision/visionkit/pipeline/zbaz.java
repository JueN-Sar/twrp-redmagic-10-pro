package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbadm;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbun;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbaz extends zbuf implements zbvn {
    private static final zbaz zbb;
    private Object zbe;
    private int zbd = 0;
    private byte zbg = 2;
    private zbun zbf = zbuf.C();

    static {
        zbaz zbazVar = new zbaz();
        zbb = zbazVar;
        zbuf.m(zbaz.class, zbazVar);
    }

    private zbaz() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbg);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\u0002\u0001\u0000\u0001\u0003\u0002\u0000\u0001\u0001\u0001:\u0000\u0003Л", new Object[]{"zbe", "zbd", "zbf", zbadm.class});
        }
        if (i3 == 3) {
            return new zbaz();
        }
        zbax zbaxVar = null;
        if (i3 == 4) {
            return new zbay(zbaxVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbg = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
