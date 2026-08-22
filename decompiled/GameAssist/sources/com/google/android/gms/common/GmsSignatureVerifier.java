package com.google.android.gms.common;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.internal.common.zzag;
import com.google.errorprone.annotations.RestrictedInheritance;
import java.util.HashMap;

@ShowFirstParty
@KeepForSdk
@RestrictedInheritance(allowedOnPath = ".*javatests/com/google/android/gmscore/integ/client/common/robolectric/.*", explanation = "Sub classing of GMS Core's APIs are restricted to testing fakes.", link = "go/gmscore-restrictedinheritance")
/* loaded from: classes.dex */
public class GmsSignatureVerifier {

    /* renamed from: a, reason: collision with root package name */
    private static final zzab f10495a;

    /* renamed from: b, reason: collision with root package name */
    private static final zzab f10496b;

    /* renamed from: c, reason: collision with root package name */
    private static final HashMap f10497c;

    static {
        zzz zzzVar = new zzz();
        zzzVar.d("com.google.android.gms");
        zzzVar.a(204200000L);
        zzl zzlVar = zzn.f11308d;
        zzzVar.c(zzag.p(zzlVar.zzf(), zzn.f11306b.zzf()));
        zzl zzlVar2 = zzn.f11307c;
        zzzVar.b(zzag.p(zzlVar2.zzf(), zzn.f11305a.zzf()));
        f10495a = zzzVar.e();
        zzz zzzVar2 = new zzz();
        zzzVar2.d("com.android.vending");
        zzzVar2.a(82240000L);
        zzzVar2.c(zzag.o(zzlVar.zzf()));
        zzzVar2.b(zzag.o(zzlVar2.zzf()));
        f10496b = zzzVar2.e();
        f10497c = new HashMap();
    }
}
