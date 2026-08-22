package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaiv extends zbuf implements zbvn {
    private static final zbaiv zbb;
    private int zbd;
    private String zbe = "visionkit-pa.googleapis.com";
    private String zbf = "";
    private zbun zbg = zbuf.C();

    static {
        zbaiv zbaivVar = new zbaiv();
        zbb = zbaivVar;
        zbuf.m(zbaiv.class, zbaivVar);
    }

    private zbaiv() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003\u001b", new Object[]{"zbd", "zbe", "zbf", "zbg", zbaiu.class});
        }
        if (i3 == 3) {
            return new zbaiv();
        }
        zbair zbairVar = null;
        if (i3 == 4) {
            return new zbais(zbairVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
