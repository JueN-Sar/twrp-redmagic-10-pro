package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zblp extends zbub implements zbvn {
    private static final zblp zbd;
    private int zbe;
    private boolean zbf;
    private byte zbg = 2;

    static {
        zblp zblpVar = new zblp();
        zbd = zblpVar;
        zbuf.m(zblp.class, zblpVar);
    }

    private zblp() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbg);
        }
        if (i3 == 2) {
            return zbuf.j(zbd, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဇ\u0000", new Object[]{"zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zblp();
        }
        zbln zblnVar = null;
        if (i3 == 4) {
            return new zblo(zblnVar);
        }
        if (i3 == 5) {
            return zbd;
        }
        this.zbg = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
