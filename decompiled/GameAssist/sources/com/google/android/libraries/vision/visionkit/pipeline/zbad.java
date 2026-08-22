package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbun;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbad extends zbuf<zbad, zbac> implements zbvn {
    private static final zbad zbb;
    private int zbd;
    private zbun zbe = zbuf.C();
    private String zbf = "";

    static {
        zbad zbadVar = new zbad();
        zbb = zbadVar;
        zbuf.m(zbad.class, zbadVar);
    }

    private zbad() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002ဈ\u0000", new Object[]{"zbd", "zbe", zbaj.class, "zbf"});
        }
        if (i3 == 3) {
            return new zbad();
        }
        zbab zbabVar = null;
        if (i3 == 4) {
            return new zbac(zbabVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
