package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbahp extends zbuf implements zbvn {
    private static final zbahp zbb;
    private int zbd = 0;
    private Object zbe;
    private float zbf;

    static {
        zbahp zbahpVar = new zbahp();
        zbb = zbahpVar;
        zbuf.m(zbahp.class, zbahpVar);
    }

    private zbahp() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001?\u0000\u0002Ȼ\u0000\u0003\u0001", new Object[]{"zbe", "zbd", "zbf"});
        }
        if (i3 == 3) {
            return new zbahp();
        }
        zbagx zbagxVar = null;
        if (i3 == 4) {
            return new zbaho(zbagxVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
