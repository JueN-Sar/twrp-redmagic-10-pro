package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbnt extends zbuf implements zbvn {
    private static final zbnt zbb;
    private int zbd;
    private zbun zbe = zbuf.C();
    private int zbf;
    private int zbg;

    static {
        zbnt zbntVar = new zbnt();
        zbb = zbntVar;
        zbuf.m(zbnt.class, zbntVar);
    }

    private zbnt() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\u001b\u0002᠌\u0000\u0003᠌\u0001", new Object[]{"zbd", "zbe", zbnr.class, "zbf", zbns.f12874a, "zbg", zbnn.f12872a});
        }
        if (i3 == 3) {
            return new zbnt();
        }
        zbno zbnoVar = null;
        if (i3 == 4) {
            return new zbnp(zbnoVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
