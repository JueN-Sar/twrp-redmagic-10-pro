package com.google.android.gms.tasks;

import java.util.concurrent.CountDownLatch;

/* loaded from: classes.dex */
final class zzad<T> implements zzae<T> {

    /* renamed from: a, reason: collision with root package name */
    private final CountDownLatch f13676a;

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void a(Object obj) {
        this.f13676a.countDown();
    }

    @Override // com.google.android.gms.tasks.OnCanceledListener
    public final void b() {
        this.f13676a.countDown();
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void d(Exception exc) {
        this.f13676a.countDown();
    }
}
