package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbnm extends zbuf implements zbvn {
    private static final zbnm zbb;
    private int zbd;
    private zbnt zbe;
    private float zbf;
    private int zbg;
    private boolean zbh;

    static {
        zbnm zbnmVar = new zbnm();
        zbb = zbnmVar;
        zbuf.m(zbnm.class, zbnmVar);
    }

    private zbnm() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ခ\u0001\u0003᠌\u0002\u0004ဇ\u0003", new Object[]{"zbd", "zbe", "zbf", "zbg", zbnl.f12871a, "zbh"});
        }
        if (i3 == 3) {
            return new zbnm();
        }
        zbnj zbnjVar = null;
        if (i3 == 4) {
            return new zbnk(zbnjVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
