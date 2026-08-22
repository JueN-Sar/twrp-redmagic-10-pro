package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbpk extends zbuf implements zbvn {
    private static final zbpk zbb;
    private boolean zbh;
    private boolean zbi;
    private boolean zbn;
    private boolean zbo;
    private zbvg zbd = zbvg.a();
    private String zbe = "";
    private String zbf = "";
    private String zbg = "";
    private String zbj = "";
    private String zbk = "";
    private String zbl = "";
    private zbun zbm = zbuf.C();
    private String zbp = "";
    private zbun zbq = zbuf.C();

    static {
        zbpk zbpkVar = new zbpk();
        zbb = zbpkVar;
        zbuf.m(zbpk.class, zbpkVar);
    }

    private zbpk() {
    }

    public static zbpi E() {
        return (zbpi) zbb.u();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0004\u000e\u0000\u0000\u0001\u0010\u000e\u0001\u0002\u0000\u00012\u0004\u0007\u0005Ȉ\u0006Ȉ\u0007Ȉ\b\u0007\tȈ\nȈ\u000bȚ\f\u0007\rȈ\u000e\u0007\u000fȈ\u0010Ț", new Object[]{"zbd", zbpj.f12921a, "zbi", "zbe", "zbf", "zbj", "zbh", "zbk", "zbl", "zbm", "zbn", "zbg", "zbo", "zbp", "zbq"});
        }
        if (i3 == 3) {
            return new zbpk();
        }
        zbph zbphVar = null;
        if (i3 == 4) {
            return new zbpi(zbphVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
