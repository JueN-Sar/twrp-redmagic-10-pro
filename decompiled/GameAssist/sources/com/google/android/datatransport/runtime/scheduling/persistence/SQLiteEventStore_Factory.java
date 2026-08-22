package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SQLiteEventStore_Factory implements Factory<SQLiteEventStore> {

    /* renamed from: a, reason: collision with root package name */
    private final Provider f10415a;

    /* renamed from: b, reason: collision with root package name */
    private final Provider f10416b;

    /* renamed from: c, reason: collision with root package name */
    private final Provider f10417c;

    /* renamed from: d, reason: collision with root package name */
    private final Provider f10418d;

    public SQLiteEventStore_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        this.f10415a = provider;
        this.f10416b = provider2;
        this.f10417c = provider3;
        this.f10418d = provider4;
    }

    public static SQLiteEventStore_Factory a(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        return new SQLiteEventStore_Factory(provider, provider2, provider3, provider4);
    }

    public static SQLiteEventStore c(Clock clock, Clock clock2, Object obj, Object obj2) {
        return new SQLiteEventStore(clock, clock2, (EventStoreConfig) obj, (SchemaManager) obj2);
    }

    @Override // javax.inject.Provider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public SQLiteEventStore get() {
        return c((Clock) this.f10415a.get(), (Clock) this.f10416b.get(), this.f10417c.get(), this.f10418d.get());
    }
}
