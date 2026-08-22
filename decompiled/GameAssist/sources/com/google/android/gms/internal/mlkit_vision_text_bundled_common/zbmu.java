package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

@Deprecated
/* loaded from: classes.dex */
public final class zbmu extends zbub implements zbvn {
    private static final zbmu zbd;
    private int zbe;
    private byte zbg = 2;
    private boolean zbf = true;

    static {
        zbmu zbmuVar = new zbmu();
        zbd = zbmuVar;
        zbuf.m(zbmu.class, zbmuVar);
    }

    private zbmu() {
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
            return new zbmu();
        }
        zbmq zbmqVar = null;
        if (i3 == 4) {
            return new zbmt(zbmqVar);
        }
        if (i3 == 5) {
            return zbd;
        }
        this.zbg = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
