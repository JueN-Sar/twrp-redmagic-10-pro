package androidx.core.provider;

import android.os.Handler;
import android.os.Process;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
class RequestExecutor {

    private static class DefaultThreadFactory implements ThreadFactory {

        /* renamed from: a, reason: collision with root package name */
        private String f3167a;

        /* renamed from: b, reason: collision with root package name */
        private int f3168b;

        private static class ProcessPriorityThread extends Thread {

            /* renamed from: c, reason: collision with root package name */
            private final int f3169c;

            ProcessPriorityThread(Runnable runnable, String str, int i2) {
                super(runnable, str);
                this.f3169c = i2;
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Process.setThreadPriority(this.f3169c);
                super.run();
            }
        }

        DefaultThreadFactory(String str, int i2) {
            this.f3167a = str;
            this.f3168b = i2;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new ProcessPriorityThread(runnable, this.f3167a, this.f3168b);
        }
    }

    private static class HandlerExecutor implements Executor {

        /* renamed from: c, reason: collision with root package name */
        private final Handler f3170c;

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            if (this.f3170c.post((Runnable) Preconditions.h(runnable))) {
                return;
            }
            throw new RejectedExecutionException(this.f3170c + " is shutting down");
        }
    }

    private static class ReplyRunnable<T> implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        private Callable f3171c;

        /* renamed from: h, reason: collision with root package name */
        private Consumer f3172h;

        /* renamed from: i, reason: collision with root package name */
        private Handler f3173i;

        ReplyRunnable(Handler handler, Callable callable, Consumer consumer) {
            this.f3171c = callable;
            this.f3172h = consumer;
            this.f3173i = handler;
        }

        @Override // java.lang.Runnable
        public void run() {
            final Object obj;
            try {
                obj = this.f3171c.call();
            } catch (Exception unused) {
                obj = null;
            }
            final Consumer consumer = this.f3172h;
            this.f3173i.post(new Runnable() { // from class: androidx.core.provider.RequestExecutor.ReplyRunnable.1
                @Override // java.lang.Runnable
                public void run() {
                    consumer.accept(obj);
                }
            });
        }
    }

    static ThreadPoolExecutor a(String str, int i2, int i3) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, i3, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), new DefaultThreadFactory(str, i2));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }

    static void b(Executor executor, Callable callable, Consumer consumer) {
        executor.execute(new ReplyRunnable(CalleeHandler.a(), callable, consumer));
    }

    static Object c(ExecutorService executorService, Callable callable, int i2) {
        try {
            return executorService.submit(callable).get(i2, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e2) {
            throw e2;
        } catch (ExecutionException e3) {
            throw new RuntimeException(e3);
        } catch (TimeoutException unused) {
            throw new InterruptedException("timeout");
        }
    }
}
