package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbafq;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbja;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbel extends zbuf implements zbvn {
    private static final zbel zbb;
    private int zbd;
    private int zbe;
    private zbafq zbf;
    private zbja zbg;
    private com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbdy zbh;
    private zbx zbi;
    private byte zbj = 2;

    static {
        zbel zbelVar = new zbel();
        zbb = zbelVar;
        zbuf.m(zbel.class, zbelVar);
    }

    private zbel() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbj);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0001\u0001ဉ\u0002\u0002ဉ\u0003\u0003᠌\u0000\u0004ဉ\u0004\u0005ᐉ\u0001", new Object[]{"zbd", "zbg", "zbh", "zbe", zbek.f13800a, "zbi", "zbf"});
        }
        if (i3 == 3) {
            return new zbel();
        }
        zbei zbeiVar = null;
        if (i3 == 4) {
            return new zbej(zbeiVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbj = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
