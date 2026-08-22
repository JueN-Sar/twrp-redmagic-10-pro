package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtp;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbun;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;
import java.util.List;

/* loaded from: classes.dex */
public final class zber extends zbuf implements zbvn {
    private static final zber zbb;
    private int zbd;
    private int zbe;
    private String zbf = "";
    private zbun zbg = zbuf.C();

    static {
        zber zberVar = new zber();
        zbb = zberVar;
        zbuf.m(zber.class, zberVar);
    }

    private zber() {
    }

    public static zber H(byte[] bArr, zbtp zbtpVar) {
        return (zber) zbuf.y(zbb, bArr, zbtpVar);
    }

    public final int E() {
        return this.zbe;
    }

    public final String I() {
        return this.zbf;
    }

    public final List J() {
        return this.zbg;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zbuf.j(zbb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001င\u0000\u0002ဈ\u0001\u0003\u001b", new Object[]{"zbd", "zbe", "zbf", "zbg", zbad.class});
        }
        if (i3 == 3) {
            return new zber();
        }
        zbep zbepVar = null;
        if (i3 == 4) {
            return new zbeq(zbepVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zbb;
    }
}
