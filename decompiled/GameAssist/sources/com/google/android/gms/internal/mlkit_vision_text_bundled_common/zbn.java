package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbn extends zbuf implements zbvn {
    private static final zbn zbb;
    private int zbd;
    private float zbf;
    private String zbe = "";
    private zbun zbg = zbuf.C();
    private zbun zbh = zbuf.C();

    static {
        zbn zbnVar = new zbn();
        zbb = zbnVar;
        zbuf.m(zbn.class, zbnVar);
    }

    private zbn() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\u0005\u0000\u0000\u0001\u007f\u0005\u0000\u0002\u0000\u0001\f\u0002Ȉ\u0003\u0001\u0004Ț\u007fȚ", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbn();
        }
        zbl zblVar = null;
        if (i3 == 4) {
            return new zbm(zblVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
