package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbpg extends zbuf implements zbvn {
    private static final zbpg zbb;
    private int zbd;
    private zbpk zbe;
    private double zbf;
    private double zbg;

    static {
        zbpg zbpgVar = new zbpg();
        zbb = zbpgVar;
        zbuf.m(zbpg.class, zbpgVar);
    }

    private zbpg() {
    }

    public static zbpf E() {
        return (zbpf) zbb.u();
    }

    static /* synthetic */ void H(zbpg zbpgVar, zbpk zbpkVar) {
        zbpkVar.getClass();
        zbpgVar.zbe = zbpkVar;
        zbpgVar.zbd |= 1;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002\u0000\u0003\u0000", new Object[]{"zbd", "zbe", "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbpg();
        }
        zbpe zbpeVar = null;
        if (i3 == 4) {
            return new zbpf(zbpeVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
