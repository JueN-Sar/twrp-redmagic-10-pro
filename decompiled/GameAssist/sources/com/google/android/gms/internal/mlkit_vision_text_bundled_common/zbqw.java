package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbqw extends zbuf implements zbvn {
    private static final zbqw zbb;
    private int zbd;
    private boolean zbe;
    private boolean zbf;
    private boolean zbg;
    private boolean zbh = true;
    private boolean zbi;
    private boolean zbj;
    private boolean zbk;
    private float zbl;
    private boolean zbm;
    private boolean zbn;
    private boolean zbo;
    private boolean zbp;
    private int zbq;
    private boolean zbr;
    private zbqo zbs;
    private zbra zbt;

    static {
        zbqw zbqwVar = new zbqw();
        zbb = zbqwVar;
        zbuf.m(zbqw.class, zbqwVar);
    }

    private zbqw() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0010\u0000\u0001\u0001\u0010\u0010\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003ဇ\u0004\u0004ဇ\u0005\u0005ဇ\u0006\u0006ဇ\u0002\u0007ဇ\u0003\bခ\u0007\tဇ\b\nဇ\t\u000bဇ\n\fဇ\u000b\rင\f\u000eဇ\r\u000fဉ\u000e\u0010ဉ\u000f", new Object[]{"zbd", "zbe", "zbf", "zbi", "zbj", "zbk", "zbg", "zbh", "zbl", "zbm", "zbn", "zbo", "zbp", "zbq", "zbr", "zbs", "zbt"});
        }
        if (i3 == 3) {
            return new zbqw();
        }
        zbpu zbpuVar = null;
        if (i3 == 4) {
            return new zbqv(zbpuVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
