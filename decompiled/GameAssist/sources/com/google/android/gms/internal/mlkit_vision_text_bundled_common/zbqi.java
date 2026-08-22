package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbqi extends zbuf implements zbvn {
    private static final zbqi zbb;
    private zbuk zbA;
    private zbul zbB;
    private float zbC;
    private int zbD;
    private int zbE;
    private byte zbF = 2;
    private int zbd;
    private zbpw zbe;
    private float zbf;
    private zbtc zbg;
    private zbtc zbh;
    private zbpw zbi;
    private int zbj;
    private zbun zbk;
    private boolean zbl;
    private boolean zbm;
    private zbun zbn;
    private String zbo;
    private String zbp;
    private zbun zbq;
    private zbun zbr;
    private int zbs;
    private int zbt;
    private float zbu;
    private float zbv;
    private float zbw;
    private int zbx;
    private zbqy zby;
    private zbun zbz;

    static {
        zbqi zbqiVar = new zbqi();
        zbb = zbqiVar;
        zbuf.m(zbqi.class, zbqiVar);
    }

    private zbqi() {
        zbtc zbtcVar = zbtc.zbb;
        this.zbg = zbtcVar;
        this.zbh = zbtcVar;
        this.zbk = zbuf.C();
        this.zbn = zbuf.C();
        this.zbo = "";
        this.zbp = "";
        this.zbq = zbuf.C();
        this.zbr = zbuf.C();
        this.zbt = 1;
        this.zbz = zbuf.C();
        this.zbA = zbuf.z();
        this.zbB = zbuf.A();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbF);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u001b\u0000\u0001\u0001d\u001b\u0000\u0007\u0007\u0001ᔉ\u0000\u0002ခ\u0001\u0003ည\u0002\u0004ည\u0003\u0005င\u0005\u0006ᐉ\u0004\u0007Л\bဇ\u0006\tဇ\u0007\nЛ\u000bဈ\b\fЛ\rЛ\u000eင\n\u000f᠌\u000b\u0010ခ\u000e\u0011ဈ\t\u0012င\u000f\u0013ဉ\u0010\u0014Л\u0015\u0013\u0016\u0016\u0017ခ\f\u0018ခ\r\u0019ခ\u0011\u001aင\u0012dင\u0013", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbj", "zbi", "zbk", zbqk.class, "zbl", "zbm", "zbn", zbse.class, "zbo", "zbq", zbpw.class, "zbr", zbqa.class, "zbs", "zbt", zbqr.f12926a, "zbw", "zbp", "zbx", "zby", "zbz", zbpw.class, "zbA", "zbB", "zbu", "zbv", "zbC", "zbD", "zbE"});
        }
        if (i3 == 3) {
            return new zbqi();
        }
        zbpu zbpuVar = null;
        if (i3 == 4) {
            return new zbqh(zbpuVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbF = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
