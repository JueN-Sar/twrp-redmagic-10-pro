package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbgd extends zbuf implements zbvn {
    private static final zbgd zbb;
    private int zbd;
    private Object zbf;
    private int zbg;
    private long zbj;
    private int zbl;
    private int zbe = 0;
    private String zbh = "";
    private zbun zbi = zbuf.C();
    private String zbk = "";

    static {
        zbgd zbgdVar = new zbgd();
        zbb = zbgdVar;
        zbuf.m(zbgd.class, zbgdVar);
    }

    private zbgd() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u000b\u0001\u0001\u0001\u000b\u000b\u0000\u0001\u0000\u0001᠌\u0000\u0002ဈ\u0001\u0003\u001b\u0004ဈ\u0003\u0005<\u0000\u0006<\u0000\u0007<\u0000\b<\u0000\t<\u0000\nဂ\u0002\u000b᠌\u0004", new Object[]{"zbf", "zbe", "zbd", "zbg", zbge.f12800a, "zbh", "zbi", zbgc.class, "zbk", zbci.class, zbaj.class, zbcc.class, zbaq.class, zbcp.class, "zbj", "zbl", zbfz.f12799a});
        }
        if (i3 == 3) {
            return new zbgd();
        }
        zbfy zbfyVar = null;
        if (i3 == 4) {
            return new zbga(zbfyVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
