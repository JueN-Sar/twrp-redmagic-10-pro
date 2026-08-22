package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbmk extends zbuf implements zbvn {
    private static final zbmk zbb;
    private zbun zbd = zbuf.C();

    static {
        zbmk zbmkVar = new zbmk();
        zbb = zbmkVar;
        zbuf.m(zbmk.class, zbmkVar);
    }

    private zbmk() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zbd", zbmj.class});
        }
        if (i3 == 3) {
            return new zbmk();
        }
        zbme zbmeVar = null;
        if (i3 == 4) {
            return new zbmh(zbmeVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
