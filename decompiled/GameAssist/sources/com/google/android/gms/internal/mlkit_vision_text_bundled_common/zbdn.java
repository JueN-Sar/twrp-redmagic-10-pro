package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbdn extends zbuf implements zbvn {
    private static final zbdn zbb;
    private int zbd;
    private int zbe;
    private String zbf = "";

    static {
        zbdn zbdnVar = new zbdn();
        zbb = zbdnVar;
        zbuf.m(zbdn.class, zbdnVar);
    }

    private zbdn() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002ဈ\u0001", new Object[]{"zbd", "zbe", zbdm.f12763a, "zbf"});
        }
        if (i3 == 3) {
            return new zbdn();
        }
        zbdk zbdkVar = null;
        if (i3 == 4) {
            return new zbdl(zbdkVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
