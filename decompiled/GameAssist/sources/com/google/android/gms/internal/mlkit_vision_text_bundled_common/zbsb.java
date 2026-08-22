package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbsb extends zbuf implements zbvn {
    private static final zbsb zbb;
    private zbun zbd = zbuf.C();
    private zbun zbe = zbuf.C();

    static {
        zbsb zbsbVar = new zbsb();
        zbb = zbsbVar;
        zbuf.m(zbsb.class, zbsbVar);
    }

    private zbsb() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0000\u0001\u000f\u0002\u0000\u0002\u0000\u0001\u001b\u000f\u001a", new Object[]{"zbd", zbrx.class, "zbe"});
        }
        if (i3 == 3) {
            return new zbsb();
        }
        zbrt zbrtVar = null;
        if (i3 == 4) {
            return new zbsa(zbrtVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
