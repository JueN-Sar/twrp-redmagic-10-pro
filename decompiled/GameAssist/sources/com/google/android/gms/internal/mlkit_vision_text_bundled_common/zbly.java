package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbly extends zbuf implements zbvn {
    private static final zbly zbb;
    private String zbd = "";
    private boolean zbe;

    static {
        zbly zblyVar = new zbly();
        zbb = zblyVar;
        zbuf.m(zbly.class, zblyVar);
    }

    private zbly() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\u0007", new Object[]{"zbd", "zbe"});
        }
        if (i3 == 3) {
            return new zbly();
        }
        zblq zblqVar = null;
        if (i3 == 4) {
            return new zblx(zblqVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
