package androidx.lifecycle;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.Lifecycle;

/* loaded from: classes.dex */
public abstract class LiveData<T> {

    /* renamed from: k, reason: collision with root package name */
    static final Object f4315k = new Object();

    /* renamed from: a, reason: collision with root package name */
    final Object f4316a = new Object();

    /* renamed from: b, reason: collision with root package name */
    private SafeIterableMap f4317b = new SafeIterableMap();

    /* renamed from: c, reason: collision with root package name */
    int f4318c = 0;

    /* renamed from: d, reason: collision with root package name */
    private boolean f4319d;

    /* renamed from: e, reason: collision with root package name */
    private volatile Object f4320e;

    /* renamed from: f, reason: collision with root package name */
    volatile Object f4321f;

    /* renamed from: g, reason: collision with root package name */
    private int f4322g;

    /* renamed from: h, reason: collision with root package name */
    private boolean f4323h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f4324i;

    /* renamed from: j, reason: collision with root package name */
    private final Runnable f4325j;

    private class AlwaysActiveObserver extends LiveData<T>.ObserverWrapper {
        AlwaysActiveObserver(Observer observer) {
            super(observer);
        }

        @Override // androidx.lifecycle.LiveData.ObserverWrapper
        boolean g() {
            return true;
        }
    }

    class LifecycleBoundObserver extends LiveData<T>.ObserverWrapper implements LifecycleEventObserver {

        /* renamed from: k, reason: collision with root package name */
        final LifecycleOwner f4328k;

        LifecycleBoundObserver(LifecycleOwner lifecycleOwner, Observer observer) {
            super(observer);
            this.f4328k = lifecycleOwner;
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void c(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            Lifecycle.State b2 = this.f4328k.a().b();
            if (b2 == Lifecycle.State.DESTROYED) {
                LiveData.this.n(this.f4330c);
                return;
            }
            Lifecycle.State state = null;
            while (state != b2) {
                b(g());
                state = b2;
                b2 = this.f4328k.a().b();
            }
        }

        @Override // androidx.lifecycle.LiveData.ObserverWrapper
        void e() {
            this.f4328k.a().c(this);
        }

        @Override // androidx.lifecycle.LiveData.ObserverWrapper
        boolean f(LifecycleOwner lifecycleOwner) {
            return this.f4328k == lifecycleOwner;
        }

        @Override // androidx.lifecycle.LiveData.ObserverWrapper
        boolean g() {
            return this.f4328k.a().b().d(Lifecycle.State.STARTED);
        }
    }

    private abstract class ObserverWrapper {

        /* renamed from: c, reason: collision with root package name */
        final Observer f4330c;

        /* renamed from: h, reason: collision with root package name */
        boolean f4331h;

        /* renamed from: i, reason: collision with root package name */
        int f4332i = -1;

        ObserverWrapper(Observer observer) {
            this.f4330c = observer;
        }

        void b(boolean z) {
            if (z == this.f4331h) {
                return;
            }
            this.f4331h = z;
            LiveData.this.c(z ? 1 : -1);
            if (this.f4331h) {
                LiveData.this.e(this);
            }
        }

        void e() {
        }

        boolean f(LifecycleOwner lifecycleOwner) {
            return false;
        }

        abstract boolean g();
    }

    public LiveData() {
        Object obj = f4315k;
        this.f4321f = obj;
        this.f4325j = new Runnable() { // from class: androidx.lifecycle.LiveData.1
            @Override // java.lang.Runnable
            public void run() {
                Object obj2;
                synchronized (LiveData.this.f4316a) {
                    obj2 = LiveData.this.f4321f;
                    LiveData.this.f4321f = LiveData.f4315k;
                }
                LiveData.this.o(obj2);
            }
        };
        this.f4320e = obj;
        this.f4322g = -1;
    }

    static void b(String str) {
        if (ArchTaskExecutor.f().b()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    private void d(ObserverWrapper observerWrapper) {
        if (observerWrapper.f4331h) {
            if (!observerWrapper.g()) {
                observerWrapper.b(false);
                return;
            }
            int i2 = observerWrapper.f4332i;
            int i3 = this.f4322g;
            if (i2 >= i3) {
                return;
            }
            observerWrapper.f4332i = i3;
            observerWrapper.f4330c.a(this.f4320e);
        }
    }

    void c(int i2) {
        int i3 = this.f4318c;
        this.f4318c = i2 + i3;
        if (this.f4319d) {
            return;
        }
        this.f4319d = true;
        while (true) {
            try {
                int i4 = this.f4318c;
                if (i3 == i4) {
                    this.f4319d = false;
                    return;
                }
                boolean z = i3 == 0 && i4 > 0;
                boolean z2 = i3 > 0 && i4 == 0;
                if (z) {
                    k();
                } else if (z2) {
                    l();
                }
                i3 = i4;
            } catch (Throwable th) {
                this.f4319d = false;
                throw th;
            }
        }
    }

    void e(ObserverWrapper observerWrapper) {
        if (this.f4323h) {
            this.f4324i = true;
            return;
        }
        this.f4323h = true;
        do {
            this.f4324i = false;
            if (observerWrapper != null) {
                d(observerWrapper);
                observerWrapper = null;
            } else {
                SafeIterableMap.IteratorWithAdditions f2 = this.f4317b.f();
                while (f2.hasNext()) {
                    d((ObserverWrapper) f2.next().getValue());
                    if (this.f4324i) {
                        break;
                    }
                }
            }
        } while (this.f4324i);
        this.f4323h = false;
    }

    public Object f() {
        Object obj = this.f4320e;
        if (obj != f4315k) {
            return obj;
        }
        return null;
    }

    int g() {
        return this.f4322g;
    }

    public boolean h() {
        return this.f4318c > 0;
    }

    public void i(LifecycleOwner lifecycleOwner, Observer observer) {
        b("observe");
        if (lifecycleOwner.a().b() == Lifecycle.State.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(lifecycleOwner, observer);
        ObserverWrapper observerWrapper = (ObserverWrapper) this.f4317b.i(observer, lifecycleBoundObserver);
        if (observerWrapper != null && !observerWrapper.f(lifecycleOwner)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (observerWrapper != null) {
            return;
        }
        lifecycleOwner.a().a(lifecycleBoundObserver);
    }

    public void j(Observer observer) {
        b("observeForever");
        AlwaysActiveObserver alwaysActiveObserver = new AlwaysActiveObserver(observer);
        ObserverWrapper observerWrapper = (ObserverWrapper) this.f4317b.i(observer, alwaysActiveObserver);
        if (observerWrapper instanceof LifecycleBoundObserver) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (observerWrapper != null) {
            return;
        }
        alwaysActiveObserver.b(true);
    }

    protected void k() {
    }

    protected void l() {
    }

    protected void m(Object obj) {
        boolean z;
        synchronized (this.f4316a) {
            z = this.f4321f == f4315k;
            this.f4321f = obj;
        }
        if (z) {
            ArchTaskExecutor.f().c(this.f4325j);
        }
    }

    public void n(Observer observer) {
        b("removeObserver");
        ObserverWrapper observerWrapper = (ObserverWrapper) this.f4317b.j(observer);
        if (observerWrapper == null) {
            return;
        }
        observerWrapper.e();
        observerWrapper.b(false);
    }

    protected void o(Object obj) {
        b("setValue");
        this.f4322g++;
        this.f4320e = obj;
        e(null);
    }
}
