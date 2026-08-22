package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaki extends zbuf implements zbvn {
    private static final zbaki zbb;
    private int zbd;
    private zbakx zbe;
    private double zbf;

    static {
        zbaki zbakiVar = new zbaki();
        zbb = zbakiVar;
        zbuf.m(zbaki.class, zbakiVar);
    }

    private zbaki() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002\u0000", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbaki();
        }
        zbakg zbakgVar = null;
        if (i3 == 4) {
            return new zbakh(zbakgVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
