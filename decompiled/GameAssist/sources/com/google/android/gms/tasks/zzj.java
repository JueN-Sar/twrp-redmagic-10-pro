package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzj implements zzq {

    /* renamed from: a, reason: collision with root package name */
    private final Executor f13702a;

    /* renamed from: b, reason: collision with root package name */
    private final Object f13703b = new Object();

    /* renamed from: c, reason: collision with root package name */
    private OnCompleteListener f13704c;

    public zzj(Executor executor, OnCompleteListener onCompleteListener) {
        this.f13702a = executor;
        this.f13704c = onCompleteListener;
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void c(Task task) {
        synchronized (this.f13703b) {
            try {
                if (this.f13704c == null) {
                    return;
                }
                this.f13702a.execute(new zzi(this, task));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void zzc() {
        synchronized (this.f13703b) {
            this.f13704c = null;
        }
    }
}
