package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaiu extends zbuf implements zbvn {
    private static final zbaiu zbb;
    private int zbd;
    private String zbe = "";
    private zbtc zbf = zbtc.zbb;

    static {
        zbaiu zbaiuVar = new zbaiu();
        zbb = zbaiuVar;
        zbuf.m(zbaiu.class, zbaiuVar);
    }

    private zbaiu() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ည\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbaiu();
        }
        zbair zbairVar = null;
        if (i3 == 4) {
            return new zbait(zbairVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
