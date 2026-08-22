package com.google.mlkit.common.sdkinternal;

import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzrr;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.mlkit.common.MlKitException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@KeepForSdk
/* loaded from: classes.dex */
public abstract class ModelResource {

    /* renamed from: a, reason: collision with root package name */
    protected final TaskQueue f15948a;

    /* renamed from: b, reason: collision with root package name */
    private final AtomicInteger f15949b = new AtomicInteger(0);

    /* renamed from: c, reason: collision with root package name */
    private final AtomicBoolean f15950c = new AtomicBoolean(false);

    protected ModelResource(TaskQueue taskQueue) {
        this.f15948a = taskQueue;
    }

    public Task a(final Executor executor, final Callable callable, final CancellationToken cancellationToken) {
        Preconditions.l(this.f15949b.get() > 0);
        if (cancellationToken.a()) {
            return Tasks.a();
        }
        final CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationTokenSource.b());
        this.f15948a.a(new Executor() { // from class: com.google.mlkit.common.sdkinternal.zzm
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                try {
                    executor.execute(runnable);
                } catch (RuntimeException e2) {
                    if (cancellationToken.a()) {
                        cancellationTokenSource.a();
                    } else {
                        taskCompletionSource.b(e2);
                    }
                    throw e2;
                }
            }
        }, new Runnable() { // from class: com.google.mlkit.common.sdkinternal.zzn
            @Override // java.lang.Runnable
            public final void run() {
                ModelResource.this.f(cancellationToken, cancellationTokenSource, callable, taskCompletionSource);
            }
        });
        return taskCompletionSource.a();
    }

    public void b() {
        this.f15949b.incrementAndGet();
    }

    protected abstract void c();

    public void d(Executor executor) {
        e(executor);
    }

    public Task e(Executor executor) {
        Preconditions.l(this.f15949b.get() > 0);
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.f15948a.a(executor, new Runnable() { // from class: com.google.mlkit.common.sdkinternal.zzl
            @Override // java.lang.Runnable
            public final void run() {
                ModelResource.this.g(taskCompletionSource);
            }
        });
        return taskCompletionSource.a();
    }

    final /* synthetic */ void f(CancellationToken cancellationToken, CancellationTokenSource cancellationTokenSource, Callable callable, TaskCompletionSource taskCompletionSource) {
        try {
            if (cancellationToken.a()) {
                cancellationTokenSource.a();
                return;
            }
            try {
                if (!this.f15950c.get()) {
                    load();
                    this.f15950c.set(true);
                }
                if (cancellationToken.a()) {
                    cancellationTokenSource.a();
                    return;
                }
                Object call = callable.call();
                if (cancellationToken.a()) {
                    cancellationTokenSource.a();
                } else {
                    taskCompletionSource.c(call);
                }
            } catch (RuntimeException e2) {
                throw new MlKitException("Internal error has occurred when executing ML Kit tasks", 13, e2);
            }
        } catch (Exception e3) {
            if (cancellationToken.a()) {
                cancellationTokenSource.a();
            } else {
                taskCompletionSource.b(e3);
            }
        }
    }

    final /* synthetic */ void g(TaskCompletionSource taskCompletionSource) {
        int decrementAndGet = this.f15949b.decrementAndGet();
        Preconditions.l(decrementAndGet >= 0);
        if (decrementAndGet == 0) {
            c();
            this.f15950c.set(false);
        }
        zzrr.a();
        taskCompletionSource.c(null);
    }

    @KeepForSdk
    @VisibleForTesting
    @WorkerThread
    public abstract void load();
}
