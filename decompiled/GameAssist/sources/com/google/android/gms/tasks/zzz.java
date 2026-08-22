package com.google.android.gms.tasks;

import java.util.concurrent.Callable;

/* loaded from: classes.dex */
final class zzz implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ zzw f13736c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Callable f13737h;

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.f13736c.o(this.f13737h.call());
        } catch (Exception e2) {
            this.f13736c.n(e2);
        } catch (Throwable th) {
            this.f13736c.n(new RuntimeException(th));
        }
    }
}
