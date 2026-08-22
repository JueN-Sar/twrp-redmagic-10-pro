package com.google.android.datatransport.runtime.dagger.internal;

import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SingleCheck<T> implements Provider<T> {

    /* renamed from: c, reason: collision with root package name */
    private static final Object f10279c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private volatile Provider f10280a;

    /* renamed from: b, reason: collision with root package name */
    private volatile Object f10281b;

    @Override // javax.inject.Provider
    public Object get() {
        Object obj = this.f10281b;
        if (obj != f10279c) {
            return obj;
        }
        Provider provider = this.f10280a;
        if (provider == null) {
            return this.f10281b;
        }
        Object obj2 = provider.get();
        this.f10281b = obj2;
        this.f10280a = null;
        return obj2;
    }
}
