package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbadz extends zbub implements zbvn {
    private static final zbadz zbd;
    private byte zbf = 2;
    private zbun zbe = zbuf.C();

    static {
        zbadz zbadzVar = new zbadz();
        zbd = zbadzVar;
        zbuf.m(zbadz.class, zbadzVar);
    }

    private zbadz() {
    }

    public static zbadz H() {
        return zbd;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbf);
        }
        if (i3 == 2) {
            return zbuf.j(zbd, "\u0001\u0001\u0000\u0000\u0003\u0003\u0001\u0000\u0001\u0000\u0003\u001b", new Object[]{"zbe", zbady.class});
        }
        if (i3 == 3) {
            return new zbadz();
        }
        zbadn zbadnVar = null;
        if (i3 == 4) {
            return new zbadw(zbadnVar);
        }
        if (i3 == 5) {
            return zbd;
        }
        this.zbf = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
