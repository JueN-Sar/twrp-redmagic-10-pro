package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.internal.Factory;

/* loaded from: classes.dex */
public final class EventStoreModule_SchemaVersionFactory implements Factory<Integer> {

    private static final class InstanceHolder {

        /* renamed from: a, reason: collision with root package name */
        private static final EventStoreModule_SchemaVersionFactory f10380a = new EventStoreModule_SchemaVersionFactory();
    }

    public static EventStoreModule_SchemaVersionFactory a() {
        return InstanceHolder.f10380a;
    }

    public static int c() {
        return EventStoreModule.b();
    }

    @Override // javax.inject.Provider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Integer get() {
        return Integer.valueOf(c());
    }
}
