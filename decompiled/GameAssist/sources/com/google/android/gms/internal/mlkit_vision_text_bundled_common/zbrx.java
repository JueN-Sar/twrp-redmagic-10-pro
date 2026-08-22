package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbrx extends zbuf implements zbvn {
    private static final zbrx zbb;
    private int zbd;
    private int zbe;
    private String zbf = "";
    private zbuk zbg = zbuf.z();
    private int zbh;
    private int zbi;
    private float zbj;

    static {
        zbrx zbrxVar = new zbrx();
        zbb = zbrxVar;
        zbuf.m(zbrx.class, zbrxVar);
    }

    private zbrx() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001င\u0000\u0002ဈ\u0001\u0003\u0013\u0004င\u0002\u0005င\u0003\u0006ခ\u0004", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbi", "zbj"});
        }
        if (i3 == 3) {
            return new zbrx();
        }
        zbrt zbrtVar = null;
        if (i3 == 4) {
            return new zbrw(zbrtVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
