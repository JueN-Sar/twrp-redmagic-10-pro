package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbace extends zbuf implements zbvn {
    private static final zbace zbb;
    private int zbd;
    private int zbe;
    private zbtc zbf = zbtc.zbb;
    private String zbg = "";
    private float zbh;

    static {
        zbace zbaceVar = new zbace();
        zbb = zbaceVar;
        zbuf.m(zbace.class, zbaceVar);
    }

    private zbace() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ည\u0001\u0003ဈ\u0002\u0004ခ\u0003", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbace();
        }
        zbabw zbabwVar = null;
        if (i3 == 4) {
            return new zbacd(zbabwVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
