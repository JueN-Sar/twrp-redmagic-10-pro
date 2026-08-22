package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbafd extends zbuf implements zbvn {
    private static final zbafd zbb;
    private int zbd;
    private int zbf;
    private String zbe = "";
    private zbuk zbg = zbuf.z();
    private String zbh = "";
    private zbun zbi = zbuf.C();

    static {
        zbafd zbafdVar = new zbafd();
        zbb = zbafdVar;
        zbuf.m(zbafd.class, zbafdVar);
    }

    private zbafd() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001ဈ\u0000\u0002င\u0001\u0003$\u0004ဈ\u0002\u0005\u001a", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbi"});
        }
        if (i3 == 3) {
            return new zbafd();
        }
        zbafb zbafbVar = null;
        if (i3 == 4) {
            return new zbafc(zbafbVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
