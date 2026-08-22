package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbun;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbey extends zbuf implements zbvn {
    private static final zbey zbb;
    private int zbd;
    private String zbe = "";
    private zbun zbf = zbuf.C();
    private boolean zbg;

    static {
        zbey zbeyVar = new zbey();
        zbb = zbeyVar;
        zbuf.m(zbey.class, zbeyVar);
    }

    private zbey() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001a\u0003ဇ\u0001", new Object[]{"zbd", "zbe", "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbey();
        }
        zbev zbevVar = null;
        if (i3 == 4) {
            return new zbex(zbevVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
