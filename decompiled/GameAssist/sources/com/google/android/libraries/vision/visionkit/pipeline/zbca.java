package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;

/* loaded from: classes.dex */
public final class zbca extends zbuf implements zbvn {
    private static final zbca zbb;
    private int zbd;
    private Object zbf;
    private zbdo zbg;
    private boolean zbh;
    private zbg zbi;
    private zbfc zbj;
    private zbd zbk;
    private int zbl;
    private int zbe = 0;
    private byte zbm = 2;

    static {
        zbca zbcaVar = new zbca();
        zbb = zbcaVar;
        zbuf.m(zbca.class, zbcaVar);
    }

    private zbca() {
    }

    public static zbbz F() {
        return (zbbz) zbb.u();
    }

    static /* synthetic */ void I(zbca zbcaVar, zbfc zbfcVar) {
        zbfcVar.getClass();
        zbcaVar.zbj = zbfcVar;
        zbcaVar.zbd |= 8;
    }

    static /* synthetic */ void J(zbca zbcaVar, zbdo zbdoVar) {
        zbdoVar.getClass();
        zbcaVar.zbg = zbdoVar;
        zbcaVar.zbd |= 1;
    }

    public final int E() {
        return this.zbl;
    }

    public final boolean K() {
        if (this.zbe == 6) {
            return ((Boolean) this.zbf).booleanValue();
        }
        return false;
    }

    public final boolean L() {
        if (this.zbe == 5) {
            return ((Boolean) this.zbf).booleanValue();
        }
        return false;
    }

    public final boolean M() {
        return (this.zbd & 32) != 0;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbm);
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\b\u0001\u0001\u0001\b\b\u0000\u0000\u0001\u0001ᐉ\u0000\u0002ဇ\u0001\u0003ဉ\u0003\u0004ဉ\u0002\u0005:\u0000\u0006:\u0000\u0007ဉ\u0004\bင\u0005", new Object[]{"zbf", "zbe", "zbd", "zbg", "zbh", "zbj", "zbi", "zbk", "zbl"});
        }
        if (i3 == 3) {
            return new zbca();
        }
        zbby zbbyVar = null;
        if (i3 == 4) {
            return new zbbz(zbbyVar);
        }
        if (i3 == 5) {
            return zbb;
        }
        this.zbm = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
