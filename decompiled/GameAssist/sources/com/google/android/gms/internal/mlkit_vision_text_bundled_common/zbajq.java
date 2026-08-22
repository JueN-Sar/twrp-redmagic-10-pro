package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbajq extends zbuf implements zbvn {
    private static final zbajq zbb;
    private zbun zbd = zbuf.C();
    private zbun zbe = zbuf.C();

    static {
        zbajq zbajqVar = new zbajq();
        zbb = zbajqVar;
        zbuf.m(zbajq.class, zbajqVar);
    }

    private zbajq() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0001\u001b\u0002\u001b", new Object[]{"zbd", zbajk.class, "zbe", zbajn.class});
        }
        if (i3 == 3) {
            return new zbajq();
        }
        zbajo zbajoVar = null;
        if (i3 == 4) {
            return new zbajp(zbajoVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
