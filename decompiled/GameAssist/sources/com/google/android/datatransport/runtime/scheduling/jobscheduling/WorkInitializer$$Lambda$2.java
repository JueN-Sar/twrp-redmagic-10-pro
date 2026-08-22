package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

/* loaded from: classes.dex */
final /* synthetic */ class WorkInitializer$$Lambda$2 implements SynchronizationGuard.CriticalSection {

    /* renamed from: a, reason: collision with root package name */
    private final WorkInitializer f10360a;

    private WorkInitializer$$Lambda$2(WorkInitializer workInitializer) {
        this.f10360a = workInitializer;
    }

    public static SynchronizationGuard.CriticalSection b(WorkInitializer workInitializer) {
        return new WorkInitializer$$Lambda$2(workInitializer);
    }

    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
    public Object a() {
        return WorkInitializer.b(this.f10360a);
    }
}
