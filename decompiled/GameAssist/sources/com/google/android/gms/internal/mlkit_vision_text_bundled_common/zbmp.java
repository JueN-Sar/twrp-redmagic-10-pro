package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbmp extends zbub implements zbvn {
    private static final zbmp zbd;
    private byte zbe = 2;

    static {
        zbmp zbmpVar = new zbmp();
        zbd = zbmpVar;
        zbuf.m(zbmp.class, zbmpVar);
    }

    private zbmp() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbe);
        }
        zbml zbmlVar = null;
        if (i3 == 2) {
            return zbuf.j(zbd, "\u0001\u0000", null);
        }
        if (i3 == 3) {
            return new zbmp();
        }
        if (i3 == 4) {
            return new zbmo(zbmlVar);
        }
        if (i3 == 5) {
            return zbd;
        }
        this.zbe = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
