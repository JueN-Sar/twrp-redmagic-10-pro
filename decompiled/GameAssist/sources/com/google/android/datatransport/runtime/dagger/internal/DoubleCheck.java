package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DoubleCheck<T> implements Provider<T>, Lazy<T> {

    /* renamed from: c, reason: collision with root package name */
    private static final Object f10269c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private volatile Provider f10270a;

    /* renamed from: b, reason: collision with root package name */
    private volatile Object f10271b = f10269c;

    private DoubleCheck(Provider provider) {
        this.f10270a = provider;
    }

    public static Lazy a(Provider provider) {
        return provider instanceof Lazy ? (Lazy) provider : new DoubleCheck((Provider) Preconditions.b(provider));
    }

    public static Provider b(Provider provider) {
        Preconditions.b(provider);
        return provider instanceof DoubleCheck ? provider : new DoubleCheck(provider);
    }

    public static Object c(Object obj, Object obj2) {
        if (obj == f10269c || (obj instanceof MemoizedSentinel) || obj == obj2) {
            return obj2;
        }
        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj + " & " + obj2 + ". This is likely due to a circular dependency.");
    }

    @Override // javax.inject.Provider
    public Object get() {
        Object obj = this.f10271b;
        Object obj2 = f10269c;
        if (obj == obj2) {
            synchronized (this) {
                try {
                    obj = this.f10271b;
                    if (obj == obj2) {
                        obj = this.f10270a.get();
                        this.f10271b = c(this.f10271b, obj);
                        this.f10270a = null;
                    }
                } finally {
                }
            }
        }
        return obj;
    }
}
