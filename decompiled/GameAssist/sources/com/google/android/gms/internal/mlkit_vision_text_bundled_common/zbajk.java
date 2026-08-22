package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbajk extends zbuf implements zbvn {
    private static final zbajk zbb;
    private int zbd;
    private zbajh zbe;
    private zbakx zbf;

    static {
        zbajk zbajkVar = new zbajk();
        zbb = zbajkVar;
        zbuf.m(zbajk.class, zbajkVar);
    }

    private zbajk() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbajk();
        }
        zbaji zbajiVar = null;
        if (i3 == 4) {
            return new zbajj(zbajiVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
