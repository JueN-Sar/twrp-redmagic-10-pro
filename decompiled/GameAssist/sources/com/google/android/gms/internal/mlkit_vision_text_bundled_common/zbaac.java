package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbaac extends zbub implements zbvn {
    private static final zbaac zbd;
    private int zbe;
    private int zbf;
    private byte zbg = 2;

    static {
        zbaac zbaacVar = new zbaac();
        zbd = zbaacVar;
        zbuf.m(zbaac.class, zbaacVar);
    }

    private zbaac() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbg);
        }
        if (i3 == 2) {
            return zbuf.j(zbd, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001᠌\u0000", new Object[]{"zbe", "zbf", zbaab.f12647a});
        }
        if (i3 == 3) {
            return new zbaac();
        }
        zbzz zbzzVar = null;
        if (i3 == 4) {
            return new zbaaa(zbzzVar);
        }
        if (i3 == 5) {
            return zbd;
        }
        this.zbg = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
