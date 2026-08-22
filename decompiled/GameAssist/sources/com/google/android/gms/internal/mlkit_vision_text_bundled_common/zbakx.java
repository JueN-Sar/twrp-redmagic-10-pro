package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbakx extends zbuf implements zbvn {
    private static final zbakx zbb;
    private Object zbe;
    private int zbd = 0;
    private String zbf = "";
    private String zbg = "";
    private String zbh = "";

    static {
        zbakx zbakxVar = new zbakx();
        zbb = zbakxVar;
        zbuf.m(zbakx.class, zbakxVar);
    }

    private zbakx() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0005\u0001\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002Ȼ\u0000\u0003=\u0000\u0004Ȉ\u0005Ȉ", new Object[]{"zbe", "zbd", "zbf", "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbakx();
        }
        zbakv zbakvVar = null;
        if (i3 == 4) {
            return new zbakw(zbakvVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
