package androidx.arch.core.executor;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@RestrictTo
/* loaded from: classes.dex */
public class DefaultTaskExecutor extends TaskExecutor {

    /* renamed from: a, reason: collision with root package name */
    private final Object f1104a = new Object();

    /* renamed from: b, reason: collision with root package name */
    private final ExecutorService f1105b = Executors.newFixedThreadPool(4, new ThreadFactory() { // from class: androidx.arch.core.executor.DefaultTaskExecutor.1

        /* renamed from: a, reason: collision with root package name */
        private final AtomicInteger f1107a = new AtomicInteger(0);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("arch_disk_io_" + this.f1107a.getAndIncrement());
            return thread;
        }
    });

    /* renamed from: c, reason: collision with root package name */
    private volatile Handler f1106c;

    @RequiresApi
    private static class Api28Impl {
        public static Handler a(Looper looper) {
            return Handler.createAsync(looper);
        }
    }

    private static Handler d(Looper looper) {
        return Api28Impl.a(looper);
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public void a(Runnable runnable) {
        this.f1105b.execute(runnable);
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public boolean b() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public void c(Runnable runnable) {
        if (this.f1106c == null) {
            synchronized (this.f1104a) {
                try {
                    if (this.f1106c == null) {
                        this.f1106c = d(Looper.getMainLooper());
                    }
                } finally {
                }
            }
        }
        this.f1106c.post(runnable);
    }
}
