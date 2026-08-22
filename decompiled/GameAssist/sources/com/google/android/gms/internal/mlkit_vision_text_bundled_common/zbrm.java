package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbrm extends zbuf implements zbvn {
    private static final zbrm zbb;
    private int zbd;
    private zbpw zbe;
    private int zbh;
    private int zbi;
    private byte zbj = 2;
    private String zbf = "";
    private zbul zbg = zbuf.A();

    static {
        zbrm zbrmVar = new zbrm();
        zbb = zbrmVar;
        zbuf.m(zbrm.class, zbrmVar);
    }

    private zbrm() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbj);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0001\u0001ᐉ\u0000\u0002ဈ\u0001\u0003\u0016\u0004င\u0002\u0005᠌\u0003", new Object[]{"zbd", "zbe", "zbf", "zbg", "zbh", "zbi", zbabe.a()});
        }
        if (i3 == 3) {
            return new zbrm();
        }
        zbpu zbpuVar = null;
        if (i3 == 4) {
            return new zbrl(zbpuVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbj = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
