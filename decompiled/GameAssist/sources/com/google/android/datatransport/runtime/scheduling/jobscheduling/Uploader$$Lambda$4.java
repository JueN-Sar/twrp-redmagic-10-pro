package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

/* loaded from: classes.dex */
final /* synthetic */ class Uploader$$Lambda$4 implements SynchronizationGuard.CriticalSection {

    /* renamed from: a, reason: collision with root package name */
    private final EventStore f10344a;

    private Uploader$$Lambda$4(EventStore eventStore) {
        this.f10344a = eventStore;
    }

    public static SynchronizationGuard.CriticalSection b(EventStore eventStore) {
        return new Uploader$$Lambda$4(eventStore);
    }

    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
    public Object a() {
        return Integer.valueOf(this.f10344a.f());
    }
}
