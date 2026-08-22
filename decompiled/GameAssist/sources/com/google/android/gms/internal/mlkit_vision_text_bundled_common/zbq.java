package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbq extends zbuf implements zbvn {
    private static final zbq zbb;
    private zbun zbd = zbuf.C();
    private int zbe;

    static {
        zbq zbqVar = new zbq();
        zbb = zbqVar;
        zbuf.m(zbq.class, zbqVar);
    }

    private zbq() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002\f", new Object[]{"zbd", zbn.class, "zbe"});
        }
        if (i3 == 3) {
            return new zbq();
        }
        zbo zboVar = null;
        if (i3 == 4) {
            return new zbp(zboVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
