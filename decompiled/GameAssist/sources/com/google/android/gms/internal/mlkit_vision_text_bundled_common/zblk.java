package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zblk extends zbuf implements zbvn {
    private static final zblk zbb;
    private int zbd;
    private float zbe;
    private float zbf;
    private float zbg;
    private float zbh;
    private float zbi;

    static {
        zblk zblkVar = new zblk();
        zbb = zblkVar;
        zbuf.m(zblk.class, zblkVar);
    }

    private zblk() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ခ\u0000\u0002ခ\u0001\u0003ခ\u0002\u0004ခ\u0003\u0005ခ\u0004", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbi"});
        }
        if (i3 == 3) {
            return new zblk();
        }
        zbli zbliVar = null;
        if (i3 == 4) {
            return new zblj(zbliVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
