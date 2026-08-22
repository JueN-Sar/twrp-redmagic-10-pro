package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzp<TResult, TContinuationResult> implements OnSuccessListener<TContinuationResult>, OnFailureListener, OnCanceledListener, zzq {

    /* renamed from: a, reason: collision with root package name */
    private final Executor f13717a;

    /* renamed from: b, reason: collision with root package name */
    private final SuccessContinuation f13718b;

    /* renamed from: c, reason: collision with root package name */
    private final zzw f13719c;

    public zzp(Executor executor, SuccessContinuation successContinuation, zzw zzwVar) {
        this.f13717a = executor;
        this.f13718b = successContinuation;
        this.f13719c = zzwVar;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void a(Object obj) {
        this.f13719c.o(obj);
    }

    @Override // com.google.android.gms.tasks.OnCanceledListener
    public final void b() {
        this.f13719c.p();
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void c(Task task) {
        this.f13717a.execute(new zzo(this, task));
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void d(Exception exc) {
        this.f13719c.n(exc);
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void zzc() {
        throw new UnsupportedOperationException();
    }
}
