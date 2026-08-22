package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbadp extends zbuf implements zbvn {
    private static final zbadp zbb;
    private int zbd;
    private zbun zbe = zbuf.C();
    private String zbf = "";
    private float zbg;

    static {
        zbadp zbadpVar = new zbadp();
        zbb = zbadpVar;
        zbuf.m(zbadp.class, zbadpVar);
    }

    private zbadp() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\u001a\u0002ဈ\u0000\u0003ခ\u0001", new Object[]{"zbd", "zbe", "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbadp();
        }
        zbadn zbadnVar = null;
        if (i3 == 4) {
            return new zbado(zbadnVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
