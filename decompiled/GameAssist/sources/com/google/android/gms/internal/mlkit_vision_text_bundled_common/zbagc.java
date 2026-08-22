package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbagc extends zbuf implements zbvn {
    private static final zbagc zbb;
    private int zbd;
    private double zbe;
    private double zbf;
    private double zbh;
    private boolean zbi;
    private double zbj;
    private double zbk;
    private byte zbl = 2;
    private String zbg = "";

    static {
        zbagc zbagcVar = new zbagc();
        zbb = zbagcVar;
        zbuf.m(zbagc.class, zbagcVar);
    }

    private zbagc() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbl);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0007\u0000\u0001\u0001\b\u0007\u0000\u0000\u0002\u0001ᔀ\u0000\u0002ᔀ\u0001\u0004က\u0005\u0005က\u0006\u0006ဇ\u0004\u0007က\u0003\bဈ\u0002", new Object[]{"zbd", "zbe", "zbf", "zbj", "zbk", "zbi", "zbh", "zbg"});
        }
        if (i3 == 3) {
            return new zbagc();
        }
        zbaga zbagaVar = null;
        if (i3 == 4) {
            return new zbagb(zbagaVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbl = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
