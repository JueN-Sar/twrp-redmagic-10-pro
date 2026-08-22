package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

@Deprecated
/* loaded from: classes.dex */
public final class zbms extends zbuf implements zbvn {
    private static final zbms zbb;
    private int zbd;
    private zbmu zbj;
    private byte zbk = 2;
    private String zbe = "";
    private zbun zbf = zbuf.C();
    private zbun zbg = zbuf.C();
    private zbun zbh = zbuf.C();
    private zbun zbi = zbuf.C();

    static {
        zbms zbmsVar = new zbms();
        zbb = zbmsVar;
        zbuf.m(zbms.class, zbmsVar);
    }

    private zbms() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbk);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0006\u0000\u0001\u0001ϫ\u0006\u0000\u0004\u0001\u0001ဈ\u0000\u0002\u001a\u0003\u001a\u0004ᐉ\u0001Ϫ\u001aϫ\u001a", new Object[]{"zbd", "zbe", "zbf", "zbh", "zbj", "zbg", "zbi"});
        }
        if (i3 == 3) {
            return new zbms();
        }
        zbmq zbmqVar = null;
        if (i3 == 4) {
            return new zbmr(zbmqVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbk = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
