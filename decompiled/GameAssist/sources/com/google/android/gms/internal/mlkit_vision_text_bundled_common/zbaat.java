package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaat extends zbub implements zbvn {
    private static final zbaat zbd;
    private int zbe;
    private Object zbg;
    private int zbi;
    private int zbj;
    private int zbk;
    private int zbf = 0;
    private byte zbl = 2;
    private String zbh = "";

    static {
        zbaat zbaatVar = new zbaat();
        zbd = zbaatVar;
        zbuf.m(zbaat.class, zbaatVar);
    }

    private zbaat() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbl);
        }
        if (i3 == 2) {
            return zbuf.j(zbd, "\u0001\u0007\u0001\u0001\u0001\b\u0007\u0000\u0000\u0000\u0001ဈ\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0006=\u0000\u0007=\u0000\b6\u0000", new Object[]{"zbg", "zbf", "zbe", "zbh", "zbi", "zbj", "zbk"});
        }
        if (i3 == 3) {
            return new zbaat();
        }
        zbaad zbaadVar = null;
        if (i3 == 4) {
            return new zbaas(zbaadVar);
        }
        if (i3 == 5) {
            return zbd;
        }
        this.zbl = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
