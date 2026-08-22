package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbalm extends zbuf implements zbvn {
    private static final zbalm zbb;

    static {
        zbalm zbalmVar = new zbalm();
        zbb = zbalmVar;
        zbuf.m(zbalm.class, zbalmVar);
    }

    private zbalm() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        zbalk zbalkVar = null;
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0000", null);
        }
        if (i3 == 3) {
            return new zbalm();
        }
        if (i3 == 4) {
            return new zball(zbalkVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
