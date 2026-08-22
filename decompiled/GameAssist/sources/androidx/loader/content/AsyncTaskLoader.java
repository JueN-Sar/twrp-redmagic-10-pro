package androidx.loader.content;

import android.os.Handler;
import android.os.SystemClock;
import androidx.core.os.OperationCanceledException;
import androidx.core.util.TimeUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public abstract class AsyncTaskLoader<D> extends Loader<D> {

    /* renamed from: j, reason: collision with root package name */
    private final Executor f4441j;

    /* renamed from: k, reason: collision with root package name */
    volatile LoadTask f4442k;

    /* renamed from: l, reason: collision with root package name */
    volatile LoadTask f4443l;

    /* renamed from: m, reason: collision with root package name */
    long f4444m;

    /* renamed from: n, reason: collision with root package name */
    long f4445n;

    /* renamed from: o, reason: collision with root package name */
    Handler f4446o;

    final class LoadTask extends ModernAsyncTask<Void, Void, D> implements Runnable {

        /* renamed from: q, reason: collision with root package name */
        private final CountDownLatch f4447q = new CountDownLatch(1);

        /* renamed from: r, reason: collision with root package name */
        boolean f4448r;

        LoadTask() {
        }

        @Override // androidx.loader.content.ModernAsyncTask
        protected void h(Object obj) {
            try {
                AsyncTaskLoader.this.A(this, obj);
            } finally {
                this.f4447q.countDown();
            }
        }

        @Override // androidx.loader.content.ModernAsyncTask
        protected void i(Object obj) {
            try {
                AsyncTaskLoader.this.B(this, obj);
            } finally {
                this.f4447q.countDown();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.loader.content.ModernAsyncTask
        /* renamed from: n, reason: merged with bridge method [inline-methods] */
        public Object b(Void... voidArr) {
            try {
                return AsyncTaskLoader.this.G();
            } catch (OperationCanceledException e2) {
                if (this.f()) {
                    return null;
                }
                throw e2;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f4448r = false;
            AsyncTaskLoader.this.C();
        }
    }

    void A(LoadTask loadTask, Object obj) {
        F(obj);
        if (this.f4443l == loadTask) {
            u();
            this.f4445n = SystemClock.uptimeMillis();
            this.f4443l = null;
            e();
            C();
        }
    }

    void B(LoadTask loadTask, Object obj) {
        if (this.f4442k != loadTask) {
            A(loadTask, obj);
            return;
        }
        if (j()) {
            F(obj);
            return;
        }
        c();
        this.f4445n = SystemClock.uptimeMillis();
        this.f4442k = null;
        f(obj);
    }

    void C() {
        if (this.f4443l != null || this.f4442k == null) {
            return;
        }
        if (this.f4442k.f4448r) {
            this.f4442k.f4448r = false;
            this.f4446o.removeCallbacks(this.f4442k);
        }
        if (this.f4444m <= 0 || SystemClock.uptimeMillis() >= this.f4445n + this.f4444m) {
            this.f4442k.c(this.f4441j, null);
        } else {
            this.f4442k.f4448r = true;
            this.f4446o.postAtTime(this.f4442k, this.f4445n + this.f4444m);
        }
    }

    public boolean D() {
        return this.f4443l != null;
    }

    public abstract Object E();

    public void F(Object obj) {
    }

    protected Object G() {
        return E();
    }

    @Override // androidx.loader.content.Loader
    public void g(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.g(str, fileDescriptor, printWriter, strArr);
        if (this.f4442k != null) {
            printWriter.print(str);
            printWriter.print("mTask=");
            printWriter.print(this.f4442k);
            printWriter.print(" waiting=");
            printWriter.println(this.f4442k.f4448r);
        }
        if (this.f4443l != null) {
            printWriter.print(str);
            printWriter.print("mCancellingTask=");
            printWriter.print(this.f4443l);
            printWriter.print(" waiting=");
            printWriter.println(this.f4443l.f4448r);
        }
        if (this.f4444m != 0) {
            printWriter.print(str);
            printWriter.print("mUpdateThrottle=");
            TimeUtils.c(this.f4444m, printWriter);
            printWriter.print(" mLastLoadCompleteTime=");
            TimeUtils.b(this.f4445n, SystemClock.uptimeMillis(), printWriter);
            printWriter.println();
        }
    }

    @Override // androidx.loader.content.Loader
    protected boolean n() {
        if (this.f4442k == null) {
            return false;
        }
        if (!this.f4458e) {
            this.f4461h = true;
        }
        if (this.f4443l != null) {
            if (this.f4442k.f4448r) {
                this.f4442k.f4448r = false;
                this.f4446o.removeCallbacks(this.f4442k);
            }
            this.f4442k = null;
            return false;
        }
        if (this.f4442k.f4448r) {
            this.f4442k.f4448r = false;
            this.f4446o.removeCallbacks(this.f4442k);
            this.f4442k = null;
            return false;
        }
        boolean a2 = this.f4442k.a(false);
        if (a2) {
            this.f4443l = this.f4442k;
            z();
        }
        this.f4442k = null;
        return a2;
    }

    @Override // androidx.loader.content.Loader
    protected void p() {
        super.p();
        b();
        this.f4442k = new LoadTask();
        C();
    }

    public void z() {
    }
}
