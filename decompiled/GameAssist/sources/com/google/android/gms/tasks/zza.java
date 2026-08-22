package com.google.android.gms.tasks;

/* loaded from: classes.dex */
final class zza implements OnSuccessListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ OnTokenCanceledListener f13673a;

    zza(zzb zzbVar, OnTokenCanceledListener onTokenCanceledListener) {
        this.f13673a = onTokenCanceledListener;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final /* bridge */ /* synthetic */ void a(Object obj) {
        this.f13673a.b();
    }
}
