package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbek extends zbuf implements zbvn {
    private static final zbek zbb;
    private int zbd;
    private long zbe;
    private float zbf;

    static {
        zbek zbekVar = new zbek();
        zbb = zbekVar;
        zbuf.m(zbek.class, zbekVar);
    }

    private zbek() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဂ\u0000\u0002ခ\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbek();
        }
        zbei zbeiVar = null;
        if (i3 == 4) {
            return new zbej(zbeiVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
