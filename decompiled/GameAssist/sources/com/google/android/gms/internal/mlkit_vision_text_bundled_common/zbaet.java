package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaet extends zbuf implements zbvn {
    private static final zbaet zbb;
    private int zbd;
    private int zbe;
    private int zbf;
    private int zbg;
    private int zbh;
    private float zbi;
    private float zbj;
    private zbuk zbk = zbuf.z();
    private zbul zbl = zbuf.A();
    private zbul zbm = zbuf.A();

    static {
        zbaet zbaetVar = new zbaet();
        zbb = zbaetVar;
        zbuf.m(zbaet.class, zbaetVar);
    }

    private zbaet() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0003\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005ခ\u0004\u0006ခ\u0005\u0007$\b'\t'", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbi", "zbj", "zbk", "zbl", "zbm"});
        }
        if (i3 == 3) {
            return new zbaet();
        }
        zbaer zbaerVar = null;
        if (i3 == 4) {
            return new zbaes(zbaerVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
