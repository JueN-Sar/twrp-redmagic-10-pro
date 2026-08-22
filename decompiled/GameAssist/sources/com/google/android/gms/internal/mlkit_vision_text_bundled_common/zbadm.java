package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbadm extends zbub implements zbvn {
    private static final zbadm zbd;
    private int zbe;
    private Object zbg;
    private Object zbi;
    private zbtc zbl;
    private zbxb zbm;
    private int zbn;
    private int zbo;
    private boolean zbp;
    private int zbq;
    private zbtc zbr;
    private int zbf = 0;
    private int zbh = 0;
    private byte zbs = 2;
    private String zbj = "FaceAttributesClientBrainEmbedder";
    private String zbk = "";

    static {
        zbadm zbadmVar = new zbadm();
        zbd = zbadmVar;
        zbuf.m(zbadm.class, zbadmVar);
    }

    private zbadm() {
        zbtc zbtcVar = zbtc.zbb;
        this.zbl = zbtcVar;
        this.zbp = true;
        this.zbq = 1;
        this.zbr = zbtcVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbs);
        }
        if (i3 == 2) {
            return zbuf.j(zbd, "\u0004\r\u0002\u0001\u0002\u0010\r\u0000\u0000\u0001\u0002<\u0000\u0003;\u0000\u0004ဇ\u0006\u0005᠌\u0007\u0007м\u0001\b;\u0001\tဈ\u0000\nဈ\u0001\fင\u0004\rဉ\u0003\u000eည\b\u000fည\u0002\u0010င\u0005", new Object[]{"zbg", "zbf", "zbi", "zbh", "zbe", zbabp.class, "zbp", "zbq", zbadk.f12670a, zbadz.class, "zbj", "zbk", "zbn", "zbm", "zbr", "zbl", "zbo"});
        }
        if (i3 == 3) {
            return new zbadm();
        }
        zbadj zbadjVar = null;
        if (i3 == 4) {
            return new zbadl(zbadjVar);
        }
        if (i3 == 5) {
            return zbd;
        }
        this.zbs = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
