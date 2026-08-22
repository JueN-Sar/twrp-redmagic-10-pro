package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;

/* loaded from: classes.dex */
public final class EventStoreModule_StoreConfigFactory implements Factory<EventStoreConfig> {

    private static final class InstanceHolder {

        /* renamed from: a, reason: collision with root package name */
        private static final EventStoreModule_StoreConfigFactory f10381a = new EventStoreModule_StoreConfigFactory();
    }

    public static EventStoreModule_StoreConfigFactory a() {
        return InstanceHolder.f10381a;
    }

    public static EventStoreConfig c() {
        return (EventStoreConfig) Preconditions.c(EventStoreModule.c(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public EventStoreConfig get() {
        return c();
    }
}
