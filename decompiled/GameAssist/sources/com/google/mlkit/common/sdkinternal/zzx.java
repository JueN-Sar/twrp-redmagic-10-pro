package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.common.internal.Preconditions;
import java.io.Closeable;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
final class zzx implements Closeable {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ TaskQueue f16049c;

    /* synthetic */ zzx(TaskQueue taskQueue, zzw zzwVar) {
        AtomicReference atomicReference;
        this.f16049c = taskQueue;
        atomicReference = taskQueue.f15975d;
        Preconditions.l(((Thread) atomicReference.getAndSet(Thread.currentThread())) == null);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        AtomicReference atomicReference;
        atomicReference = this.f16049c.f15975d;
        atomicReference.set(null);
        this.f16049c.d();
    }
}
