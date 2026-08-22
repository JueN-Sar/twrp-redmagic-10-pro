package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbajb extends zbuf implements zbvn {
    private static final zbajb zbb;
    private String zbd = "";
    private zbun zbe = zbuf.C();

    static {
        zbajb zbajbVar = new zbajb();
        zbb = zbajbVar;
        zbuf.m(zbajb.class, zbajbVar);
    }

    private zbajb() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", new Object[]{"zbd", "zbe", zbajh.class});
        }
        if (i3 == 3) {
            return new zbajb();
        }
        zbaiz zbaizVar = null;
        if (i3 == 4) {
            return new zbaja(zbaizVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
