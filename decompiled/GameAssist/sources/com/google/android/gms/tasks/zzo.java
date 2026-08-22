package com.google.android.gms.tasks;

import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzo implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Task f13715c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ zzp f13716h;

    zzo(zzp zzpVar, Task task) {
        this.f13716h = zzpVar;
        this.f13715c = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SuccessContinuation successContinuation;
        try {
            successContinuation = this.f13716h.f13718b;
            Task a2 = successContinuation.a(this.f13715c.i());
            if (a2 == null) {
                this.f13716h.d(new NullPointerException("Continuation returned null"));
                return;
            }
            zzp zzpVar = this.f13716h;
            Executor executor = TaskExecutors.f13672b;
            a2.g(executor, zzpVar);
            a2.e(executor, this.f13716h);
            a2.a(executor, this.f13716h);
        } catch (RuntimeExecutionException e2) {
            if (e2.getCause() instanceof Exception) {
                this.f13716h.d((Exception) e2.getCause());
            } else {
                this.f13716h.d(e2);
            }
        } catch (CancellationException unused) {
            this.f13716h.b();
        } catch (Exception e3) {
            this.f13716h.d(e3);
        }
    }
}
