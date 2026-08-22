package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbsp extends zbuf implements zbvn {
    private static final zbsp zbb;
    private String zbd = "";
    private zbtc zbe = zbtc.zbb;

    static {
        zbsp zbspVar = new zbsp();
        zbb = zbspVar;
        zbuf.m(zbsp.class, zbspVar);
    }

    private zbsp() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return new zbvw(zbb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\n", new Object[]{"zbd", "zbe"});
        }
        if (i3 == 3) {
            return new zbsp();
        }
        zbsn zbsnVar = null;
        if (i3 == 4) {
            return new zbso(zbsnVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
