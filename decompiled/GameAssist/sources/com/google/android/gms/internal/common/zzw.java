package com.google.android.gms.internal.common;

import com.google.android.gms.common.api.Api;

/* loaded from: classes.dex */
abstract class zzw extends zzj {

    /* renamed from: i, reason: collision with root package name */
    final CharSequence f11406i;

    /* renamed from: j, reason: collision with root package name */
    final zzo f11407j;

    /* renamed from: k, reason: collision with root package name */
    final boolean f11408k;

    /* renamed from: l, reason: collision with root package name */
    int f11409l = 0;

    /* renamed from: m, reason: collision with root package name */
    int f11410m;

    protected zzw(zzx zzxVar, CharSequence charSequence) {
        zzo zzoVar;
        boolean z;
        zzoVar = zzxVar.f11411a;
        this.f11407j = zzoVar;
        z = zzxVar.f11412b;
        this.f11408k = z;
        this.f11410m = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        this.f11406i = charSequence;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0047, code lost:
    
        r3 = r5.f11410m;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004a, code lost:
    
        if (r3 != 1) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004c, code lost:
    
        r1 = r5.f11406i.length();
        r5.f11409l = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0054, code lost:
    
        if (r1 <= r0) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0056, code lost:
    
        r5.f11406i.charAt(r1 - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:?, code lost:
    
        return r5.f11406i.subSequence(r0, r1).toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005e, code lost:
    
        r5.f11410m = r3 - 1;
     */
    @Override // com.google.android.gms.internal.common.zzj
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected final /* bridge */ /* synthetic */ java.lang.Object b() {
        /*
            r5 = this;
            int r0 = r5.f11409l
        L2:
            int r1 = r5.f11409l
            r2 = -1
            if (r1 == r2) goto L6c
            int r1 = r5.e(r1)
            if (r1 != r2) goto L17
            java.lang.CharSequence r1 = r5.f11406i
            int r1 = r1.length()
            r5.f11409l = r2
            r3 = r2
            goto L1d
        L17:
            int r3 = r5.d(r1)
            r5.f11409l = r3
        L1d:
            if (r3 != r0) goto L2e
            int r3 = r3 + 1
            r5.f11409l = r3
            java.lang.CharSequence r1 = r5.f11406i
            int r1 = r1.length()
            if (r3 <= r1) goto L2
            r5.f11409l = r2
            goto L2
        L2e:
            if (r0 >= r1) goto L35
            java.lang.CharSequence r3 = r5.f11406i
            r3.charAt(r0)
        L35:
            if (r0 >= r1) goto L3e
            java.lang.CharSequence r3 = r5.f11406i
            int r4 = r1 + (-1)
            r3.charAt(r4)
        L3e:
            boolean r3 = r5.f11408k
            if (r3 == 0) goto L47
            if (r0 != r1) goto L47
            int r0 = r5.f11409l
            goto L2
        L47:
            int r3 = r5.f11410m
            r4 = 1
            if (r3 != r4) goto L5e
            java.lang.CharSequence r1 = r5.f11406i
            int r1 = r1.length()
            r5.f11409l = r2
            if (r1 <= r0) goto L61
            java.lang.CharSequence r2 = r5.f11406i
            int r3 = r1 + (-1)
            r2.charAt(r3)
            goto L61
        L5e:
            int r3 = r3 + r2
            r5.f11410m = r3
        L61:
            java.lang.CharSequence r5 = r5.f11406i
            java.lang.CharSequence r5 = r5.subSequence(r0, r1)
            java.lang.String r5 = r5.toString()
            goto L70
        L6c:
            r5.c()
            r5 = 0
        L70:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.common.zzw.b():java.lang.Object");
    }

    abstract int d(int i2);

    abstract int e(int i2);
}
