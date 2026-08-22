package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbala extends zbuf implements zbvn {
    private static final zbala zbb;
    private long zbe;
    private long zbf;
    private zbtc zbd = zbtc.zbb;
    private zbun zbg = zbuf.C();

    static {
        zbala zbalaVar = new zbala();
        zbb = zbalaVar;
        zbuf.m(zbala.class, zbalaVar);
    }

    private zbala() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0001\u0000\u0001\n\u0002\u0002\u0003\u0002\u0004\u001b", new Object[]{"zbd", "zbe", "zbf", "zbg", zbajt.class});
        }
        if (i3 == 3) {
            return new zbala();
        }
        zbaky zbakyVar = null;
        if (i3 == 4) {
            return new zbakz(zbakyVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
