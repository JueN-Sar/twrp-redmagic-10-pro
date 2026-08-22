package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbys extends zbuf implements zbvn {
    private static final zbys zbb;
    private int zbd;
    private int zbe;
    private int zbf = 100;
    private int zbg;

    static {
        zbys zbysVar = new zbys();
        zbb = zbysVar;
        zbuf.m(zbys.class, zbysVar);
    }

    private zbys() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001\u0003င\u0002", new Object[]{"zbd", "zbe", zbyq.f13081a, "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbys();
        }
        zbwz zbwzVar = null;
        if (i3 == 4) {
            return new zbyr(zbwzVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
