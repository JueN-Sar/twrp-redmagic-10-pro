package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaam extends zbuf implements zbvn {
    private static final zbaam zbb;
    private int zbd;
    private zbpb zbf;
    private float zbg;
    private byte zbh = 2;
    private int zbe = 2;

    static {
        zbaam zbaamVar = new zbaam();
        zbb = zbaamVar;
        zbuf.m(zbaam.class, zbaamVar);
    }

    private zbaam() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbh);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0007\u0003\u0000\u0000\u0001\u0001᠌\u0000\u0002ᐉ\u0001\u0007ခ\u0002", new Object[]{"zbd", "zbe", zbaak.f12649a, "zbf", "zbg"});
        }
        if (i3 == 3) {
            return new zbaam();
        }
        zbaad zbaadVar = null;
        if (i3 == 4) {
            return new zbaal(zbaadVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbh = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
