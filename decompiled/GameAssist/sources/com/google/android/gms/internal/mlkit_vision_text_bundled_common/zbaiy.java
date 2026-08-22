package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaiy extends zbuf implements zbvn {
    private static final zbaiy zbb;
    private int zbd = 0;
    private Object zbe;

    static {
        zbaiy zbaiyVar = new zbaiy();
        zbb = zbaiyVar;
        zbuf.m(zbaiy.class, zbaiyVar);
    }

    private zbaiy() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0003\u0001\u0000\u0002\u0004\u0003\u0000\u0000\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000", new Object[]{"zbe", "zbd", zbakr.class, zbakc.class, zbalm.class});
        }
        if (i3 == 3) {
            return new zbaiy();
        }
        zbaiw zbaiwVar = null;
        if (i3 == 4) {
            return new zbaix(zbaiwVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
