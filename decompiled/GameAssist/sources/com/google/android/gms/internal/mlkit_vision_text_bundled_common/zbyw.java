package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbyw extends zbuf implements zbvn {
    private static final zbyw zbb;
    private int zbd;
    private String zbe = "";
    private zbzc zbf;

    static {
        zbyw zbywVar = new zbyw();
        zbb = zbywVar;
        zbuf.m(zbyw.class, zbywVar);
    }

    private zbyw() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဉ\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbyw();
        }
        zbwz zbwzVar = null;
        if (i3 == 4) {
            return new zbyv(zbwzVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
