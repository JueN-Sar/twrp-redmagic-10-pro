package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzn implements zzq {

    /* renamed from: a, reason: collision with root package name */
    private final Executor f13712a;

    /* renamed from: b, reason: collision with root package name */
    private final Object f13713b = new Object();

    /* renamed from: c, reason: collision with root package name */
    private OnSuccessListener f13714c;

    public zzn(Executor executor, OnSuccessListener onSuccessListener) {
        this.f13712a = executor;
        this.f13714c = onSuccessListener;
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void c(Task task) {
        if (task.l()) {
            synchronized (this.f13713b) {
                try {
                    if (this.f13714c == null) {
                        return;
                    }
                    this.f13712a.execute(new zzm(this, task));
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void zzc() {
        synchronized (this.f13713b) {
            this.f13714c = null;
        }
    }
}
