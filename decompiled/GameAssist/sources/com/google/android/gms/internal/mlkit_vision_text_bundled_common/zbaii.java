package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaii extends zbuf implements zbvn {
    private static final zbaii zbb;
    private int zbd;
    private boolean zbg;
    private boolean zbo;
    private float zbe = 0.05f;
    private float zbf = 0.5f;
    private int zbh = 10;
    private int zbi = 200;
    private float zbj = 0.8f;
    private int zbk = 4;
    private int zbl = 10;
    private float zbm = 0.2f;
    private float zbn = 0.1f;

    static {
        zbaii zbaiiVar = new zbaii();
        zbb = zbaiiVar;
        zbuf.m(zbaii.class, zbaiiVar);
    }

    private zbaii() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0000\u0000\u0001ခ\u0000\u0002ခ\u0001\u0003င\u0003\u0004င\u0004\u0005င\u0006\u0006င\u0007\u0007ခ\b\bခ\t\tဇ\n\nခ\u0005\u000bဇ\u0002", new Object[]{"zbd", "zbe", "zbf", "zbh", "zbi", "zbk", "zbl", "zbm", "zbn", "zbo", "zbj", "zbg"});
        }
        if (i3 == 3) {
            return new zbaii();
        }
        zbaig zbaigVar = null;
        if (i3 == 4) {
            return new zbaih(zbaigVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
