package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.List;

/* loaded from: classes.dex */
public final class zbaax extends zbuf implements zbvn {
    private static final zbaax zbb;
    private byte zbe = 2;
    private zbun zbd = zbuf.C();

    static {
        zbaax zbaaxVar = new zbaax();
        zbb = zbaaxVar;
        zbuf.m(zbaax.class, zbaaxVar);
    }

    private zbaax() {
    }

    public static zbaax F() {
        return zbb;
    }

    public final List H() {
        return this.zbd;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbe);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0001Л", new Object[]{"zbd", zbaaw.class});
        }
        if (i3 == 3) {
            return new zbaax();
        }
        zbaad zbaadVar = null;
        if (i3 == 4) {
            return new zbaau(zbaadVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbe = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
