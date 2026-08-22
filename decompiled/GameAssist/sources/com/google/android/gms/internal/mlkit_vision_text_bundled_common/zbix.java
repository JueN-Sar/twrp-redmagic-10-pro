package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbix extends zbuf implements zbvn {
    private static final zbix zbb;
    private int zbd;
    private zbiw zbg;
    private zbhl zbh;
    private zbhl zbi;
    private zbalp zbj;
    private float zbm;
    private boolean zbp;
    private zbxb zbq;
    private String zbe = "";
    private String zbf = "";
    private String zbk = "en";
    private int zbl = -1;
    private zbun zbn = zbuf.C();
    private zbun zbo = zbuf.C();
    private int zbr = -1;

    static {
        zbix zbixVar = new zbix();
        zbb = zbixVar;
        zbuf.m(zbix.class, zbixVar);
    }

    private zbix() {
    }

    public static zbiu E() {
        return (zbiu) zbb.u();
    }

    static /* synthetic */ void H(zbix zbixVar, String str) {
        zbixVar.zbd |= 1;
        zbixVar.zbe = "PassThroughCoarseClassifier";
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u000e\u0000\u0001\u0001\u000f\u000e\u0000\u0002\u0000\u0001ဈ\u0000\u0002င\u0007\u0003ခ\b\u0004\u001a\u0005\u001a\u0006ဉ\u0002\bဇ\t\tဉ\n\nဉ\u0003\u000bဈ\u0006\fဉ\u0004\rင\u000b\u000eဉ\u0005\u000fဈ\u0001", new Object[]{"zbd", "zbe", "zbl", "zbm", "zbn", "zbo", "zbg", "zbp", "zbq", "zbh", "zbk", "zbi", "zbr", "zbj", "zbf"});
        }
        if (i3 == 3) {
            return new zbix();
        }
        zbit zbitVar = null;
        if (i3 == 4) {
            return new zbiu(zbitVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
