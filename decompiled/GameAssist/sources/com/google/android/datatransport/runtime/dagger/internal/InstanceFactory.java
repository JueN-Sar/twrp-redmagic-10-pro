package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;

/* loaded from: classes.dex */
public final class InstanceFactory<T> implements Factory<T>, Lazy<T> {

    /* renamed from: b, reason: collision with root package name */
    private static final InstanceFactory f10272b = new InstanceFactory(null);

    /* renamed from: a, reason: collision with root package name */
    private final Object f10273a;

    private InstanceFactory(Object obj) {
        this.f10273a = obj;
    }

    public static Factory a(Object obj) {
        return new InstanceFactory(Preconditions.c(obj, "instance cannot be null"));
    }

    @Override // javax.inject.Provider
    public Object get() {
        return this.f10273a;
    }
}
