package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaeq extends zbuf implements zbvn {
    private static final zbaeq zbb;
    private zbun zbd = zbuf.C();

    static {
        zbaeq zbaeqVar = new zbaeq();
        zbb = zbaeqVar;
        zbuf.m(zbaeq.class, zbaeqVar);
    }

    private zbaeq() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zbd", zbaen.class});
        }
        if (i3 == 3) {
            return new zbaeq();
        }
        zbaeo zbaeoVar = null;
        if (i3 == 4) {
            return new zbaep(zbaeoVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
