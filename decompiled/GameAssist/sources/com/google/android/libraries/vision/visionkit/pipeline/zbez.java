package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbun;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbez extends zbuf implements zbvn {
    private static final zbez zbb;
    private zbun zbd = zbuf.C();

    static {
        zbez zbezVar = new zbez();
        zbb = zbezVar;
        zbuf.m(zbez.class, zbezVar);
    }

    private zbez() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zbd", zbey.class});
        }
        if (i3 == 3) {
            return new zbez();
        }
        zbev zbevVar = null;
        if (i3 == 4) {
            return new zbew(zbevVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
