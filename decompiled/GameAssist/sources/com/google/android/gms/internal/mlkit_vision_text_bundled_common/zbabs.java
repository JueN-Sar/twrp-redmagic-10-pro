package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbabs extends zbuf implements zbvn {
    private static final zbabs zbb;
    private int zbd;
    private zbuk zbe = zbuf.z();
    private zbuk zbf = zbuf.z();
    private int zbg;
    private int zbh;
    private int zbi;
    private int zbj;

    static {
        zbabs zbabsVar = new zbabs();
        zbb = zbabsVar;
        zbuf.m(zbabs.class, zbabsVar);
    }

    private zbabs() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0002\u0000\u0001\u0013\u0002\u0013\u0003ဋ\u0000\u0004ဋ\u0001\u0005ဋ\u0002\u0006ဋ\u0003", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbi", "zbj"});
        }
        if (i3 == 3) {
            return new zbabs();
        }
        zbabq zbabqVar = null;
        if (i3 == 4) {
            return new zbabr(zbabqVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
