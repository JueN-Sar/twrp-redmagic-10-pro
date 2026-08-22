package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SchemaManager_Factory implements Factory<SchemaManager> {

    /* renamed from: a, reason: collision with root package name */
    private final Provider f10431a;

    /* renamed from: b, reason: collision with root package name */
    private final Provider f10432b;

    /* renamed from: c, reason: collision with root package name */
    private final Provider f10433c;

    public SchemaManager_Factory(Provider provider, Provider provider2, Provider provider3) {
        this.f10431a = provider;
        this.f10432b = provider2;
        this.f10433c = provider3;
    }

    public static SchemaManager_Factory a(Provider provider, Provider provider2, Provider provider3) {
        return new SchemaManager_Factory(provider, provider2, provider3);
    }

    public static SchemaManager c(Context context, String str, int i2) {
        return new SchemaManager(context, str, i2);
    }

    @Override // javax.inject.Provider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public SchemaManager get() {
        return c((Context) this.f10431a.get(), (String) this.f10432b.get(), ((Integer) this.f10433c.get()).intValue());
    }
}
