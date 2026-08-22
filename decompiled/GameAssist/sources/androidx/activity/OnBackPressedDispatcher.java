package androidx.activity;

import android.os.Build;
import android.window.BackEvent;
import android.window.OnBackAnimationCallback;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.DoNotInline;
import androidx.annotation.MainThread;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Consumer;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    /* renamed from: a, reason: collision with root package name */
    private final Runnable f58a;

    /* renamed from: b, reason: collision with root package name */
    private final Consumer f59b;

    /* renamed from: c, reason: collision with root package name */
    private final ArrayDeque f60c;

    /* renamed from: d, reason: collision with root package name */
    private OnBackPressedCallback f61d;

    /* renamed from: e, reason: collision with root package name */
    private OnBackInvokedCallback f62e;

    /* renamed from: f, reason: collision with root package name */
    private OnBackInvokedDispatcher f63f;

    /* renamed from: g, reason: collision with root package name */
    private boolean f64g;

    /* renamed from: h, reason: collision with root package name */
    private boolean f65h;

    @RequiresApi
    @Metadata
    public static final class Api33Impl {

        /* renamed from: a, reason: collision with root package name */
        public static final Api33Impl f66a = new Api33Impl();

        private Api33Impl() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void c(Function0 onBackInvoked) {
            Intrinsics.e(onBackInvoked, "$onBackInvoked");
            onBackInvoked.a();
        }

        @DoNotInline
        @NotNull
        public final OnBackInvokedCallback b(@NotNull final Function0<Unit> onBackInvoked) {
            Intrinsics.e(onBackInvoked, "onBackInvoked");
            return new OnBackInvokedCallback() { // from class: androidx.activity.h
                @Override // android.window.OnBackInvokedCallback
                public final void onBackInvoked() {
                    OnBackPressedDispatcher.Api33Impl.c(Function0.this);
                }
            };
        }

        @DoNotInline
        public final void d(@NotNull Object dispatcher, int i2, @NotNull Object callback) {
            Intrinsics.e(dispatcher, "dispatcher");
            Intrinsics.e(callback, "callback");
            ((OnBackInvokedDispatcher) dispatcher).registerOnBackInvokedCallback(i2, (OnBackInvokedCallback) callback);
        }

        @DoNotInline
        public final void e(@NotNull Object dispatcher, @NotNull Object callback) {
            Intrinsics.e(dispatcher, "dispatcher");
            Intrinsics.e(callback, "callback");
            ((OnBackInvokedDispatcher) dispatcher).unregisterOnBackInvokedCallback((OnBackInvokedCallback) callback);
        }
    }

    @RequiresApi
    @Metadata
    public static final class Api34Impl {

        /* renamed from: a, reason: collision with root package name */
        public static final Api34Impl f67a = new Api34Impl();

        private Api34Impl() {
        }

        @DoNotInline
        @NotNull
        public final OnBackInvokedCallback a(@NotNull final Function1<? super BackEventCompat, Unit> onBackStarted, @NotNull final Function1<? super BackEventCompat, Unit> onBackProgressed, @NotNull final Function0<Unit> onBackInvoked, @NotNull final Function0<Unit> onBackCancelled) {
            Intrinsics.e(onBackStarted, "onBackStarted");
            Intrinsics.e(onBackProgressed, "onBackProgressed");
            Intrinsics.e(onBackInvoked, "onBackInvoked");
            Intrinsics.e(onBackCancelled, "onBackCancelled");
            return new OnBackAnimationCallback() { // from class: androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1
                @Override // android.window.OnBackAnimationCallback
                public void onBackCancelled() {
                    onBackCancelled.a();
                }

                @Override // android.window.OnBackInvokedCallback
                public void onBackInvoked() {
                    onBackInvoked.a();
                }

                @Override // android.window.OnBackAnimationCallback
                public void onBackProgressed(BackEvent backEvent) {
                    Intrinsics.e(backEvent, "backEvent");
                    onBackProgressed.c(new BackEventCompat(backEvent));
                }

                @Override // android.window.OnBackAnimationCallback
                public void onBackStarted(BackEvent backEvent) {
                    Intrinsics.e(backEvent, "backEvent");
                    Function1.this.c(new BackEventCompat(backEvent));
                }
            };
        }
    }

    @Metadata
    private final class LifecycleOnBackPressedCancellable implements LifecycleEventObserver, Cancellable {

        /* renamed from: c, reason: collision with root package name */
        private final Lifecycle f72c;

        /* renamed from: h, reason: collision with root package name */
        private final OnBackPressedCallback f73h;

        /* renamed from: i, reason: collision with root package name */
        private Cancellable f74i;

        /* renamed from: j, reason: collision with root package name */
        final /* synthetic */ OnBackPressedDispatcher f75j;

        public LifecycleOnBackPressedCancellable(OnBackPressedDispatcher onBackPressedDispatcher, Lifecycle lifecycle, OnBackPressedCallback onBackPressedCallback) {
            Intrinsics.e(lifecycle, "lifecycle");
            Intrinsics.e(onBackPressedCallback, "onBackPressedCallback");
            this.f75j = onBackPressedDispatcher;
            this.f72c = lifecycle;
            this.f73h = onBackPressedCallback;
            lifecycle.a(this);
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void c(LifecycleOwner source, Lifecycle.Event event) {
            Intrinsics.e(source, "source");
            Intrinsics.e(event, "event");
            if (event == Lifecycle.Event.ON_START) {
                this.f74i = this.f75j.i(this.f73h);
                return;
            }
            if (event != Lifecycle.Event.ON_STOP) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    cancel();
                }
            } else {
                Cancellable cancellable = this.f74i;
                if (cancellable != null) {
                    cancellable.cancel();
                }
            }
        }

        @Override // androidx.activity.Cancellable
        public void cancel() {
            this.f72c.c(this);
            this.f73h.i(this);
            Cancellable cancellable = this.f74i;
            if (cancellable != null) {
                cancellable.cancel();
            }
            this.f74i = null;
        }
    }

    @Metadata
    private final class OnBackPressedCancellable implements Cancellable {

        /* renamed from: c, reason: collision with root package name */
        private final OnBackPressedCallback f76c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ OnBackPressedDispatcher f77h;

        public OnBackPressedCancellable(OnBackPressedDispatcher onBackPressedDispatcher, OnBackPressedCallback onBackPressedCallback) {
            Intrinsics.e(onBackPressedCallback, "onBackPressedCallback");
            this.f77h = onBackPressedDispatcher;
            this.f76c = onBackPressedCallback;
        }

        @Override // androidx.activity.Cancellable
        public void cancel() {
            this.f77h.f60c.remove(this.f76c);
            if (Intrinsics.a(this.f77h.f61d, this.f76c)) {
                this.f76c.c();
                this.f77h.f61d = null;
            }
            this.f76c.i(this);
            Function0 b2 = this.f76c.b();
            if (b2 != null) {
                b2.a();
            }
            this.f76c.k(null);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable, Consumer consumer) {
        this.f58a = runnable;
        this.f59b = consumer;
        this.f60c = new ArrayDeque();
        this.f62e = Build.VERSION.SDK_INT >= 34 ? Api34Impl.f67a.a(new Function1<BackEventCompat, Unit>() { // from class: androidx.activity.OnBackPressedDispatcher.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object c(Object obj) {
                d((BackEventCompat) obj);
                return Unit.f18288a;
            }

            public final void d(BackEventCompat backEvent) {
                Intrinsics.e(backEvent, "backEvent");
                OnBackPressedDispatcher.this.m(backEvent);
            }
        }, new Function1<BackEventCompat, Unit>() { // from class: androidx.activity.OnBackPressedDispatcher.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object c(Object obj) {
                d((BackEventCompat) obj);
                return Unit.f18288a;
            }

            public final void d(BackEventCompat backEvent) {
                Intrinsics.e(backEvent, "backEvent");
                OnBackPressedDispatcher.this.l(backEvent);
            }
        }, new Function0<Unit>() { // from class: androidx.activity.OnBackPressedDispatcher.3
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object a() {
                d();
                return Unit.f18288a;
            }

            public final void d() {
                OnBackPressedDispatcher.this.k();
            }
        }, new Function0<Unit>() { // from class: androidx.activity.OnBackPressedDispatcher.4
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object a() {
                d();
                return Unit.f18288a;
            }

            public final void d() {
                OnBackPressedDispatcher.this.j();
            }
        }) : Api33Impl.f66a.b(new Function0<Unit>() { // from class: androidx.activity.OnBackPressedDispatcher.5
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object a() {
                d();
                return Unit.f18288a;
            }

            public final void d() {
                OnBackPressedDispatcher.this.k();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void j() {
        Object obj;
        ArrayDeque arrayDeque = this.f60c;
        ListIterator<E> listIterator = arrayDeque.listIterator(arrayDeque.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                obj = null;
                break;
            } else {
                obj = listIterator.previous();
                if (((OnBackPressedCallback) obj).g()) {
                    break;
                }
            }
        }
        OnBackPressedCallback onBackPressedCallback = (OnBackPressedCallback) obj;
        this.f61d = null;
        if (onBackPressedCallback != null) {
            onBackPressedCallback.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void l(BackEventCompat backEventCompat) {
        Object obj;
        ArrayDeque arrayDeque = this.f60c;
        ListIterator<E> listIterator = arrayDeque.listIterator(arrayDeque.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                obj = null;
                break;
            } else {
                obj = listIterator.previous();
                if (((OnBackPressedCallback) obj).g()) {
                    break;
                }
            }
        }
        OnBackPressedCallback onBackPressedCallback = (OnBackPressedCallback) obj;
        if (onBackPressedCallback != null) {
            onBackPressedCallback.e(backEventCompat);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void m(BackEventCompat backEventCompat) {
        Object obj;
        ArrayDeque arrayDeque = this.f60c;
        ListIterator<E> listIterator = arrayDeque.listIterator(arrayDeque.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                obj = null;
                break;
            } else {
                obj = listIterator.previous();
                if (((OnBackPressedCallback) obj).g()) {
                    break;
                }
            }
        }
        OnBackPressedCallback onBackPressedCallback = (OnBackPressedCallback) obj;
        this.f61d = onBackPressedCallback;
        if (onBackPressedCallback != null) {
            onBackPressedCallback.f(backEventCompat);
        }
    }

    private final void o(boolean z) {
        OnBackInvokedDispatcher onBackInvokedDispatcher = this.f63f;
        OnBackInvokedCallback onBackInvokedCallback = this.f62e;
        if (onBackInvokedDispatcher == null || onBackInvokedCallback == null) {
            return;
        }
        if (z && !this.f64g) {
            Api33Impl.f66a.d(onBackInvokedDispatcher, 0, onBackInvokedCallback);
            this.f64g = true;
        } else {
            if (z || !this.f64g) {
                return;
            }
            Api33Impl.f66a.e(onBackInvokedDispatcher, onBackInvokedCallback);
            this.f64g = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void p() {
        boolean z = this.f65h;
        ArrayDeque arrayDeque = this.f60c;
        boolean z2 = false;
        if (!(arrayDeque instanceof Collection) || !arrayDeque.isEmpty()) {
            Iterator<E> it = arrayDeque.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (((OnBackPressedCallback) it.next()).g()) {
                    z2 = true;
                    break;
                }
            }
        }
        this.f65h = z2;
        if (z2 != z) {
            Consumer consumer = this.f59b;
            if (consumer != null) {
                consumer.accept(Boolean.valueOf(z2));
            }
            o(z2);
        }
    }

    @MainThread
    @VisibleForTesting
    public final void dispatchOnBackCancelled() {
        j();
    }

    @MainThread
    @VisibleForTesting
    public final void dispatchOnBackProgressed(@NotNull BackEventCompat backEvent) {
        Intrinsics.e(backEvent, "backEvent");
        l(backEvent);
    }

    @MainThread
    @VisibleForTesting
    public final void dispatchOnBackStarted(@NotNull BackEventCompat backEvent) {
        Intrinsics.e(backEvent, "backEvent");
        m(backEvent);
    }

    public final void h(LifecycleOwner owner, OnBackPressedCallback onBackPressedCallback) {
        Intrinsics.e(owner, "owner");
        Intrinsics.e(onBackPressedCallback, "onBackPressedCallback");
        Lifecycle a2 = owner.a();
        if (a2.b() == Lifecycle.State.DESTROYED) {
            return;
        }
        onBackPressedCallback.a(new LifecycleOnBackPressedCancellable(this, a2, onBackPressedCallback));
        p();
        onBackPressedCallback.k(new OnBackPressedDispatcher$addCallback$1(this));
    }

    public final Cancellable i(OnBackPressedCallback onBackPressedCallback) {
        Intrinsics.e(onBackPressedCallback, "onBackPressedCallback");
        this.f60c.add(onBackPressedCallback);
        OnBackPressedCancellable onBackPressedCancellable = new OnBackPressedCancellable(this, onBackPressedCallback);
        onBackPressedCallback.a(onBackPressedCancellable);
        p();
        onBackPressedCallback.k(new OnBackPressedDispatcher$addCancellableCallback$1(this));
        return onBackPressedCancellable;
    }

    public final void k() {
        Object obj;
        ArrayDeque arrayDeque = this.f60c;
        ListIterator<E> listIterator = arrayDeque.listIterator(arrayDeque.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                obj = null;
                break;
            } else {
                obj = listIterator.previous();
                if (((OnBackPressedCallback) obj).g()) {
                    break;
                }
            }
        }
        OnBackPressedCallback onBackPressedCallback = (OnBackPressedCallback) obj;
        this.f61d = null;
        if (onBackPressedCallback != null) {
            onBackPressedCallback.d();
            return;
        }
        Runnable runnable = this.f58a;
        if (runnable != null) {
            runnable.run();
        }
    }

    public final void n(OnBackInvokedDispatcher invoker) {
        Intrinsics.e(invoker, "invoker");
        this.f63f = invoker;
        o(this.f65h);
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this(runnable, null);
    }
}
