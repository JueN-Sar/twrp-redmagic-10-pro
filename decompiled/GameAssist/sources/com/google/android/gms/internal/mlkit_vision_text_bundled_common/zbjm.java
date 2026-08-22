package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbjm extends zbub implements zbvn {
    private static final zbjm zbd;
    private int zbe;
    private long zbf;
    private zbgo zbg;
    private zbgw zbh;
    private byte zbj = 2;
    private zbun zbi = zbuf.C();

    static {
        zbjm zbjmVar = new zbjm();
        zbd = zbjmVar;
        zbuf.m(zbjm.class, zbjmVar);
    }

    private zbjm() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbj);
        }
        if (i3 == 2) {
            return zbuf.j(zbd, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001ဂ\u0000\u0002ဉ\u0001\u0003\u001b\u0004ဉ\u0002", new Object[]{"zbe", "zbf", "zbg", "zbi", zbgz.class, "zbh"});
        }
        if (i3 == 3) {
            return new zbjm();
        }
        zbjk zbjkVar = null;
        if (i3 == 4) {
            return new zbjl(zbjkVar);
        }
        if (i3 == 5) {
            return zbd;
        }
        this.zbj = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
