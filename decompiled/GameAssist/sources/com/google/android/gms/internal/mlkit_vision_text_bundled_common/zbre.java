package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbre extends zbuf implements zbvn {
    private static final zbre zbb;
    private int zbd;
    private int zbe;
    private zbpw zbg;
    private zbpw zbh;
    private float zbj;
    private zbpw zbl;
    private zbpw zbm;
    private zbqm zbn;
    private zbri zbp;
    private byte zbq = 2;
    private zbul zbf = zbuf.A();
    private String zbi = "";
    private zbun zbk = zbuf.C();
    private boolean zbo = true;

    static {
        zbre zbreVar = new zbre();
        zbb = zbreVar;
        zbuf.m(zbre.class, zbreVar);
    }

    private zbre() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbq);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\f\u0000\u0001\u0001\f\f\u0000\u0002\b\u0001ᔄ\u0000\u0002ᔉ\u0001\u0003ᐉ\u0002\u0004ဈ\u0003\u0005ခ\u0004\u0006ᐉ\u0005\u0007ᐉ\u0006\bᐉ\u0007\tဇ\b\nᐉ\t\u000bЛ\f\u0016", new Object[]{"zbd", "zbe", "zbg", "zbh", "zbi", "zbj", "zbl", "zbm", "zbn", "zbo", "zbp", "zbk", zbqa.class, "zbf"});
        }
        if (i3 == 3) {
            return new zbre();
        }
        zbpu zbpuVar = null;
        if (i3 == 4) {
            return new zbrd(zbpuVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbq = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
