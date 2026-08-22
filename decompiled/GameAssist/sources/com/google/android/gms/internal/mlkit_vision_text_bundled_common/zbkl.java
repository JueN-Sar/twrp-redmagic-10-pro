package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
final class zbkl extends zbkn {

    /* renamed from: l, reason: collision with root package name */
    final /* synthetic */ zbkm f12843l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zbkl(zbkm zbkmVar, zbko zbkoVar, CharSequence charSequence) {
        super(zbkoVar, charSequence);
        this.f12843l = zbkmVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbkn
    public final int d(int i2) {
        return i2 + this.f12843l.f12844a.length();
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0026, code lost:
    
        r7 = r7 + 1;
     */
    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbkn
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int e(int r7) {
        /*
            r6 = this;
            java.lang.CharSequence r0 = r6.f12845i
            int r0 = r0.length()
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbkm r1 = r6.f12843l
            java.lang.String r1 = r1.f12844a
            int r1 = r1.length()
            int r0 = r0 - r1
        Lf:
            if (r7 > r0) goto L2d
            r2 = 0
        L12:
            if (r2 >= r1) goto L2c
            java.lang.CharSequence r3 = r6.f12845i
            int r4 = r2 + r7
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbkm r5 = r6.f12843l
            java.lang.String r5 = r5.f12844a
            char r3 = r3.charAt(r4)
            char r4 = r5.charAt(r2)
            if (r3 == r4) goto L29
            int r7 = r7 + 1
            goto Lf
        L29:
            int r2 = r2 + 1
            goto L12
        L2c:
            return r7
        L2d:
            r6 = -1
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbkl.e(int):int");
    }
}
