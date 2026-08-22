package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public final class zbakc extends zbuf implements zbvn {
    private static final zbakc zbb;

    static {
        zbakc zbakcVar = new zbakc();
        zbb = zbakcVar;
        zbuf.m(zbakc.class, zbakcVar);
    }

    private zbakc() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        zbaka zbakaVar = null;
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0000\u0000", null);
        }
        if (i3 == 3) {
            return new zbakc();
        }
        if (i3 == 4) {
            return new zbakb(zbakaVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
