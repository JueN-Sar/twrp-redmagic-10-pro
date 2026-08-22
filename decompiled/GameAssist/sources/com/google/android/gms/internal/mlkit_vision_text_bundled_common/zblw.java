package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zblw extends zbuf implements zbvn {
    private static final zblw zbb;
    private int zbd;
    private zbmd zbg;
    private byte zbh = 2;
    private String zbe = "";
    private String zbf = "";

    static {
        zblw zblwVar = new zblw();
        zbb = zblwVar;
        zbuf.m(zblw.class, zblwVar);
    }

    private zblw() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbh);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0001\u0001Ȉ\u0002Ȉ\u0003ᐉ\u0000", new Object[]{"zbd", "zbe", "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zblw();
        }
        zblq zblqVar = null;
        if (i3 == 4) {
            return new zblv(zblqVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbh = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
