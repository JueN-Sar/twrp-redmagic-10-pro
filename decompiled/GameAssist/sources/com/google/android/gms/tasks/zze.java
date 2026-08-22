package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zze implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Task f13691c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ zzf f13692h;

    zze(zzf zzfVar, Task task) {
        this.f13692h = zzfVar;
        this.f13691c = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzw zzwVar;
        zzw zzwVar2;
        zzw zzwVar3;
        Continuation continuation;
        try {
            continuation = this.f13692h.f13694b;
            Task task = (Task) continuation.a(this.f13691c);
            if (task == null) {
                this.f13692h.d(new NullPointerException("Continuation returned null"));
                return;
            }
            zzf zzfVar = this.f13692h;
            Executor executor = TaskExecutors.f13672b;
            task.g(executor, zzfVar);
            task.e(executor, this.f13692h);
            task.a(executor, this.f13692h);
        } catch (RuntimeExecutionException e2) {
            if (e2.getCause() instanceof Exception) {
                zzwVar3 = this.f13692h.f13695c;
                zzwVar3.n((Exception) e2.getCause());
            } else {
                zzwVar2 = this.f13692h.f13695c;
                zzwVar2.n(e2);
            }
        } catch (Exception e3) {
            zzwVar = this.f13692h.f13695c;
            zzwVar.n(e3);
        }
    }
}
