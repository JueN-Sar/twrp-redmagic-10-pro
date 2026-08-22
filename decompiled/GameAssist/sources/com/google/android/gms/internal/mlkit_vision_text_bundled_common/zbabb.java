package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbabb extends zbuf implements zbvn {
    private static final zbabb zbb;
    private int zbd;
    private int zbe;
    private int zbf;

    static {
        zbabb zbabbVar = new zbabb();
        zbb = zbabbVar;
        zbuf.m(zbabb.class, zbabbVar);
    }

    private zbabb() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbabb();
        }
        zbaad zbaadVar = null;
        if (i3 == 4) {
            return new zbaba(zbaadVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
