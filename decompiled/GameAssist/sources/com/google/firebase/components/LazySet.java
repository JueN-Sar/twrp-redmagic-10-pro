package com.google.firebase.components;

import com.google.firebase.inject.Provider;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
class LazySet<T> implements Provider<Set<T>> {

    /* renamed from: b, reason: collision with root package name */
    private volatile Set f15844b = null;

    /* renamed from: a, reason: collision with root package name */
    private volatile Set f15843a = Collections.newSetFromMap(new ConcurrentHashMap());

    LazySet(Collection collection) {
        this.f15843a.addAll(collection);
    }

    static LazySet b(Collection collection) {
        return new LazySet((Set) collection);
    }

    private synchronized void d() {
        try {
            Iterator it = this.f15843a.iterator();
            while (it.hasNext()) {
                this.f15844b.add(((Provider) it.next()).get());
            }
            this.f15843a = null;
        } catch (Throwable th) {
            throw th;
        }
    }

    synchronized void a(Provider provider) {
        try {
            if (this.f15844b == null) {
                this.f15843a.add(provider);
            } else {
                this.f15844b.add(provider.get());
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.google.firebase.inject.Provider
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Set get() {
        if (this.f15844b == null) {
            synchronized (this) {
                try {
                    if (this.f15844b == null) {
                        this.f15844b = Collections.newSetFromMap(new ConcurrentHashMap());
                        d();
                    }
                } finally {
                }
            }
        }
        return Collections.unmodifiableSet(this.f15844b);
    }
}
