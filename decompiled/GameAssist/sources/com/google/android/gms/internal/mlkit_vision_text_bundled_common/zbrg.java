package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbrg extends zbuf implements zbvn {
    private static final zbrg zbb;
    private int zbd;
    private int zbe;
    private float zbg;
    private byte zbh = 2;
    private zbul zbf = zbuf.A();

    static {
        zbrg zbrgVar = new zbrg();
        zbb = zbrgVar;
        zbuf.m(zbrg.class, zbrgVar);
    }

    private zbrg() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbh);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0002\u0001ᔄ\u0000\u0002ᔁ\u0001\u0003\u0016", new Object[]{"zbd", "zbe", "zbg", "zbf"});
        }
        if (i3 == 3) {
            return new zbrg();
        }
        zbpu zbpuVar = null;
        if (i3 == 4) {
            return new zbrf(zbpuVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbh = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
