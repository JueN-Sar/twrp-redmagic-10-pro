package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzd implements zzq {

    /* renamed from: a, reason: collision with root package name */
    private final Executor f13688a;

    /* renamed from: b, reason: collision with root package name */
    private final Continuation f13689b;

    /* renamed from: c, reason: collision with root package name */
    private final zzw f13690c;

    @Override // com.google.android.gms.tasks.zzq
    public final void c(Task task) {
        this.f13688a.execute(new zzc(this, task));
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void zzc() {
        throw new UnsupportedOperationException();
    }
}
