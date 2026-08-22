package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbyg extends zbuf implements zbvn {
    private static final zbyg zbb;
    private int zbd;
    private boolean zbe;
    private boolean zbf;

    static {
        zbyg zbygVar = new zbyg();
        zbb = zbygVar;
        zbuf.m(zbyg.class, zbygVar);
    }

    private zbyg() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0005\u0006\u0002\u0000\u0000\u0000\u0005ဇ\u0000\u0006ဇ\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbyg();
        }
        zbwz zbwzVar = null;
        if (i3 == 4) {
            return new zbyf(zbwzVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
