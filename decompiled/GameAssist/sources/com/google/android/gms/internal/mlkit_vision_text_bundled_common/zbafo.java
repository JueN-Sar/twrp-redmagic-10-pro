package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbafo extends zbuf implements zbvn {
    private static final zbafo zbb;
    private int zbd;
    private int zbe;
    private long zbf;
    private long zbg;

    static {
        zbafo zbafoVar = new zbafo();
        zbb = zbafoVar;
        zbuf.m(zbafo.class, zbafoVar);
    }

    private zbafo() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002ဂ\u0001\u0003ဂ\u0002", new Object[]{"zbd", "zbe", "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbafo();
        }
        zbafj zbafjVar = null;
        if (i3 == 4) {
            return new zbafn(zbafjVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
