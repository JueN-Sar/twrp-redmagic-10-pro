package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbyp extends zbuf implements zbvn {
    private static final zbyp zbb;
    private int zbd;
    private boolean zbf;
    private int zbg;
    private boolean zbj;
    private int zbm;
    private int zbn;
    private boolean zbo;
    private int zbe = -1;
    private zbtc zbh = zbtc.zbb;
    private String zbi = "";
    private boolean zbk = true;
    private boolean zbl = true;

    static {
        zbyp zbypVar = new zbyp();
        zbb = zbypVar;
        zbuf.m(zbyp.class, zbypVar);
    }

    private zbyp() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            zbuj zbujVar = zbyn.f13079a;
            zbuj zbujVar2 = zbyo.f13080a;
            return zbuf.j(zbb, "\u0001\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0000\u0000\u0001င\u0000\u0002ဇ\u0001\u0003᠌\u0002\u0004ည\u0003\u0005ဈ\u0004\u0006ဇ\u0005\u0007ဇ\u0006\bဇ\u0007\t᠌\b\n᠌\t\u000bဇ\n", new Object[]{"zbd", "zbe", "zbf", "zbg", zbujVar, "zbh", "zbi", "zbj", "zbk", "zbl", "zbm", zbujVar2, "zbn", zbujVar2, "zbo"});
        }
        if (i3 == 3) {
            return new zbyp();
        }
        zbwz zbwzVar = null;
        if (i3 == 4) {
            return new zbym(zbwzVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
