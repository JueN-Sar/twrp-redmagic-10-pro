package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.Module;

@Module
/* loaded from: classes.dex */
public abstract class EventStoreModule {
    static String a() {
        return "com.google.android.datatransport.events";
    }

    static int b() {
        return SchemaManager.f10419i;
    }

    static EventStoreConfig c() {
        return EventStoreConfig.f10378a;
    }
}
