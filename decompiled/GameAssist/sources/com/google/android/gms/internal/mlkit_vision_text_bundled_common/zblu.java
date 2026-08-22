package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zblu extends zbuf implements zbvn {
    private static final zblu zbb;
    private int zbd;
    private int zbh;
    private int zbn;
    private boolean zbo;
    private zbna zbp;
    private zbnc zbq;
    private zbma zbs;
    private zbmd zbv;
    private byte zbx = 2;
    private zbun zbe = zbuf.C();
    private zbun zbf = zbuf.C();
    private zbun zbg = zbuf.C();
    private zbun zbi = zbuf.C();
    private zbun zbj = zbuf.C();
    private zbun zbk = zbuf.C();
    private zbun zbl = zbuf.C();
    private zbun zbm = zbuf.C();
    private zbun zbr = zbuf.C();
    private String zbt = "";
    private String zbu = "";
    private zbun zbw = zbuf.C();

    static {
        zblu zbluVar = new zblu();
        zbb = zbluVar;
        zbuf.m(zblu.class, zbluVar);
    }

    private zblu() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbx);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0013\u0000\u0001\u0001Ϫ\u0013\u0000\n\b\u0001Л\u0006Л\u0007Л\b\u0004\tЛ\nȚ\u000b\u0004\fᐉ\u0000\rᐉ\u0001\u000eЛ\u000fȚ\u0010Ț\u0011Ț\u0012ဉ\u0002\u0013Ȉ\u0014Ȉ\u0015\u0007ϩᐉ\u0003Ϫ\u001b", new Object[]{"zbd", "zbe", zblt.class, "zbf", zbmn.class, "zbg", zbms.class, "zbh", "zbi", zbmx.class, "zbj", "zbn", "zbp", "zbq", "zbr", zblw.class, "zbk", "zbl", "zbm", "zbs", "zbt", "zbu", "zbo", "zbv", "zbw", zbsp.class});
        }
        if (i3 == 3) {
            return new zblu();
        }
        zblq zblqVar = null;
        if (i3 == 4) {
            return new zblr(zblqVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbx = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
