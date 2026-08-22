package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbra extends zbuf implements zbvn {
    private static final zbra zbb;
    private int zbd;
    private float zbe;
    private float zbf;
    private float zbg;
    private float zbh;
    private float zbi;
    private float zbj;
    private float zbk;
    private float zbl;
    private float zbm;
    private float zbn;
    private float zbo;
    private float zbp;
    private int zbq;
    private float zbr;
    private float zbs;
    private float zbt;
    private boolean zbu;
    private boolean zbv;
    private boolean zbw;
    private int zbx;

    static {
        zbra zbraVar = new zbra();
        zbb = zbraVar;
        zbuf.m(zbra.class, zbraVar);
    }

    private zbra() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0014\u0000\u0001\u0001\u0014\u0014\u0000\u0000\u0000\u0001ခ\u0000\u0002ခ\u0001\u0003ခ\u0002\u0004ခ\u0003\u0005ခ\u0004\u0006ခ\u0005\u0007ခ\u0006\bခ\u0007\tခ\b\nခ\t\u000bခ\n\fခ\u000b\rင\f\u000eခ\r\u000fခ\u000e\u0010ခ\u000f\u0011ဇ\u0010\u0012ဇ\u0011\u0013ဇ\u0012\u0014င\u0013", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbi", "zbj", "zbk", "zbl", "zbm", "zbn", "zbo", "zbp", "zbq", "zbr", "zbs", "zbt", "zbu", "zbv", "zbw", "zbx"});
        }
        if (i3 == 3) {
            return new zbra();
        }
        zbpu zbpuVar = null;
        if (i3 == 4) {
            return new zbqz(zbpuVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
