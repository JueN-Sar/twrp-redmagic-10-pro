package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaht extends zbuf implements zbvn {
    private static final zbaht zbb;
    private int zbd = 0;
    private Object zbe;
    private int zbf;
    private float zbg;

    static {
        zbaht zbahtVar = new zbaht();
        zbb = zbahtVar;
        zbuf.m(zbaht.class, zbahtVar);
    }

    private zbaht() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0004\u0001\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\f\u0002\u0001\u0003?\u0000\u0004Ȼ\u0000", new Object[]{"zbe", "zbd", "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbaht();
        }
        zbagx zbagxVar = null;
        if (i3 == 4) {
            return new zbahs(zbagxVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
