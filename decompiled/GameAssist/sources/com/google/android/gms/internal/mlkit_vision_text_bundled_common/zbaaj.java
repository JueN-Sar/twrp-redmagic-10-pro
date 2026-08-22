package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaaj extends zbuf implements zbvn {
    private static final zbaaj zbb;
    private Object zbe;
    private int zbd = 0;
    private byte zbf = 2;

    static {
        zbaaj zbaajVar = new zbaaj();
        zbb = zbaajVar;
        zbuf.m(zbaaj.class, zbaajVar);
    }

    private zbaaj() {
    }

    public static zbaaj K() {
        return zbb;
    }

    public final boolean E() {
        return this.zbd == 1;
    }

    public final zbow F() {
        return this.zbd == 3 ? (zbow) this.zbe : zbow.F();
    }

    public final zbpb H() {
        return this.zbd == 2 ? (zbpb) this.zbe : zbpb.L();
    }

    public final zbaai I() {
        return this.zbd == 1 ? (zbaai) this.zbe : zbaai.H();
    }

    public final boolean L() {
        return this.zbd == 3;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbf);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0000\u0003\u0001м\u0000\u0002м\u0000\u0003м\u0000", new Object[]{"zbe", "zbd", zbaai.class, zbpb.class, zbow.class});
        }
        if (i3 == 3) {
            return new zbaaj();
        }
        zbaad zbaadVar = null;
        if (i3 == 4) {
            return new zbaae(zbaadVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbf = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
