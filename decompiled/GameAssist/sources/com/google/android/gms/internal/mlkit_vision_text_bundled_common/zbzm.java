package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbzm extends zbuf implements zbvn {
    private static final zbzm zbb;
    private int zbd;
    private int zbf;
    private boolean zbh;
    private int zbm;
    private String zbe = "";
    private String zbg = "";
    private String zbi = "";
    private zbun zbj = zbuf.C();
    private zbun zbk = zbuf.C();
    private zbun zbl = zbuf.C();

    static {
        zbzm zbzmVar = new zbzm();
        zbb = zbzmVar;
        zbuf.m(zbzm.class, zbzmVar);
    }

    private zbzm() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\t\u0000\u0001\u0001\n\t\u0000\u0003\u0000\u0001ဈ\u0000\u0002င\u0001\u0003\u001a\u0004\u001b\u0006ဈ\u0002\u0007ဇ\u0003\bဈ\u0004\t\u001a\nင\u0005", new Object[]{"zbd", "zbe", "zbf", "zbj", "zbk", zbzg.class, "zbg", "zbh", "zbi", "zbl", "zbm"});
        }
        if (i3 == 3) {
            return new zbzm();
        }
        zbwz zbwzVar = null;
        if (i3 == 4) {
            return new zbzl(zbwzVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
