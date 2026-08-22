package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbpb extends zbuf implements zbvn {
    private static final zbpb zbb;
    private int zbd;
    private int zbe;
    private int zbf;
    private int zbg;
    private int zbh;
    private float zbi;
    private byte zbj = 2;

    static {
        zbpb zbpbVar = new zbpb();
        zbb = zbpbVar;
        zbuf.m(zbpb.class, zbpbVar);
    }

    private zbpb() {
    }

    public static zbpb L() {
        return zbb;
    }

    public final float E() {
        return this.zbi;
    }

    public final int F() {
        return this.zbh;
    }

    public final int H() {
        return this.zbe;
    }

    public final int I() {
        return this.zbf;
    }

    public final int J() {
        return this.zbg;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbj);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0004\u0001ᔄ\u0000\u0002ᔄ\u0001\u0003ᔄ\u0002\u0004ᔄ\u0003\u0005ခ\u0004", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbi"});
        }
        if (i3 == 3) {
            return new zbpb();
        }
        zboq zboqVar = null;
        if (i3 == 4) {
            return new zbpa(zboqVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbj = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
