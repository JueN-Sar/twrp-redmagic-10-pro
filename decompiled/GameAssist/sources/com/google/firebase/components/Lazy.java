package com.google.firebase.components;

import androidx.annotation.VisibleForTesting;
import com.google.firebase.inject.Provider;

/* loaded from: classes.dex */
public class Lazy<T> implements Provider<T> {

    /* renamed from: c, reason: collision with root package name */
    private static final Object f15840c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private volatile Object f15841a = f15840c;

    /* renamed from: b, reason: collision with root package name */
    private volatile Provider f15842b;

    public Lazy(Provider provider) {
        this.f15842b = provider;
    }

    @Override // com.google.firebase.inject.Provider
    public Object get() {
        Object obj = this.f15841a;
        Object obj2 = f15840c;
        if (obj == obj2) {
            synchronized (this) {
                try {
                    obj = this.f15841a;
                    if (obj == obj2) {
                        obj = this.f15842b.get();
                        this.f15841a = obj;
                        this.f15842b = null;
                    }
                } finally {
                }
            }
        }
        return obj;
    }

    @VisibleForTesting
    boolean isInitialized() {
        return this.f15841a != f15840c;
    }
}
