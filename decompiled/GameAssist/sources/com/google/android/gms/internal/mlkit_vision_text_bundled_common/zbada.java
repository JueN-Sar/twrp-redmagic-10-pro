package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbada extends zbuf implements zbvn {
    private static final zbada zbb;
    private int zbd;
    private float zbe;
    private float zbf;
    private float zbg;
    private float zbh;
    private float zbi;
    private float zbj;
    private zbact zbk;
    private int zbl;
    private int zbm;
    private float zbn;

    static {
        zbada zbadaVar = new zbada();
        zbb = zbadaVar;
        zbuf.m(zbada.class, zbadaVar);
    }

    private zbada() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\n\u0000\u0001\u0001\u000b\n\u0000\u0000\u0000\u0001ခ\u0000\u0002ခ\u0001\u0003ခ\u0002\u0004᠌\u0007\u0005᠌\b\u0006ခ\t\u0007ဉ\u0006\tခ\u0003\nခ\u0004\u000bခ\u0005", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbl", zbacy.f12663a, "zbm", zbacz.f12664a, "zbn", "zbk", "zbh", "zbi", "zbj"});
        }
        if (i3 == 3) {
            return new zbada();
        }
        zbacp zbacpVar = null;
        if (i3 == 4) {
            return new zbacx(zbacpVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
