package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbrq extends zbuf implements zbvn {
    private static final zbrq zbb;
    private int zbd;
    private int zbe;
    private int zbf;
    private float zbg;
    private float zbh;
    private int zbi;
    private int zbj;
    private int zbk;
    private int zbl;
    private int zbm;
    private float zbo;
    private float zbq;
    private String zbn = "";
    private String zbp = "";
    private zbun zbr = zbuf.C();
    private zbuk zbs = zbuf.z();
    private zbuk zbt = zbuf.z();
    private zbun zbu = zbuf.C();
    private zbuk zbv = zbuf.z();
    private zbuk zbw = zbuf.z();

    static {
        zbrq zbrqVar = new zbrq();
        zbb = zbrqVar;
        zbuf.m(zbrq.class, zbrqVar);
    }

    private zbrq() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0013\u0000\u0001\u0001\u0013\u0013\u0000\u0006\u0000\u0001င\u0000\u0002င\u0001\u0003ခ\u0002\u0004ခ\u0003\u0005င\u0004\u0006င\u0005\u0007င\u0006\bင\u0007\tင\b\nဈ\u000b\u000b\u001a\fဈ\t\rခ\n\u000eခ\f\u000f$\u0010$\u0011\u001a\u0012$\u0013$", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbi", "zbj", "zbk", "zbl", "zbm", "zbp", "zbr", "zbn", "zbo", "zbq", "zbs", "zbt", "zbu", "zbv", "zbw"});
        }
        if (i3 == 3) {
            return new zbrq();
        }
        zbpu zbpuVar = null;
        if (i3 == 4) {
            return new zbrp(zbpuVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
