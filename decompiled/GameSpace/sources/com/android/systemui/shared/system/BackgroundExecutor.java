package com.android.systemui.shared.system;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* loaded from: classes2.dex */
public class BackgroundExecutor {
    private static final BackgroundExecutor sInstance = new BackgroundExecutor();
    private final ExecutorService mExecutorService = Executors.newFixedThreadPool(2);

    public static BackgroundExecutor get() {
        return sInstance;
    }

    public Future<?> submit(Runnable runnable) {
        return this.mExecutorService.submit(runnable);
    }

    public <T> Future<T> submit(Runnable runnable, T t) {
        return this.mExecutorService.submit(runnable, t);
    }

    public <T> Future<T> submit(Callable<T> callable) {
        return this.mExecutorService.submit(callable);
    }
}
