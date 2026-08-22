package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbpp extends zbuf implements zbvn {
    private static final zbpp zbb;
    private int zbd;
    private int zbe;
    private int zbf;
    private int zbg;
    private String zbh = "";

    static {
        zbpp zbppVar = new zbpp();
        zbb = zbppVar;
        zbuf.m(zbpp.class, zbppVar);
    }

    private zbpp() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003ဏ\u0002\u0004ဈ\u0003", new Object[]{"zbd", "zbe", zbpt.f12924a, "zbf", zbps.f12923a, "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbpp();
        }
        zbpn zbpnVar = null;
        if (i3 == 4) {
            return new zbpo(zbpnVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
