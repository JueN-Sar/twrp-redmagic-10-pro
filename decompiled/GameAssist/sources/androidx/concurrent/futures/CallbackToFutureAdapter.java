package androidx.concurrent.futures;

import com.google.common.util.concurrent.ListenableFuture;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class CallbackToFutureAdapter {

    public static final class Completer<T> {

        /* renamed from: a, reason: collision with root package name */
        Object f1444a;

        /* renamed from: b, reason: collision with root package name */
        SafeFuture f1445b;

        /* renamed from: c, reason: collision with root package name */
        private ResolvableFuture f1446c;

        /* renamed from: d, reason: collision with root package name */
        private boolean f1447d;

        void a() {
            this.f1444a = null;
            this.f1445b = null;
            this.f1446c.o(null);
        }

        protected void finalize() {
            ResolvableFuture resolvableFuture;
            SafeFuture safeFuture = this.f1445b;
            if (safeFuture != null && !safeFuture.isDone()) {
                safeFuture.a(new FutureGarbageCollectedException("The completer object was garbage collected - this future would otherwise never complete. The tag was: " + this.f1444a));
            }
            if (this.f1447d || (resolvableFuture = this.f1446c) == null) {
                return;
            }
            resolvableFuture.o(null);
        }
    }

    static final class FutureGarbageCollectedException extends Throwable {
        FutureGarbageCollectedException(String str) {
            super(str);
        }

        @Override // java.lang.Throwable
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }

    public interface Resolver<T> {
    }

    private static final class SafeFuture<T> implements ListenableFuture<T> {

        /* renamed from: c, reason: collision with root package name */
        final WeakReference f1448c;

        /* renamed from: h, reason: collision with root package name */
        private final AbstractResolvableFuture f1449h;

        /* renamed from: androidx.concurrent.futures.CallbackToFutureAdapter$SafeFuture$1, reason: invalid class name */
        class AnonymousClass1 extends AbstractResolvableFuture<T> {

            /* renamed from: n, reason: collision with root package name */
            final /* synthetic */ SafeFuture f1450n;

            @Override // androidx.concurrent.futures.AbstractResolvableFuture
            protected String l() {
                Completer completer = (Completer) this.f1450n.f1448c.get();
                if (completer == null) {
                    return "Completer object has been garbage collected, future will fail soon";
                }
                return "tag=[" + completer.f1444a + "]";
            }
        }

        boolean a(Throwable th) {
            return this.f1449h.p(th);
        }

        @Override // com.google.common.util.concurrent.ListenableFuture
        public void addListener(Runnable runnable, Executor executor) {
            this.f1449h.addListener(runnable, executor);
        }

        @Override // java.util.concurrent.Future
        public boolean cancel(boolean z) {
            Completer completer = (Completer) this.f1448c.get();
            boolean cancel = this.f1449h.cancel(z);
            if (cancel && completer != null) {
                completer.a();
            }
            return cancel;
        }

        @Override // java.util.concurrent.Future
        public Object get() {
            return this.f1449h.get();
        }

        @Override // java.util.concurrent.Future
        public boolean isCancelled() {
            return this.f1449h.isCancelled();
        }

        @Override // java.util.concurrent.Future
        public boolean isDone() {
            return this.f1449h.isDone();
        }

        public String toString() {
            return this.f1449h.toString();
        }

        @Override // java.util.concurrent.Future
        public Object get(long j2, TimeUnit timeUnit) {
            return this.f1449h.get(j2, timeUnit);
        }
    }
}
