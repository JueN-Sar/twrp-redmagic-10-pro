package com.google.android.gms.internal.mlkit_common;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public abstract class zzav extends zzx implements ExecutorService {
    protected zzav() {
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzx
    protected /* bridge */ /* synthetic */ Object a() {
        throw null;
    }

    @Override // java.util.concurrent.ExecutorService
    public final boolean awaitTermination(long j2, TimeUnit timeUnit) {
        return c().awaitTermination(j2, timeUnit);
    }

    protected abstract ExecutorService c();

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        c().execute(runnable);
    }

    @Override // java.util.concurrent.ExecutorService
    public final List invokeAll(Collection collection) {
        return c().invokeAll(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public final Object invokeAny(Collection collection) {
        return c().invokeAny(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public final boolean isShutdown() {
        return c().isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public final boolean isTerminated() {
        return c().isTerminated();
    }

    @Override // java.util.concurrent.ExecutorService
    public final void shutdown() {
        c().shutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public final List shutdownNow() {
        return c().shutdownNow();
    }

    @Override // java.util.concurrent.ExecutorService
    public final Future submit(Runnable runnable) {
        return c().submit(runnable);
    }

    @Override // java.util.concurrent.ExecutorService
    public final List invokeAll(Collection collection, long j2, TimeUnit timeUnit) {
        return c().invokeAll(collection, j2, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public final Object invokeAny(Collection collection, long j2, TimeUnit timeUnit) {
        return c().invokeAny(collection, j2, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public final Future submit(Runnable runnable, Object obj) {
        return c().submit(runnable, obj);
    }

    @Override // java.util.concurrent.ExecutorService
    public final Future submit(Callable callable) {
        return c().submit(callable);
    }
}
