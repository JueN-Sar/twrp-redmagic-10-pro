package com.google.android.gms.tasks;

/* loaded from: classes.dex */
final class zzb extends CancellationToken {

    /* renamed from: a, reason: collision with root package name */
    private final zzw f13685a = new zzw();

    zzb() {
    }

    @Override // com.google.android.gms.tasks.CancellationToken
    public final boolean a() {
        return this.f13685a.k();
    }

    @Override // com.google.android.gms.tasks.CancellationToken
    public final CancellationToken b(OnTokenCanceledListener onTokenCanceledListener) {
        this.f13685a.g(TaskExecutors.f13671a, new zza(this, onTokenCanceledListener));
        return this;
    }

    public final void c() {
        this.f13685a.r(null);
    }
}
