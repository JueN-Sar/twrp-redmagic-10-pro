package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaff extends zbuf implements zbvn {
    private static final zbaff zbb;
    private zbun zbd = zbuf.C();

    static {
        zbaff zbaffVar = new zbaff();
        zbb = zbaffVar;
        zbuf.m(zbaff.class, zbaffVar);
    }

    private zbaff() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zbd", zbafd.class});
        }
        if (i3 == 3) {
            return new zbaff();
        }
        zbafb zbafbVar = null;
        if (i3 == 4) {
            return new zbafe(zbafbVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
