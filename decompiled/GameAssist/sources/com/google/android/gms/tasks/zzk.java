package com.google.android.gms.tasks;

import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes.dex */
final class zzk implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Task f13705c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ zzl f13706h;

    zzk(zzl zzlVar, Task task) {
        this.f13706h = zzlVar;
        this.f13705c = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        OnFailureListener onFailureListener;
        OnFailureListener onFailureListener2;
        obj = this.f13706h.f13708b;
        synchronized (obj) {
            try {
                zzl zzlVar = this.f13706h;
                onFailureListener = zzlVar.f13709c;
                if (onFailureListener != null) {
                    onFailureListener2 = zzlVar.f13709c;
                    onFailureListener2.d((Exception) Preconditions.i(this.f13705c.h()));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
