package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbhr extends zbuf implements zbvn {
    private static final zbhr zbb;
    private int zbd;
    private Object zbf;
    private boolean zbg;
    private long zbi;
    private float zbm;
    private float zbn;
    private float zbo;
    private int zbe = 0;
    private byte zbp = 2;
    private zbun zbh = zbuf.C();
    private boolean zbj = true;
    private zbuk zbk = zbuf.z();
    private float zbl = 0.15f;

    static {
        zbhr zbhrVar = new zbhr();
        zbb = zbhrVar;
        zbuf.m(zbhr.class, zbhrVar);
    }

    private zbhr() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbp);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\n\u0001\u0001\u0002\r\n\u0000\u0002\u0001\u0002м\u0000\u0003ဇ\u0000\u0004\u001b\u0005\u0013\u0006ခ\u0003\u0007ခ\u0004\bခ\u0005\u000bခ\u0006\fဂ\u0001\rဇ\u0002", new Object[]{"zbf", "zbe", "zbd", zbim.class, "zbg", "zbh", zbhi.class, "zbk", "zbl", "zbm", "zbn", "zbo", "zbi", "zbj"});
        }
        if (i3 == 3) {
            return new zbhr();
        }
        zbhp zbhpVar = null;
        if (i3 == 4) {
            return new zbhq(zbhpVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbp = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
