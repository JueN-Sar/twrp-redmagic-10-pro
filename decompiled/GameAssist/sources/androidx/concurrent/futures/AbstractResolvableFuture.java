package androidx.concurrent.futures;

import androidx.annotation.RestrictTo;
import com.google.common.util.concurrent.ListenableFuture;
import com.zte.distbus.basetransfer.DistBusKeys;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestrictTo
/* loaded from: classes.dex */
public abstract class AbstractResolvableFuture<V> implements ListenableFuture<V> {

    /* renamed from: j, reason: collision with root package name */
    static final boolean f1417j = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));

    /* renamed from: k, reason: collision with root package name */
    private static final Logger f1418k = Logger.getLogger(AbstractResolvableFuture.class.getName());

    /* renamed from: l, reason: collision with root package name */
    static final AtomicHelper f1419l;

    /* renamed from: m, reason: collision with root package name */
    private static final Object f1420m;

    /* renamed from: c, reason: collision with root package name */
    volatile Object f1421c;

    /* renamed from: h, reason: collision with root package name */
    volatile Listener f1422h;

    /* renamed from: i, reason: collision with root package name */
    volatile Waiter f1423i;

    private static abstract class AtomicHelper {
        private AtomicHelper() {
        }

        abstract boolean a(AbstractResolvableFuture abstractResolvableFuture, Listener listener, Listener listener2);

        abstract boolean b(AbstractResolvableFuture abstractResolvableFuture, Object obj, Object obj2);

        abstract boolean c(AbstractResolvableFuture abstractResolvableFuture, Waiter waiter, Waiter waiter2);

        abstract void d(Waiter waiter, Waiter waiter2);

        abstract void e(Waiter waiter, Thread thread);
    }

    private static final class Cancellation {

        /* renamed from: c, reason: collision with root package name */
        static final Cancellation f1424c;

        /* renamed from: d, reason: collision with root package name */
        static final Cancellation f1425d;

        /* renamed from: a, reason: collision with root package name */
        final boolean f1426a;

        /* renamed from: b, reason: collision with root package name */
        final Throwable f1427b;

        static {
            if (AbstractResolvableFuture.f1417j) {
                f1425d = null;
                f1424c = null;
            } else {
                f1425d = new Cancellation(false, null);
                f1424c = new Cancellation(true, null);
            }
        }

        Cancellation(boolean z, Throwable th) {
            this.f1426a = z;
            this.f1427b = th;
        }
    }

    private static final class Failure {

        /* renamed from: b, reason: collision with root package name */
        static final Failure f1428b = new Failure(new Throwable("Failure occurred while trying to finish a future.") { // from class: androidx.concurrent.futures.AbstractResolvableFuture.Failure.1
            @Override // java.lang.Throwable
            public synchronized Throwable fillInStackTrace() {
                return this;
            }
        });

        /* renamed from: a, reason: collision with root package name */
        final Throwable f1429a;

        Failure(Throwable th) {
            this.f1429a = (Throwable) AbstractResolvableFuture.d(th);
        }
    }

    private static final class Listener {

        /* renamed from: d, reason: collision with root package name */
        static final Listener f1430d = new Listener(null, null);

        /* renamed from: a, reason: collision with root package name */
        final Runnable f1431a;

        /* renamed from: b, reason: collision with root package name */
        final Executor f1432b;

        /* renamed from: c, reason: collision with root package name */
        Listener f1433c;

        Listener(Runnable runnable, Executor executor) {
            this.f1431a = runnable;
            this.f1432b = executor;
        }
    }

    private static final class SafeAtomicHelper extends AtomicHelper {

        /* renamed from: a, reason: collision with root package name */
        final AtomicReferenceFieldUpdater f1434a;

        /* renamed from: b, reason: collision with root package name */
        final AtomicReferenceFieldUpdater f1435b;

        /* renamed from: c, reason: collision with root package name */
        final AtomicReferenceFieldUpdater f1436c;

        /* renamed from: d, reason: collision with root package name */
        final AtomicReferenceFieldUpdater f1437d;

        /* renamed from: e, reason: collision with root package name */
        final AtomicReferenceFieldUpdater f1438e;

        SafeAtomicHelper(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
            super();
            this.f1434a = atomicReferenceFieldUpdater;
            this.f1435b = atomicReferenceFieldUpdater2;
            this.f1436c = atomicReferenceFieldUpdater3;
            this.f1437d = atomicReferenceFieldUpdater4;
            this.f1438e = atomicReferenceFieldUpdater5;
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        boolean a(AbstractResolvableFuture abstractResolvableFuture, Listener listener, Listener listener2) {
            return this.f1437d.compareAndSet(abstractResolvableFuture, listener, listener2);
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        boolean b(AbstractResolvableFuture abstractResolvableFuture, Object obj, Object obj2) {
            return this.f1438e.compareAndSet(abstractResolvableFuture, obj, obj2);
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        boolean c(AbstractResolvableFuture abstractResolvableFuture, Waiter waiter, Waiter waiter2) {
            return this.f1436c.compareAndSet(abstractResolvableFuture, waiter, waiter2);
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        void d(Waiter waiter, Waiter waiter2) {
            this.f1435b.lazySet(waiter, waiter2);
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        void e(Waiter waiter, Thread thread) {
            this.f1434a.lazySet(waiter, thread);
        }
    }

    private static final class SetFuture<V> implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final AbstractResolvableFuture f1439c;

        /* renamed from: h, reason: collision with root package name */
        final ListenableFuture f1440h;

        @Override // java.lang.Runnable
        public void run() {
            if (this.f1439c.f1421c != this) {
                return;
            }
            if (AbstractResolvableFuture.f1419l.b(this.f1439c, this, AbstractResolvableFuture.i(this.f1440h))) {
                AbstractResolvableFuture.f(this.f1439c);
            }
        }
    }

    private static final class SynchronizedHelper extends AtomicHelper {
        SynchronizedHelper() {
            super();
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        boolean a(AbstractResolvableFuture abstractResolvableFuture, Listener listener, Listener listener2) {
            synchronized (abstractResolvableFuture) {
                try {
                    if (abstractResolvableFuture.f1422h != listener) {
                        return false;
                    }
                    abstractResolvableFuture.f1422h = listener2;
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        boolean b(AbstractResolvableFuture abstractResolvableFuture, Object obj, Object obj2) {
            synchronized (abstractResolvableFuture) {
                try {
                    if (abstractResolvableFuture.f1421c != obj) {
                        return false;
                    }
                    abstractResolvableFuture.f1421c = obj2;
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        boolean c(AbstractResolvableFuture abstractResolvableFuture, Waiter waiter, Waiter waiter2) {
            synchronized (abstractResolvableFuture) {
                try {
                    if (abstractResolvableFuture.f1423i != waiter) {
                        return false;
                    }
                    abstractResolvableFuture.f1423i = waiter2;
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        void d(Waiter waiter, Waiter waiter2) {
            waiter.f1443b = waiter2;
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        void e(Waiter waiter, Thread thread) {
            waiter.f1442a = thread;
        }
    }

    private static final class Waiter {

        /* renamed from: c, reason: collision with root package name */
        static final Waiter f1441c = new Waiter(false);

        /* renamed from: a, reason: collision with root package name */
        volatile Thread f1442a;

        /* renamed from: b, reason: collision with root package name */
        volatile Waiter f1443b;

        Waiter(boolean z) {
        }

        void a(Waiter waiter) {
            AbstractResolvableFuture.f1419l.d(this, waiter);
        }

        void b() {
            Thread thread = this.f1442a;
            if (thread != null) {
                this.f1442a = null;
                LockSupport.unpark(thread);
            }
        }

        Waiter() {
            AbstractResolvableFuture.f1419l.e(this, Thread.currentThread());
        }
    }

    static {
        AtomicHelper synchronizedHelper;
        try {
            synchronizedHelper = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Thread.class, DistBusKeys.KEY_WIFI_ENABLE), AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Waiter.class, DistBusKeys.KEY_WIFI_5G_ENABLE), AtomicReferenceFieldUpdater.newUpdater(AbstractResolvableFuture.class, Waiter.class, "i"), AtomicReferenceFieldUpdater.newUpdater(AbstractResolvableFuture.class, Listener.class, "h"), AtomicReferenceFieldUpdater.newUpdater(AbstractResolvableFuture.class, Object.class, DistBusKeys.KEY_WIFI_FREQUENCY));
            th = null;
        } catch (Throwable th) {
            th = th;
            synchronizedHelper = new SynchronizedHelper();
        }
        f1419l = synchronizedHelper;
        if (th != null) {
            f1418k.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
        f1420m = new Object();
    }

    protected AbstractResolvableFuture() {
    }

    private void a(StringBuilder sb) {
        try {
            Object j2 = j(this);
            sb.append("SUCCESS, result=[");
            sb.append(q(j2));
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (RuntimeException e2) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e2.getClass());
            sb.append(" thrown from get()]");
        } catch (ExecutionException e3) {
            sb.append("FAILURE, cause=[");
            sb.append(e3.getCause());
            sb.append("]");
        }
    }

    private static CancellationException c(String str, Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }

    static Object d(Object obj) {
        if (obj != null) {
            return obj;
        }
        throw null;
    }

    private Listener e(Listener listener) {
        Listener listener2;
        do {
            listener2 = this.f1422h;
        } while (!f1419l.a(this, listener2, Listener.f1430d));
        while (true) {
            Listener listener3 = listener;
            listener = listener2;
            if (listener == null) {
                return listener3;
            }
            listener2 = listener.f1433c;
            listener.f1433c = listener3;
        }
    }

    static void f(AbstractResolvableFuture abstractResolvableFuture) {
        Listener listener = null;
        while (true) {
            abstractResolvableFuture.m();
            abstractResolvableFuture.b();
            Listener e2 = abstractResolvableFuture.e(listener);
            while (e2 != null) {
                listener = e2.f1433c;
                Runnable runnable = e2.f1431a;
                if (runnable instanceof SetFuture) {
                    SetFuture setFuture = (SetFuture) runnable;
                    abstractResolvableFuture = setFuture.f1439c;
                    if (abstractResolvableFuture.f1421c == setFuture) {
                        if (f1419l.b(abstractResolvableFuture, setFuture, i(setFuture.f1440h))) {
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    g(runnable, e2.f1432b);
                }
                e2 = listener;
            }
            return;
        }
    }

    private static void g(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e2) {
            f1418k.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e2);
        }
    }

    private Object h(Object obj) {
        if (obj instanceof Cancellation) {
            throw c("Task was cancelled.", ((Cancellation) obj).f1427b);
        }
        if (obj instanceof Failure) {
            throw new ExecutionException(((Failure) obj).f1429a);
        }
        if (obj == f1420m) {
            return null;
        }
        return obj;
    }

    static Object i(ListenableFuture listenableFuture) {
        if (listenableFuture instanceof AbstractResolvableFuture) {
            Object obj = ((AbstractResolvableFuture) listenableFuture).f1421c;
            if (!(obj instanceof Cancellation)) {
                return obj;
            }
            Cancellation cancellation = (Cancellation) obj;
            return cancellation.f1426a ? cancellation.f1427b != null ? new Cancellation(false, cancellation.f1427b) : Cancellation.f1425d : obj;
        }
        boolean isCancelled = listenableFuture.isCancelled();
        if ((!f1417j) && isCancelled) {
            return Cancellation.f1425d;
        }
        try {
            Object j2 = j(listenableFuture);
            return j2 == null ? f1420m : j2;
        } catch (CancellationException e2) {
            if (isCancelled) {
                return new Cancellation(false, e2);
            }
            return new Failure(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: " + listenableFuture, e2));
        } catch (ExecutionException e3) {
            return new Failure(e3.getCause());
        } catch (Throwable th) {
            return new Failure(th);
        }
    }

    static Object j(Future future) {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    private void m() {
        Waiter waiter;
        do {
            waiter = this.f1423i;
        } while (!f1419l.c(this, waiter, Waiter.f1441c));
        while (waiter != null) {
            waiter.b();
            waiter = waiter.f1443b;
        }
    }

    private void n(Waiter waiter) {
        waiter.f1442a = null;
        while (true) {
            Waiter waiter2 = this.f1423i;
            if (waiter2 == Waiter.f1441c) {
                return;
            }
            Waiter waiter3 = null;
            while (waiter2 != null) {
                Waiter waiter4 = waiter2.f1443b;
                if (waiter2.f1442a != null) {
                    waiter3 = waiter2;
                } else if (waiter3 != null) {
                    waiter3.f1443b = waiter4;
                    if (waiter3.f1442a == null) {
                        break;
                    }
                } else if (!f1419l.c(this, waiter2, waiter4)) {
                    break;
                }
                waiter2 = waiter4;
            }
            return;
        }
    }

    private String q(Object obj) {
        return obj == this ? "this future" : String.valueOf(obj);
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable runnable, Executor executor) {
        d(runnable);
        d(executor);
        Listener listener = this.f1422h;
        if (listener != Listener.f1430d) {
            Listener listener2 = new Listener(runnable, executor);
            do {
                listener2.f1433c = listener;
                if (f1419l.a(this, listener, listener2)) {
                    return;
                } else {
                    listener = this.f1422h;
                }
            } while (listener != Listener.f1430d);
        }
        g(runnable, executor);
    }

    protected void b() {
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        Object obj = this.f1421c;
        if (!(obj == null) && !(obj instanceof SetFuture)) {
            return false;
        }
        Cancellation cancellation = f1417j ? new Cancellation(z, new CancellationException("Future.cancel() was called.")) : z ? Cancellation.f1424c : Cancellation.f1425d;
        boolean z2 = false;
        while (true) {
            if (f1419l.b(this, obj, cancellation)) {
                if (z) {
                    this.k();
                }
                f(this);
                if (!(obj instanceof SetFuture)) {
                    return true;
                }
                ListenableFuture listenableFuture = ((SetFuture) obj).f1440h;
                if (!(listenableFuture instanceof AbstractResolvableFuture)) {
                    listenableFuture.cancel(z);
                    return true;
                }
                this = (AbstractResolvableFuture) listenableFuture;
                obj = this.f1421c;
                if (!(obj == null) && !(obj instanceof SetFuture)) {
                    return true;
                }
                z2 = true;
            } else {
                obj = this.f1421c;
                if (!(obj instanceof SetFuture)) {
                    return z2;
                }
            }
        }
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j2, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(j2);
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj = this.f1421c;
        if ((obj != null) && (!(obj instanceof SetFuture))) {
            return h(obj);
        }
        long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
        if (nanos >= 1000) {
            Waiter waiter = this.f1423i;
            if (waiter != Waiter.f1441c) {
                Waiter waiter2 = new Waiter();
                do {
                    waiter2.a(waiter);
                    if (f1419l.c(this, waiter, waiter2)) {
                        do {
                            LockSupport.parkNanos(this, nanos);
                            if (Thread.interrupted()) {
                                n(waiter2);
                                throw new InterruptedException();
                            }
                            Object obj2 = this.f1421c;
                            if ((obj2 != null) && (!(obj2 instanceof SetFuture))) {
                                return h(obj2);
                            }
                            nanos = nanoTime - System.nanoTime();
                        } while (nanos >= 1000);
                        n(waiter2);
                    } else {
                        waiter = this.f1423i;
                    }
                } while (waiter != Waiter.f1441c);
            }
            return h(this.f1421c);
        }
        while (nanos > 0) {
            Object obj3 = this.f1421c;
            if ((obj3 != null) && (!(obj3 instanceof SetFuture))) {
                return h(obj3);
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            nanos = nanoTime - System.nanoTime();
        }
        String abstractResolvableFuture = toString();
        String timeUnit2 = timeUnit.toString();
        Locale locale = Locale.ROOT;
        String lowerCase = timeUnit2.toLowerCase(locale);
        String str = "Waited " + j2 + " " + timeUnit.toString().toLowerCase(locale);
        if (nanos + 1000 < 0) {
            String str2 = str + " (plus ";
            long j3 = -nanos;
            long convert = timeUnit.convert(j3, TimeUnit.NANOSECONDS);
            long nanos2 = j3 - timeUnit.toNanos(convert);
            boolean z = convert == 0 || nanos2 > 1000;
            if (convert > 0) {
                String str3 = str2 + convert + " " + lowerCase;
                if (z) {
                    str3 = str3 + ",";
                }
                str2 = str3 + " ";
            }
            if (z) {
                str2 = str2 + nanos2 + " nanoseconds ";
            }
            str = str2 + "delay)";
        }
        if (isDone()) {
            throw new TimeoutException(str + " but future completed as timeout expired");
        }
        throw new TimeoutException(str + " for " + abstractResolvableFuture);
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.f1421c instanceof Cancellation;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return (!(r2 instanceof SetFuture)) & (this.f1421c != null);
    }

    protected void k() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected String l() {
        Object obj = this.f1421c;
        if (obj instanceof SetFuture) {
            return "setFuture=[" + q(((SetFuture) obj).f1440h) + "]";
        }
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
    }

    protected boolean o(Object obj) {
        if (obj == null) {
            obj = f1420m;
        }
        if (!f1419l.b(this, null, obj)) {
            return false;
        }
        f(this);
        return true;
    }

    protected boolean p(Throwable th) {
        if (!f1419l.b(this, null, new Failure((Throwable) d(th)))) {
            return false;
        }
        f(this);
        return true;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (isCancelled()) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            a(sb);
        } else {
            try {
                str = l();
            } catch (RuntimeException e2) {
                str = "Exception thrown from implementation: " + e2.getClass();
            }
            if (str != null && !str.isEmpty()) {
                sb.append("PENDING, info=[");
                sb.append(str);
                sb.append("]");
            } else if (isDone()) {
                a(sb);
            } else {
                sb.append("PENDING");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.f1421c;
            if ((obj2 != null) & (!(obj2 instanceof SetFuture))) {
                return h(obj2);
            }
            Waiter waiter = this.f1423i;
            if (waiter != Waiter.f1441c) {
                Waiter waiter2 = new Waiter();
                do {
                    waiter2.a(waiter);
                    if (f1419l.c(this, waiter, waiter2)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.f1421c;
                            } else {
                                n(waiter2);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof SetFuture))));
                        return h(obj);
                    }
                    waiter = this.f1423i;
                } while (waiter != Waiter.f1441c);
            }
            return h(this.f1421c);
        }
        throw new InterruptedException();
    }
}
