package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbagk extends zbuf implements zbvn {
    private static final zbagk zbb;
    private int zbd;
    private long zbe;
    private int zbh;
    private int zbi;
    private zbagc zbl;
    private zbtc zbm;
    private zbagw zbn;
    private String zbo;
    private zbun zbp;
    private zbun zbq;
    private zbtc zbr;
    private String zbs;
    private byte zbt = 2;
    private String zbf = "";
    private String zbg = "";
    private zbun zbj = zbuf.C();
    private String zbk = "";

    static {
        zbagk zbagkVar = new zbagk();
        zbb = zbagkVar;
        zbuf.m(zbagk.class, zbagkVar);
    }

    private zbagk() {
        zbtc zbtcVar = zbtc.zbb;
        this.zbm = zbtcVar;
        this.zbo = "";
        this.zbp = zbuf.C();
        this.zbq = zbuf.C();
        this.zbr = zbtcVar;
        this.zbs = "";
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbt);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u000f\u0000\u0001\u0001\u001c\u000f\u0000\u0003\u0004\u0001ᔂ\u0000\u0002б\u0010ဈ\u0001\u0011ဈ\u0002\u0012င\u0003\u0013င\u0004\u0014\u001a\u0015ဈ\u0005\u0016ည\u0007\u0017ᐉ\b\u0018ᐉ\u0006\u0019ည\n\u001aဈ\t\u001bဈ\u000b\u001c\u001b", new Object[]{"zbd", "zbe", "zbp", zbagj.class, "zbf", "zbg", "zbh", "zbi", "zbj", "zbk", "zbm", "zbn", "zbl", "zbr", "zbo", "zbs", "zbq", zbagf.class});
        }
        if (i3 == 3) {
            return new zbagk();
        }
        zbagg zbaggVar = null;
        if (i3 == 4) {
            return new zbagh(zbaggVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbt = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
