package androidx.lifecycle;

import androidx.annotation.VisibleForTesting;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.FastSafeIterableMap;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.Lifecycle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes.dex */
public class LifecycleRegistry extends Lifecycle {

    /* renamed from: j, reason: collision with root package name */
    public static final Companion f4301j = new Companion(null);

    /* renamed from: b, reason: collision with root package name */
    private final boolean f4302b;

    /* renamed from: c, reason: collision with root package name */
    private FastSafeIterableMap f4303c;

    /* renamed from: d, reason: collision with root package name */
    private Lifecycle.State f4304d;

    /* renamed from: e, reason: collision with root package name */
    private final WeakReference f4305e;

    /* renamed from: f, reason: collision with root package name */
    private int f4306f;

    /* renamed from: g, reason: collision with root package name */
    private boolean f4307g;

    /* renamed from: h, reason: collision with root package name */
    private boolean f4308h;

    /* renamed from: i, reason: collision with root package name */
    private ArrayList f4309i;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public final Lifecycle.State a(Lifecycle.State state1, Lifecycle.State state) {
            Intrinsics.e(state1, "state1");
            return (state == null || state.compareTo(state1) >= 0) ? state1 : state;
        }

        @JvmStatic
        @VisibleForTesting
        @NotNull
        public final LifecycleRegistry createUnsafe(@NotNull LifecycleOwner owner) {
            Intrinsics.e(owner, "owner");
            return new LifecycleRegistry(owner, false, null);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata
    public static final class ObserverWithState {

        /* renamed from: a, reason: collision with root package name */
        private Lifecycle.State f4310a;

        /* renamed from: b, reason: collision with root package name */
        private LifecycleEventObserver f4311b;

        public ObserverWithState(LifecycleObserver lifecycleObserver, Lifecycle.State initialState) {
            Intrinsics.e(initialState, "initialState");
            Intrinsics.b(lifecycleObserver);
            this.f4311b = Lifecycling.f(lifecycleObserver);
            this.f4310a = initialState;
        }

        public final void a(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            Intrinsics.e(event, "event");
            Lifecycle.State d2 = event.d();
            this.f4310a = LifecycleRegistry.f4301j.a(this.f4310a, d2);
            LifecycleEventObserver lifecycleEventObserver = this.f4311b;
            Intrinsics.b(lifecycleOwner);
            lifecycleEventObserver.c(lifecycleOwner, event);
            this.f4310a = d2;
        }

        public final Lifecycle.State b() {
            return this.f4310a;
        }
    }

    public /* synthetic */ LifecycleRegistry(LifecycleOwner lifecycleOwner, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(lifecycleOwner, z);
    }

    @JvmStatic
    @VisibleForTesting
    @NotNull
    public static final LifecycleRegistry createUnsafe(@NotNull LifecycleOwner lifecycleOwner) {
        return f4301j.createUnsafe(lifecycleOwner);
    }

    private final void d(LifecycleOwner lifecycleOwner) {
        Iterator descendingIterator = this.f4303c.descendingIterator();
        Intrinsics.d(descendingIterator, "observerMap.descendingIterator()");
        while (descendingIterator.hasNext() && !this.f4308h) {
            Map.Entry entry = (Map.Entry) descendingIterator.next();
            Intrinsics.d(entry, "next()");
            LifecycleObserver lifecycleObserver = (LifecycleObserver) entry.getKey();
            ObserverWithState observerWithState = (ObserverWithState) entry.getValue();
            while (observerWithState.b().compareTo(this.f4304d) > 0 && !this.f4308h && this.f4303c.contains(lifecycleObserver)) {
                Lifecycle.Event a2 = Lifecycle.Event.Companion.a(observerWithState.b());
                if (a2 == null) {
                    throw new IllegalStateException("no event down from " + observerWithState.b());
                }
                l(a2.d());
                observerWithState.a(lifecycleOwner, a2);
                k();
            }
        }
    }

    private final Lifecycle.State e(LifecycleObserver lifecycleObserver) {
        ObserverWithState observerWithState;
        Map.Entry k2 = this.f4303c.k(lifecycleObserver);
        Lifecycle.State state = null;
        Lifecycle.State b2 = (k2 == null || (observerWithState = (ObserverWithState) k2.getValue()) == null) ? null : observerWithState.b();
        if (!this.f4309i.isEmpty()) {
            state = (Lifecycle.State) this.f4309i.get(r0.size() - 1);
        }
        Companion companion = f4301j;
        return companion.a(companion.a(this.f4304d, b2), state);
    }

    private final void f(String str) {
        if (!this.f4302b || ArchTaskExecutor.f().b()) {
            return;
        }
        throw new IllegalStateException(("Method " + str + " must be called on the main thread").toString());
    }

    private final void g(LifecycleOwner lifecycleOwner) {
        SafeIterableMap.IteratorWithAdditions f2 = this.f4303c.f();
        Intrinsics.d(f2, "observerMap.iteratorWithAdditions()");
        while (f2.hasNext() && !this.f4308h) {
            Map.Entry next = f2.next();
            LifecycleObserver lifecycleObserver = (LifecycleObserver) next.getKey();
            ObserverWithState observerWithState = (ObserverWithState) next.getValue();
            while (observerWithState.b().compareTo(this.f4304d) < 0 && !this.f4308h && this.f4303c.contains(lifecycleObserver)) {
                l(observerWithState.b());
                Lifecycle.Event b2 = Lifecycle.Event.Companion.b(observerWithState.b());
                if (b2 == null) {
                    throw new IllegalStateException("no event up from " + observerWithState.b());
                }
                observerWithState.a(lifecycleOwner, b2);
                k();
            }
        }
    }

    private final boolean i() {
        if (this.f4303c.size() == 0) {
            return true;
        }
        Map.Entry b2 = this.f4303c.b();
        Intrinsics.b(b2);
        Lifecycle.State b3 = ((ObserverWithState) b2.getValue()).b();
        Map.Entry g2 = this.f4303c.g();
        Intrinsics.b(g2);
        Lifecycle.State b4 = ((ObserverWithState) g2.getValue()).b();
        return b3 == b4 && this.f4304d == b4;
    }

    private final void j(Lifecycle.State state) {
        Lifecycle.State state2 = this.f4304d;
        if (state2 == state) {
            return;
        }
        if (state2 == Lifecycle.State.INITIALIZED && state == Lifecycle.State.DESTROYED) {
            throw new IllegalStateException(("no event down from " + this.f4304d + " in component " + this.f4305e.get()).toString());
        }
        this.f4304d = state;
        if (this.f4307g || this.f4306f != 0) {
            this.f4308h = true;
            return;
        }
        this.f4307g = true;
        n();
        this.f4307g = false;
        if (this.f4304d == Lifecycle.State.DESTROYED) {
            this.f4303c = new FastSafeIterableMap();
        }
    }

    private final void k() {
        this.f4309i.remove(r1.size() - 1);
    }

    private final void l(Lifecycle.State state) {
        this.f4309i.add(state);
    }

    private final void n() {
        LifecycleOwner lifecycleOwner = (LifecycleOwner) this.f4305e.get();
        if (lifecycleOwner == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is already garbage collected. It is too late to change lifecycle state.");
        }
        while (!i()) {
            this.f4308h = false;
            Lifecycle.State state = this.f4304d;
            Map.Entry b2 = this.f4303c.b();
            Intrinsics.b(b2);
            if (state.compareTo(((ObserverWithState) b2.getValue()).b()) < 0) {
                d(lifecycleOwner);
            }
            Map.Entry g2 = this.f4303c.g();
            if (!this.f4308h && g2 != null && this.f4304d.compareTo(((ObserverWithState) g2.getValue()).b()) > 0) {
                g(lifecycleOwner);
            }
        }
        this.f4308h = false;
    }

    @Override // androidx.lifecycle.Lifecycle
    public void a(LifecycleObserver observer) {
        LifecycleOwner lifecycleOwner;
        Intrinsics.e(observer, "observer");
        f("addObserver");
        Lifecycle.State state = this.f4304d;
        Lifecycle.State state2 = Lifecycle.State.DESTROYED;
        if (state != state2) {
            state2 = Lifecycle.State.INITIALIZED;
        }
        ObserverWithState observerWithState = new ObserverWithState(observer, state2);
        if (((ObserverWithState) this.f4303c.i(observer, observerWithState)) == null && (lifecycleOwner = (LifecycleOwner) this.f4305e.get()) != null) {
            boolean z = this.f4306f != 0 || this.f4307g;
            Lifecycle.State e2 = e(observer);
            this.f4306f++;
            while (observerWithState.b().compareTo(e2) < 0 && this.f4303c.contains(observer)) {
                l(observerWithState.b());
                Lifecycle.Event b2 = Lifecycle.Event.Companion.b(observerWithState.b());
                if (b2 == null) {
                    throw new IllegalStateException("no event up from " + observerWithState.b());
                }
                observerWithState.a(lifecycleOwner, b2);
                k();
                e2 = e(observer);
            }
            if (!z) {
                n();
            }
            this.f4306f--;
        }
    }

    @Override // androidx.lifecycle.Lifecycle
    public Lifecycle.State b() {
        return this.f4304d;
    }

    @Override // androidx.lifecycle.Lifecycle
    public void c(LifecycleObserver observer) {
        Intrinsics.e(observer, "observer");
        f("removeObserver");
        this.f4303c.j(observer);
    }

    public void h(Lifecycle.Event event) {
        Intrinsics.e(event, "event");
        f("handleLifecycleEvent");
        j(event.d());
    }

    public void m(Lifecycle.State state) {
        Intrinsics.e(state, "state");
        f("setCurrentState");
        j(state);
    }

    private LifecycleRegistry(LifecycleOwner lifecycleOwner, boolean z) {
        this.f4302b = z;
        this.f4303c = new FastSafeIterableMap();
        this.f4304d = Lifecycle.State.INITIALIZED;
        this.f4309i = new ArrayList();
        this.f4305e = new WeakReference(lifecycleOwner);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LifecycleRegistry(LifecycleOwner provider) {
        this(provider, true);
        Intrinsics.e(provider, "provider");
    }
}
