package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import java.util.concurrent.Executor;

@KeepForSdk
/* loaded from: classes.dex */
public final class ListenerHolder<L> {

    /* renamed from: a, reason: collision with root package name */
    private final Executor f10604a;

    /* renamed from: b, reason: collision with root package name */
    private volatile Object f10605b;

    /* renamed from: c, reason: collision with root package name */
    private volatile ListenerKey f10606c;

    @KeepForSdk
    public static final class ListenerKey<L> {

        /* renamed from: a, reason: collision with root package name */
        private final Object f10607a;

        /* renamed from: b, reason: collision with root package name */
        private final String f10608b;

        ListenerKey(Object obj, String str) {
            this.f10607a = obj;
            this.f10608b = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ListenerKey)) {
                return false;
            }
            ListenerKey listenerKey = (ListenerKey) obj;
            return this.f10607a == listenerKey.f10607a && this.f10608b.equals(listenerKey.f10608b);
        }

        public int hashCode() {
            return (System.identityHashCode(this.f10607a) * 31) + this.f10608b.hashCode();
        }
    }

    @KeepForSdk
    public interface Notifier<L> {
        void a(Object obj);

        void b();
    }

    ListenerHolder(Looper looper, Object obj, String str) {
        this.f10604a = new HandlerExecutor(looper);
        this.f10605b = Preconditions.j(obj, "Listener must not be null");
        this.f10606c = new ListenerKey(obj, Preconditions.f(str));
    }

    public void a() {
        this.f10605b = null;
        this.f10606c = null;
    }

    public ListenerKey b() {
        return this.f10606c;
    }

    public void c(final Notifier notifier) {
        Preconditions.j(notifier, "Notifier must not be null");
        this.f10604a.execute(new Runnable() { // from class: com.google.android.gms.common.api.internal.zacb
            @Override // java.lang.Runnable
            public final void run() {
                ListenerHolder.this.d(notifier);
            }
        });
    }

    final void d(Notifier notifier) {
        Object obj = this.f10605b;
        if (obj == null) {
            notifier.b();
            return;
        }
        try {
            notifier.a(obj);
        } catch (RuntimeException e2) {
            notifier.b();
            throw e2;
        }
    }

    ListenerHolder(Executor executor, Object obj, String str) {
        this.f10604a = (Executor) Preconditions.j(executor, "Executor must not be null");
        this.f10605b = Preconditions.j(obj, "Listener must not be null");
        this.f10606c = new ListenerKey(obj, Preconditions.f(str));
    }
}
