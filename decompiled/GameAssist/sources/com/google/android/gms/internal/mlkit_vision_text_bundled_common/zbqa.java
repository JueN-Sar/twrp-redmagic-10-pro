package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbqa extends zbuf implements zbvn {
    private static final zbqa zbb;
    private int zbd;
    private float zbe;
    private int zbf;
    private byte zbg = 2;

    static {
        zbqa zbqaVar = new zbqa();
        zbb = zbqaVar;
        zbuf.m(zbqa.class, zbqaVar);
    }

    private zbqa() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbg);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001ᔁ\u0000\u0002င\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbqa();
        }
        zbpu zbpuVar = null;
        if (i3 == 4) {
            return new zbpz(zbpuVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbg = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
