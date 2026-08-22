package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ProviderOfLazy<T> implements Provider<Lazy<T>> {

    /* renamed from: a, reason: collision with root package name */
    private final Provider f10275a;

    @Override // javax.inject.Provider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Lazy get() {
        return DoubleCheck.a(this.f10275a);
    }
}
