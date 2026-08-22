package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbow extends zbuf implements zbvn {
    private static final zbow zbb;
    private int zbd;
    private zbou zbe;
    private double zbf;
    private boolean zbg;
    private zbpb zbh;
    private byte zbi = 2;

    static {
        zbow zbowVar = new zbow();
        zbb = zbowVar;
        zbuf.m(zbow.class, zbowVar);
    }

    private zbow() {
    }

    public static zbow F() {
        return zbb;
    }

    public final zbpb H() {
        zbpb zbpbVar = this.zbh;
        return zbpbVar == null ? zbpb.L() : zbpbVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbi);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0001\u0001ဉ\u0000\u0002က\u0001\u0003ဇ\u0002\u0004ᐉ\u0003", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh"});
        }
        if (i3 == 3) {
            return new zbow();
        }
        zboq zboqVar = null;
        if (i3 == 4) {
            return new zbov(zboqVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbi = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
