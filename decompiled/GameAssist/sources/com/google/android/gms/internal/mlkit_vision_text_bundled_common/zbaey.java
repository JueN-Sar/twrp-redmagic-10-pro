package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaey extends zbuf implements zbvn {
    private static final zbaey zbb;
    private zbuk zbd = zbuf.z();
    private zbuk zbe = zbuf.z();
    private zbuk zbf = zbuf.z();
    private zbuk zbg = zbuf.z();
    private zbuk zbh = zbuf.z();
    private zbuk zbi = zbuf.z();

    static {
        zbaey zbaeyVar = new zbaey();
        zbb = zbaeyVar;
        zbuf.m(zbaey.class, zbaeyVar);
    }

    private zbaey() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0006\u0000\u0000\u0001\u0006\u0006\u0000\u0006\u0000\u0001\u0013\u0002\u0013\u0003\u0013\u0004\u0013\u0005\u0013\u0006\u0013", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbi"});
        }
        if (i3 == 3) {
            return new zbaey();
        }
        zbaeu zbaeuVar = null;
        if (i3 == 4) {
            return new zbaex(zbaeuVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
