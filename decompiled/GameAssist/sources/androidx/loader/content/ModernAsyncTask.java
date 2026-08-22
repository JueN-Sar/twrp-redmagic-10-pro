package androidx.loader.content;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
abstract class ModernAsyncTask<Params, Progress, Result> {

    /* renamed from: l, reason: collision with root package name */
    private static final ThreadFactory f4464l;

    /* renamed from: m, reason: collision with root package name */
    private static final BlockingQueue f4465m;

    /* renamed from: n, reason: collision with root package name */
    public static final Executor f4466n;

    /* renamed from: o, reason: collision with root package name */
    private static InternalHandler f4467o;

    /* renamed from: p, reason: collision with root package name */
    private static volatile Executor f4468p;

    /* renamed from: c, reason: collision with root package name */
    private final WorkerRunnable f4469c;

    /* renamed from: h, reason: collision with root package name */
    private final FutureTask f4470h;

    /* renamed from: i, reason: collision with root package name */
    private volatile Status f4471i = Status.PENDING;

    /* renamed from: j, reason: collision with root package name */
    final AtomicBoolean f4472j = new AtomicBoolean();

    /* renamed from: k, reason: collision with root package name */
    final AtomicBoolean f4473k = new AtomicBoolean();

    /* renamed from: androidx.loader.content.ModernAsyncTask$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f4477a;

        static {
            int[] iArr = new int[Status.values().length];
            f4477a = iArr;
            try {
                iArr[Status.RUNNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4477a[Status.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static class AsyncTaskResult<Data> {

        /* renamed from: a, reason: collision with root package name */
        final ModernAsyncTask f4478a;

        /* renamed from: b, reason: collision with root package name */
        final Object[] f4479b;

        AsyncTaskResult(ModernAsyncTask modernAsyncTask, Object... objArr) {
            this.f4478a = modernAsyncTask;
            this.f4479b = objArr;
        }
    }

    private static class InternalHandler extends Handler {
        InternalHandler() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AsyncTaskResult asyncTaskResult = (AsyncTaskResult) message.obj;
            int i2 = message.what;
            if (i2 == 1) {
                asyncTaskResult.f4478a.d(asyncTaskResult.f4479b[0]);
            } else {
                if (i2 != 2) {
                    return;
                }
                asyncTaskResult.f4478a.k(asyncTaskResult.f4479b);
            }
        }
    }

    public enum Status {
        PENDING,
        RUNNING,
        FINISHED
    }

    private static abstract class WorkerRunnable<Params, Result> implements Callable<Result> {

        /* renamed from: a, reason: collision with root package name */
        Object[] f4480a;

        WorkerRunnable() {
        }
    }

    static {
        ThreadFactory threadFactory = new ThreadFactory() { // from class: androidx.loader.content.ModernAsyncTask.1

            /* renamed from: a, reason: collision with root package name */
            private final AtomicInteger f4474a = new AtomicInteger(1);

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "ModernAsyncTask #" + this.f4474a.getAndIncrement());
            }
        };
        f4464l = threadFactory;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(10);
        f4465m = linkedBlockingQueue;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 128, 1L, TimeUnit.SECONDS, linkedBlockingQueue, threadFactory);
        f4466n = threadPoolExecutor;
        f4468p = threadPoolExecutor;
    }

    ModernAsyncTask() {
        WorkerRunnable<Params, Result> workerRunnable = new WorkerRunnable<Params, Result>() { // from class: androidx.loader.content.ModernAsyncTask.2
            @Override // java.util.concurrent.Callable
            public Object call() {
                ModernAsyncTask.this.f4473k.set(true);
                Object obj = null;
                try {
                    Process.setThreadPriority(10);
                    obj = ModernAsyncTask.this.b(this.f4480a);
                    Binder.flushPendingCommands();
                    return obj;
                } finally {
                }
            }
        };
        this.f4469c = workerRunnable;
        this.f4470h = new FutureTask<Result>(workerRunnable) { // from class: androidx.loader.content.ModernAsyncTask.3
            @Override // java.util.concurrent.FutureTask
            protected void done() {
                try {
                    ModernAsyncTask.this.m(get());
                } catch (InterruptedException e2) {
                    Log.w("AsyncTask", e2);
                } catch (CancellationException unused) {
                    ModernAsyncTask.this.m(null);
                } catch (ExecutionException e3) {
                    throw new RuntimeException("An error occurred while executing doInBackground()", e3.getCause());
                } catch (Throwable th) {
                    throw new RuntimeException("An error occurred while executing doInBackground()", th);
                }
            }
        };
    }

    private static Handler e() {
        InternalHandler internalHandler;
        synchronized (ModernAsyncTask.class) {
            try {
                if (f4467o == null) {
                    f4467o = new InternalHandler();
                }
                internalHandler = f4467o;
            } catch (Throwable th) {
                throw th;
            }
        }
        return internalHandler;
    }

    public final boolean a(boolean z) {
        this.f4472j.set(true);
        return this.f4470h.cancel(z);
    }

    protected abstract Object b(Object... objArr);

    public final ModernAsyncTask c(Executor executor, Object... objArr) {
        if (this.f4471i == Status.PENDING) {
            this.f4471i = Status.RUNNING;
            j();
            this.f4469c.f4480a = objArr;
            executor.execute(this.f4470h);
            return this;
        }
        int i2 = AnonymousClass4.f4477a[this.f4471i.ordinal()];
        if (i2 == 1) {
            throw new IllegalStateException("Cannot execute task: the task is already running.");
        }
        if (i2 != 2) {
            throw new IllegalStateException("We should never reach this state");
        }
        throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
    }

    void d(Object obj) {
        if (f()) {
            h(obj);
        } else {
            i(obj);
        }
        this.f4471i = Status.FINISHED;
    }

    public final boolean f() {
        return this.f4472j.get();
    }

    protected void g() {
    }

    protected void h(Object obj) {
        g();
    }

    protected void i(Object obj) {
    }

    protected void j() {
    }

    protected void k(Object... objArr) {
    }

    Object l(Object obj) {
        e().obtainMessage(1, new AsyncTaskResult(this, obj)).sendToTarget();
        return obj;
    }

    void m(Object obj) {
        if (this.f4473k.get()) {
            return;
        }
        l(obj);
    }
}
