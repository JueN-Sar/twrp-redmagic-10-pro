package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzh implements zzq {

    /* renamed from: a, reason: collision with root package name */
    private final Executor f13697a;

    /* renamed from: b, reason: collision with root package name */
    private final Object f13698b = new Object();

    /* renamed from: c, reason: collision with root package name */
    private OnCanceledListener f13699c;

    public zzh(Executor executor, OnCanceledListener onCanceledListener) {
        this.f13697a = executor;
        this.f13699c = onCanceledListener;
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void c(Task task) {
        if (task.j()) {
            synchronized (this.f13698b) {
                try {
                    if (this.f13699c == null) {
                        return;
                    }
                    this.f13697a.execute(new zzg(this));
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void zzc() {
        synchronized (this.f13698b) {
            this.f13699c = null;
        }
    }
}
