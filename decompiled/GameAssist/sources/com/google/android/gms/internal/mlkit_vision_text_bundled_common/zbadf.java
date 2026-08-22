package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbadf extends zbuf implements zbvn {
    private static final zbadf zbb;
    private byte zbe = 2;
    private zbun zbd = zbuf.C();

    static {
        zbadf zbadfVar = new zbadf();
        zbb = zbadfVar;
        zbuf.m(zbadf.class, zbadfVar);
    }

    private zbadf() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbe);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0001Л", new Object[]{"zbd", zbadb.class});
        }
        if (i3 == 3) {
            return new zbadf();
        }
        zbadd zbaddVar = null;
        if (i3 == 4) {
            return new zbade(zbaddVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbe = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
