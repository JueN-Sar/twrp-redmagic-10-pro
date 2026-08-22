package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbalp extends zbuf implements zbvn {
    private static final zbalp zbb;
    private int zbd;
    private zbhl zbe;
    private zbxb zbf;

    static {
        zbalp zbalpVar = new zbalp();
        zbb = zbalpVar;
        zbuf.m(zbalp.class, zbalpVar);
    }

    private zbalp() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbalp();
        }
        zbaln zbalnVar = null;
        if (i3 == 4) {
            return new zbalo(zbalnVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
