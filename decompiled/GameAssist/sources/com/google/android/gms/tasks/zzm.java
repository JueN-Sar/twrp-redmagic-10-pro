package com.google.android.gms.tasks;

/* loaded from: classes.dex */
final class zzm implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Task f13710c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ zzn f13711h;

    zzm(zzn zznVar, Task task) {
        this.f13711h = zznVar;
        this.f13710c = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        OnSuccessListener onSuccessListener;
        OnSuccessListener onSuccessListener2;
        obj = this.f13711h.f13713b;
        synchronized (obj) {
            try {
                zzn zznVar = this.f13711h;
                onSuccessListener = zznVar.f13714c;
                if (onSuccessListener != null) {
                    onSuccessListener2 = zznVar.f13714c;
                    onSuccessListener2.a(this.f13710c.i());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
