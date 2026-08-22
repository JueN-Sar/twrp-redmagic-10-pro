package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaku extends zbuf implements zbvn {
    private static final zbaku zbb;
    private int zbd;
    private zbbt zbe;
    private zbun zbf = zbuf.C();

    static {
        zbaku zbakuVar = new zbaku();
        zbb = zbakuVar;
        zbuf.m(zbaku.class, zbakuVar);
    }

    private zbaku() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဉ\u0000\u0002\u001b", new Object[]{"zbd", "zbe", "zbf", zbajh.class});
        }
        if (i3 == 3) {
            return new zbaku();
        }
        zbaks zbaksVar = null;
        if (i3 == 4) {
            return new zbakt(zbaksVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
