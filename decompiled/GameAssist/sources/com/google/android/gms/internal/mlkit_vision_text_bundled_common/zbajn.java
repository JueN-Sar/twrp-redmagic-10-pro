package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbajn extends zbuf implements zbvn {
    private static final zbajn zbb;
    private String zbd = "";
    private zbun zbe = zbuf.C();
    private String zbf = "";

    static {
        zbajn zbajnVar = new zbajn();
        zbb = zbajnVar;
        zbuf.m(zbajn.class, zbajnVar);
    }

    private zbajn() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0001\u0000\u0001Ȉ\u0002\u001b\u0003Ȉ", new Object[]{"zbd", "zbe", zbajk.class, "zbf"});
        }
        if (i3 == 3) {
            return new zbajn();
        }
        zbajl zbajlVar = null;
        if (i3 == 4) {
            return new zbajm(zbajlVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
