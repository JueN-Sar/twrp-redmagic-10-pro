package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzf<TResult, TContinuationResult> implements OnSuccessListener<TContinuationResult>, OnFailureListener, OnCanceledListener, zzq {

    /* renamed from: a, reason: collision with root package name */
    private final Executor f13693a;

    /* renamed from: b, reason: collision with root package name */
    private final Continuation f13694b;

    /* renamed from: c, reason: collision with root package name */
    private final zzw f13695c;

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void a(Object obj) {
        this.f13695c.o(obj);
    }

    @Override // com.google.android.gms.tasks.OnCanceledListener
    public final void b() {
        this.f13695c.p();
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void c(Task task) {
        this.f13693a.execute(new zze(this, task));
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void d(Exception exc) {
        this.f13695c.n(exc);
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void zzc() {
        throw new UnsupportedOperationException();
    }
}
