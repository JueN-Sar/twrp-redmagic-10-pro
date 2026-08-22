package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zban extends zbuf implements zbvn {
    private static final zban zbb;
    private int zbd;
    private int zbe;
    private float zbf = 1.0f;

    static {
        zban zbanVar = new zban();
        zbb = zbanVar;
        zbuf.m(zban.class, zbanVar);
    }

    private zban() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002ခ\u0001", new Object[]{"zbd", "zbe", zbam.f12730a, "zbf"});
        }
        if (i3 == 3) {
            return new zban();
        }
        zbak zbakVar = null;
        if (i3 == 4) {
            return new zbal(zbakVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
