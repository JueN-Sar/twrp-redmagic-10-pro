package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MetadataBackendRegistry_Factory implements Factory<MetadataBackendRegistry> {

    /* renamed from: a, reason: collision with root package name */
    private final Provider f10265a;

    /* renamed from: b, reason: collision with root package name */
    private final Provider f10266b;

    public MetadataBackendRegistry_Factory(Provider provider, Provider provider2) {
        this.f10265a = provider;
        this.f10266b = provider2;
    }

    public static MetadataBackendRegistry_Factory a(Provider provider, Provider provider2) {
        return new MetadataBackendRegistry_Factory(provider, provider2);
    }

    public static MetadataBackendRegistry c(Context context, Object obj) {
        return new MetadataBackendRegistry(context, (CreationContextFactory) obj);
    }

    @Override // javax.inject.Provider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public MetadataBackendRegistry get() {
        return c((Context) this.f10265a.get(), this.f10266b.get());
    }
}
