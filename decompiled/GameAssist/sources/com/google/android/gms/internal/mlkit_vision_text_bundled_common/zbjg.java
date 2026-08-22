package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbjg extends zbuf implements zbvn {
    private static final zbjg zbb;
    private int zbd;
    private zbun zbe = zbuf.C();
    private String zbf = "";

    static {
        zbjg zbjgVar = new zbjg();
        zbb = zbjgVar;
        zbuf.m(zbjg.class, zbjgVar);
    }

    private zbjg() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002ဈ\u0000", new Object[]{"zbd", "zbe", zbjd.class, "zbf"});
        }
        if (i3 == 3) {
            return new zbjg();
        }
        zbje zbjeVar = null;
        if (i3 == 4) {
            return new zbjf(zbjeVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
