package com.google.android.gms.tasks;

/* loaded from: classes.dex */
final class zzi implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Task f13700c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ zzj f13701h;

    zzi(zzj zzjVar, Task task) {
        this.f13701h = zzjVar;
        this.f13700c = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        OnCompleteListener onCompleteListener;
        OnCompleteListener onCompleteListener2;
        obj = this.f13701h.f13703b;
        synchronized (obj) {
            try {
                zzj zzjVar = this.f13701h;
                onCompleteListener = zzjVar.f13704c;
                if (onCompleteListener != null) {
                    onCompleteListener2 = zzjVar.f13704c;
                    onCompleteListener2.a(this.f13700c);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
