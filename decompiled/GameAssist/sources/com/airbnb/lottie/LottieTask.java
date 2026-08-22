package com.airbnb.lottie;

import android.os.Handler;
import android.os.Looper;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.LottieThreadFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/* loaded from: classes.dex */
public class LottieTask<T> {

    /* renamed from: e, reason: collision with root package name */
    public static Executor f9326e = Executors.newCachedThreadPool(new LottieThreadFactory());

    /* renamed from: a, reason: collision with root package name */
    private final Set f9327a;

    /* renamed from: b, reason: collision with root package name */
    private final Set f9328b;

    /* renamed from: c, reason: collision with root package name */
    private final Handler f9329c;

    /* renamed from: d, reason: collision with root package name */
    private volatile LottieResult f9330d;

    private static class LottieFutureTask<T> extends FutureTask<LottieResult<T>> {

        /* renamed from: c, reason: collision with root package name */
        private LottieTask f9331c;

        LottieFutureTask(LottieTask lottieTask, Callable callable) {
            super(callable);
            this.f9331c = lottieTask;
        }

        @Override // java.util.concurrent.FutureTask
        protected void done() {
            try {
                if (isCancelled()) {
                    return;
                }
                try {
                    this.f9331c.l(get());
                } catch (InterruptedException | ExecutionException e2) {
                    this.f9331c.l(new LottieResult(e2));
                }
            } finally {
                this.f9331c = null;
            }
        }
    }

    public LottieTask(Callable callable) {
        this(callable, false);
    }

    private synchronized void f(Throwable th) {
        ArrayList arrayList = new ArrayList(this.f9328b);
        if (arrayList.isEmpty()) {
            Logger.d("Lottie encountered an error but no failure listener was added:", th);
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((LottieListener) it.next()).onResult(th);
        }
    }

    private void g() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            h();
        } else {
            this.f9329c.post(new Runnable() { // from class: com.airbnb.lottie.B
                @Override // java.lang.Runnable
                public final void run() {
                    LottieTask.this.h();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LottieResult lottieResult = this.f9330d;
        if (lottieResult == null) {
            return;
        }
        if (lottieResult.b() != null) {
            i(lottieResult.b());
        } else {
            f(lottieResult.a());
        }
    }

    private synchronized void i(Object obj) {
        Iterator it = new ArrayList(this.f9327a).iterator();
        while (it.hasNext()) {
            ((LottieListener) it.next()).onResult(obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(LottieResult lottieResult) {
        if (this.f9330d != null) {
            throw new IllegalStateException("A task may only be set once.");
        }
        this.f9330d = lottieResult;
        g();
    }

    public synchronized LottieTask c(LottieListener lottieListener) {
        try {
            LottieResult lottieResult = this.f9330d;
            if (lottieResult != null && lottieResult.a() != null) {
                lottieListener.onResult(lottieResult.a());
            }
            this.f9328b.add(lottieListener);
        } catch (Throwable th) {
            throw th;
        }
        return this;
    }

    public synchronized LottieTask d(LottieListener lottieListener) {
        try {
            LottieResult lottieResult = this.f9330d;
            if (lottieResult != null && lottieResult.b() != null) {
                lottieListener.onResult(lottieResult.b());
            }
            this.f9327a.add(lottieListener);
        } catch (Throwable th) {
            throw th;
        }
        return this;
    }

    public LottieResult e() {
        return this.f9330d;
    }

    public synchronized LottieTask j(LottieListener lottieListener) {
        this.f9328b.remove(lottieListener);
        return this;
    }

    public synchronized LottieTask k(LottieListener lottieListener) {
        this.f9327a.remove(lottieListener);
        return this;
    }

    public LottieTask(Object obj) {
        this.f9327a = new LinkedHashSet(1);
        this.f9328b = new LinkedHashSet(1);
        this.f9329c = new Handler(Looper.getMainLooper());
        this.f9330d = null;
        l(new LottieResult(obj));
    }

    LottieTask(Callable callable, boolean z) {
        this.f9327a = new LinkedHashSet(1);
        this.f9328b = new LinkedHashSet(1);
        this.f9329c = new Handler(Looper.getMainLooper());
        this.f9330d = null;
        if (z) {
            try {
                l((LottieResult) callable.call());
                return;
            } catch (Throwable th) {
                l(new LottieResult(th));
                return;
            }
        }
        f9326e.execute(new LottieFutureTask(this, callable));
    }
}
