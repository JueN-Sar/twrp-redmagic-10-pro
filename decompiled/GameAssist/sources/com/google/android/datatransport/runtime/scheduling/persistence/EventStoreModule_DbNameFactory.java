package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;

/* loaded from: classes.dex */
public final class EventStoreModule_DbNameFactory implements Factory<String> {

    private static final class InstanceHolder {

        /* renamed from: a, reason: collision with root package name */
        private static final EventStoreModule_DbNameFactory f10379a = new EventStoreModule_DbNameFactory();
    }

    public static EventStoreModule_DbNameFactory a() {
        return InstanceHolder.f10379a;
    }

    public static String b() {
        return (String) Preconditions.c(EventStoreModule.a(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public String get() {
        return b();
    }
}
