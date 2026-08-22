package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbabv extends zbuf implements zbvn {
    private static final zbabv zbb;
    private zbun zbd = zbuf.C();
    private zbun zbe = zbuf.C();

    static {
        zbabv zbabvVar = new zbabv();
        zbb = zbabvVar;
        zbuf.m(zbabv.class, zbabvVar);
    }

    private zbabv() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0001\u001b\u0002\u001b", new Object[]{"zbd", zbabs.class, "zbe", zbadi.class});
        }
        if (i3 == 3) {
            return new zbabv();
        }
        zbabt zbabtVar = null;
        if (i3 == 4) {
            return new zbabu(zbabtVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
