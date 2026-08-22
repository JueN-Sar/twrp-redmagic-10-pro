package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbun;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbbc extends zbuf implements zbvn {
    private static final zbbc zbb;
    private int zbd;
    private zbun zbe = zbuf.C();
    private long zbf;

    static {
        zbbc zbbcVar = new zbbc();
        zbb = zbbcVar;
        zbuf.m(zbbc.class, zbbcVar);
    }

    private zbbc() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001a\u0002ဂ\u0000", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbbc();
        }
        zbba zbbaVar = null;
        if (i3 == 4) {
            return new zbbb(zbbaVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
