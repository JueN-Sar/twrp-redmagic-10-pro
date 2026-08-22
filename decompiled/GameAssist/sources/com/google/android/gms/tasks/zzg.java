package com.google.android.gms.tasks;

/* loaded from: classes.dex */
final class zzg implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ zzh f13696c;

    zzg(zzh zzhVar) {
        this.f13696c = zzhVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        OnCanceledListener onCanceledListener;
        OnCanceledListener onCanceledListener2;
        obj = this.f13696c.f13698b;
        synchronized (obj) {
            try {
                zzh zzhVar = this.f13696c;
                onCanceledListener = zzhVar.f13699c;
                if (onCanceledListener != null) {
                    onCanceledListener2 = zzhVar.f13699c;
                    onCanceledListener2.b();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
