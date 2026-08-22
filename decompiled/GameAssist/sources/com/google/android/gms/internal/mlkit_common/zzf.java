package com.google.android.gms.internal.mlkit_common;

/* loaded from: classes.dex */
public final class zzf {

    /* renamed from: a, reason: collision with root package name */
    private final zzac f11504a = new zzac();

    /* renamed from: b, reason: collision with root package name */
    private Boolean f11505b;

    /* synthetic */ zzf(zze zzeVar) {
    }

    public final zzf a(zzk zzkVar) {
        zzt.c(this.f11505b, "Must call internal() or external() before appending rules.");
        this.f11504a.b(zzkVar);
        return this;
    }

    public final zzf b() {
        zzt.e(this.f11505b == null, "A SourcePolicy can only set internal() or external() once.");
        this.f11505b = Boolean.FALSE;
        return this;
    }

    public final zzf c() {
        zzt.e(this.f11505b == null, "A SourcePolicy can only set internal() or external() once.");
        this.f11505b = Boolean.TRUE;
        return this;
    }

    public final zzh d() {
        zzt.c(this.f11505b, "Must call internal() or external() when building a SourcePolicy.");
        return new zzh(this.f11505b.booleanValue(), false, this.f11504a.c(), null);
    }
}
