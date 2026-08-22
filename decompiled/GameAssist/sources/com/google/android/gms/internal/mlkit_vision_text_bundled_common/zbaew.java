package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaew extends zbuf implements zbvn {
    private static final zbaew zbb;
    private int zbd;
    private float zbe = 0.01f;

    static {
        zbaew zbaewVar = new zbaew();
        zbb = zbaewVar;
        zbuf.m(zbaew.class, zbaewVar);
    }

    private zbaew() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ခ\u0000", new Object[]{"zbd", "zbe"});
        }
        if (i3 == 3) {
            return new zbaew();
        }
        zbaeu zbaeuVar = null;
        if (i3 == 4) {
            return new zbaev(zbaeuVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
