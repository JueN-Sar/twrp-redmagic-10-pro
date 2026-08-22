package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class CreationContextFactory_Factory implements Factory<CreationContextFactory> {

    /* renamed from: a, reason: collision with root package name */
    private final Provider f10257a;

    /* renamed from: b, reason: collision with root package name */
    private final Provider f10258b;

    /* renamed from: c, reason: collision with root package name */
    private final Provider f10259c;

    public CreationContextFactory_Factory(Provider provider, Provider provider2, Provider provider3) {
        this.f10257a = provider;
        this.f10258b = provider2;
        this.f10259c = provider3;
    }

    public static CreationContextFactory_Factory a(Provider provider, Provider provider2, Provider provider3) {
        return new CreationContextFactory_Factory(provider, provider2, provider3);
    }

    public static CreationContextFactory c(Context context, Clock clock, Clock clock2) {
        return new CreationContextFactory(context, clock, clock2);
    }

    @Override // javax.inject.Provider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public CreationContextFactory get() {
        return c((Context) this.f10257a.get(), (Clock) this.f10258b.get(), (Clock) this.f10259c.get());
    }
}
