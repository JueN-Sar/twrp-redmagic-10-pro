package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zblt extends zbuf implements zbvn {
    private static final zblt zbb;
    private int zbd;
    private zblp zbk;
    private int zbm;
    private int zbn;
    private zbna zbo;
    private zbnc zbp;
    private zbma zbs;
    private int zbt;
    private byte zbw = 2;
    private String zbe = "";
    private String zbf = "";
    private zbun zbg = zbuf.C();
    private zbun zbh = zbuf.C();
    private zbun zbi = zbuf.C();
    private zbun zbj = zbuf.C();
    private zbun zbl = zbuf.C();
    private zbun zbq = zbuf.C();
    private String zbr = "";
    private zbun zbu = zbuf.C();
    private zbun zbv = zbuf.C();

    static {
        zblt zbltVar = new zblt();
        zbb = zbltVar;
        zbuf.m(zblt.class, zbltVar);
    }

    private zblt() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbw);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0012\u0000\u0001\u0001ϭ\u0012\u0000\b\u0003\u0001Ȉ\u0002Ȉ\u0003Ț\u0004Ț\u0005Ț\u0006Ț\u0007ᐉ\u0000\b\u001b\t\u0004\n\u0004\u000bᐉ\u0001\fᐉ\u0002\r\u001b\u000eȈ\u000fဉ\u0003\u0010\u0004\u0011ȚϭȚ", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbi", "zbj", "zbk", "zbl", zbsp.class, "zbm", "zbn", "zbo", "zbp", "zbq", zbly.class, "zbr", "zbs", "zbt", "zbu", "zbv"});
        }
        if (i3 == 3) {
            return new zblt();
        }
        zblq zblqVar = null;
        if (i3 == 4) {
            return new zbls(zblqVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbw = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
