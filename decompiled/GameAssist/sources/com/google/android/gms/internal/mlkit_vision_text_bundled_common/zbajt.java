package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbajt extends zbuf implements zbvn {
    private static final zbajt zbb;
    private int zbd;
    private int zbe;
    private int zbf;
    private String zbg = "";

    static {
        zbajt zbajtVar = new zbajt();
        zbb = zbajtVar;
        zbuf.m(zbajt.class, zbajtVar);
    }

    private zbajt() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000b\u0002\u000b\u0003\u000b\u0004Ȉ", new Object[]{"zbd", "zbe", "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbajt();
        }
        zbajr zbajrVar = null;
        if (i3 == 4) {
            return new zbajs(zbajrVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
