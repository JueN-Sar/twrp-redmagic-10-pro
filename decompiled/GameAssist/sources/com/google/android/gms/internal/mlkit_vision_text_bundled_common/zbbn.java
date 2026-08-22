package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbbn extends zbuf implements zbvn {
    private static final zbbn zbb;
    private int zbd;
    private float zbh;
    private byte zbi = 2;
    private String zbe = "";
    private String zbf = "";
    private zbun zbg = zbuf.C();

    static {
        zbbn zbbnVar = new zbbn();
        zbb = zbbnVar;
        zbuf.m(zbbn.class, zbbnVar);
    }

    private zbbn() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbi);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0001\u0001ဈ\u0000\u0002ဈ\u0001\u0003Л\u0004ခ\u0002", new Object[]{"zbd", "zbe", "zbf", "zbg", zbre.class, "zbh"});
        }
        if (i3 == 3) {
            return new zbbn();
        }
        zbbl zbblVar = null;
        if (i3 == 4) {
            return new zbbm(zbblVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbi = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
