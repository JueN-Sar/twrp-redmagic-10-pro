package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.List;

/* loaded from: classes.dex */
public final class zbabl extends zbub implements zbvn {
    private static final zbabl zbd;
    private int zbe;
    private int zbf;
    private int zbg;
    private zbaat zbh;
    private int zbj;
    private int zbk;
    private zbaax zbm;
    private long zbp;
    private byte zbq = 2;
    private String zbi = "";
    private zbun zbl = zbuf.C();
    private String zbn = "";
    private zbun zbo = zbuf.C();

    static {
        zbabl zbablVar = new zbabl();
        zbd = zbablVar;
        zbuf.m(zbabl.class, zbablVar);
    }

    private zbabl() {
    }

    public static zbabl H() {
        return zbd;
    }

    public final List I() {
        return this.zbl;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbq);
        }
        if (i3 == 2) {
            return zbuf.j(zbd, "\u0001\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0002\u0003\u0001င\u0000\u0002င\u0001\u0003ᐉ\u0002\u0004ဈ\u0003\u0005င\u0004\u0006င\u0005\u0007Л\bᐉ\u0006\tဈ\u0007\n\u001a\u000bဂ\b", new Object[]{"zbe", "zbf", "zbg", "zbh", "zbi", "zbj", "zbk", "zbl", zbabj.class, "zbm", "zbn", "zbo", "zbp"});
        }
        if (i3 == 3) {
            return new zbabl();
        }
        zbaad zbaadVar = null;
        if (i3 == 4) {
            return new zbabk(zbaadVar);
        }
        if (i3 == 5) {
            return zbd;
        }
        this.zbq = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
