package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbahl extends zbuf implements zbvn {
    private static final zbahl zbb;
    private String zbd = "";
    private String zbe = "";
    private float zbf;

    static {
        zbahl zbahlVar = new zbahl();
        zbb = zbahlVar;
        zbuf.m(zbahl.class, zbahlVar);
    }

    private zbahl() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbahl();
        }
        zbagx zbagxVar = null;
        if (i3 == 4) {
            return new zbahk(zbagxVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
