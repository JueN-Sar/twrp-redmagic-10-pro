package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaft extends zbuf implements zbvn {
    private static final zbaft zbb;
    private int zbd;
    private zbafw zbe;
    private zbaey zbf;
    private zbaff zbg;

    static {
        zbaft zbaftVar = new zbaft();
        zbb = zbaftVar;
        zbuf.m(zbaft.class, zbaftVar);
    }

    private zbaft() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zbd", "zbe", "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbaft();
        }
        zbafr zbafrVar = null;
        if (i3 == 4) {
            return new zbafs(zbafrVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
