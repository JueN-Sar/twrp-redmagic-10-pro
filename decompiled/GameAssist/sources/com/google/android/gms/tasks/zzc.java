package com.google.android.gms.tasks;

/* loaded from: classes.dex */
final class zzc implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Task f13686c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ zzd f13687h;

    zzc(zzd zzdVar, Task task) {
        this.f13687h = zzdVar;
        this.f13686c = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzw zzwVar;
        zzw zzwVar2;
        zzw zzwVar3;
        Continuation continuation;
        zzw zzwVar4;
        zzw zzwVar5;
        if (this.f13686c.j()) {
            zzwVar5 = this.f13687h.f13690c;
            zzwVar5.p();
            return;
        }
        try {
            continuation = this.f13687h.f13689b;
            Object a2 = continuation.a(this.f13686c);
            zzwVar4 = this.f13687h.f13690c;
            zzwVar4.o(a2);
        } catch (RuntimeExecutionException e2) {
            if (e2.getCause() instanceof Exception) {
                zzwVar3 = this.f13687h.f13690c;
                zzwVar3.n((Exception) e2.getCause());
            } else {
                zzwVar2 = this.f13687h.f13690c;
                zzwVar2.n(e2);
            }
        } catch (Exception e3) {
            zzwVar = this.f13687h.f13690c;
            zzwVar.n(e3);
        }
    }
}
