package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbix;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbun;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbaa extends zbuf implements zbvn {
    private static final zbaa zbb;
    private int zbd;
    private int zbf;
    private zbun zbe = zbuf.C();
    private zbun zbg = zbuf.C();

    static {
        zbaa zbaaVar = new zbaa();
        zbb = zbaaVar;
        zbuf.m(zbaa.class, zbaaVar);
    }

    private zbaa() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0002\u0000\u0001\u001b\u0002င\u0000\u0003\u001a", new Object[]{"zbd", "zbe", zbix.class, "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbaa();
        }
        zby zbyVar = null;
        if (i3 == 4) {
            return new zbz(zbyVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
