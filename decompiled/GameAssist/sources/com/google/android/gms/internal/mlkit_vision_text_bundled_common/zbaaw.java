package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaaw extends zbuf implements zbvn {
    private static final zbaaw zbb;
    private int zbd;
    private byte zbg = 2;
    private String zbe = "";
    private float zbf = 1.0f;

    static {
        zbaaw zbaawVar = new zbaaw();
        zbb = zbaawVar;
        zbuf.m(zbaaw.class, zbaawVar);
    }

    private zbaaw() {
    }

    public final float E() {
        return this.zbf;
    }

    public final String H() {
        return this.zbe;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbg);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001ᔈ\u0000\u0002ခ\u0001", new Object[]{"zbd", "zbe", "zbf"});
        }
        if (i3 == 3) {
            return new zbaaw();
        }
        zbaad zbaadVar = null;
        if (i3 == 4) {
            return new zbaav(zbaadVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbg = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
