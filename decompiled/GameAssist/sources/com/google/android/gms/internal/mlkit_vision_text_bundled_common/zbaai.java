package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaai extends zbuf implements zbvn {
    private static final zbaai zbb;
    private int zbd;
    private zbpb zbf;
    private byte zbg = 2;
    private zbun zbe = zbuf.C();

    static {
        zbaai zbaaiVar = new zbaai();
        zbb = zbaaiVar;
        zbuf.m(zbaai.class, zbaaiVar);
    }

    private zbaai() {
    }

    public static zbaai H() {
        return zbb;
    }

    public final zbpb E() {
        zbpb zbpbVar = this.zbf;
        return zbpbVar == null ? zbpb.L() : zbpbVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbg);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0002\u0001Л\u0002ᐉ\u0000", new Object[]{"zbd", "zbe", zbaag.class, "zbf"});
        }
        if (i3 == 3) {
            return new zbaai();
        }
        zbaad zbaadVar = null;
        if (i3 == 4) {
            return new zbaah(zbaadVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbg = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
