package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzl implements zzq {

    /* renamed from: a, reason: collision with root package name */
    private final Executor f13707a;

    /* renamed from: b, reason: collision with root package name */
    private final Object f13708b = new Object();

    /* renamed from: c, reason: collision with root package name */
    private OnFailureListener f13709c;

    public zzl(Executor executor, OnFailureListener onFailureListener) {
        this.f13707a = executor;
        this.f13709c = onFailureListener;
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void c(Task task) {
        if (task.l() || task.j()) {
            return;
        }
        synchronized (this.f13708b) {
            try {
                if (this.f13709c == null) {
                    return;
                }
                this.f13707a.execute(new zzk(this, task));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void zzc() {
        synchronized (this.f13708b) {
            this.f13709c = null;
        }
    }
}
