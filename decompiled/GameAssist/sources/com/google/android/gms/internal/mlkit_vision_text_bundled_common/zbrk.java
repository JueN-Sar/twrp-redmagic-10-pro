package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbrk extends zbuf implements zbvn {
    private static final zbrk zbb;
    private int zbd;
    private zbpw zbe;
    private byte zbg = 2;
    private zbul zbf = zbuf.A();

    static {
        zbrk zbrkVar = new zbrk();
        zbb = zbrkVar;
        zbuf.m(zbrk.class, zbrkVar);
    }

    private zbrk() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbg);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0001\u0001ᐉ\u0000\u0002\u0016", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbrk();
        }
        zbpu zbpuVar = null;
        if (i3 == 4) {
            return new zbrj(zbpuVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbg = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
