package com.google.android.datatransport.runtime.dagger.internal;

import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DelegateFactory<T> implements Factory<T> {

    /* renamed from: a, reason: collision with root package name */
    private Provider f10268a;

    @Override // javax.inject.Provider
    public Object get() {
        Provider provider = this.f10268a;
        if (provider != null) {
            return provider.get();
        }
        throw new IllegalStateException();
    }
}
