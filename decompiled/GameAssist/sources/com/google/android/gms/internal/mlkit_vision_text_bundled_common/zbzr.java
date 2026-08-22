package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbzr extends zbuf implements zbvn {
    private static final zbzr zbb;
    private int zbd;
    private int zbe;
    private int zbf;
    private String zbg = "";

    static {
        zbzr zbzrVar = new zbzr();
        zbb = zbzrVar;
        zbuf.m(zbzr.class, zbzrVar);
    }

    private zbzr() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002᠌\u0001\u0003ဈ\u0002", new Object[]{"zbd", "zbe", "zbf", zbzp.f13084a, "zbg"});
        }
        if (i3 == 3) {
            return new zbzr();
        }
        zbwz zbwzVar = null;
        if (i3 == 4) {
            return new zbzq(zbwzVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
