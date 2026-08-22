package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbagj extends zbuf implements zbvn {
    private static final zbagj zbb;
    private int zbd;
    private int zbe;
    private int zbg;
    private boolean zbh;
    private byte zbi = 2;
    private zbun zbf = zbuf.C();

    static {
        zbagj zbagjVar = new zbagj();
        zbb = zbagjVar;
        zbuf.m(zbagj.class, zbagjVar);
    }

    private zbagj() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbi);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0003\u0006\u0004\u0000\u0001\u0002\u0003ᔄ\u0000\u0004Л\u0005င\u0001\u0006ဇ\u0002", new Object[]{"zbd", "zbe", "zbf", zbagn.class, "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbagj();
        }
        zbagg zbaggVar = null;
        if (i3 == 4) {
            return new zbagi(zbaggVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbi = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
