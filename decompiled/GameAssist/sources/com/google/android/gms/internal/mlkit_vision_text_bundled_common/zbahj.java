package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbahj extends zbuf implements zbvn {
    private static final zbahj zbb;
    private int zbd;
    private float zbe;

    static {
        zbahj zbahjVar = new zbahj();
        zbb = zbahjVar;
        zbuf.m(zbahj.class, zbahjVar);
    }

    private zbahj() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002\u0001", new Object[]{"zbd", "zbe"});
        }
        if (i3 == 3) {
            return new zbahj();
        }
        zbagx zbagxVar = null;
        if (i3 == 4) {
            return new zbahi(zbagxVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
