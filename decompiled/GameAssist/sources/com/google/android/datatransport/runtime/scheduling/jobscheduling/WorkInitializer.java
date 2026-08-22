package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.Iterator;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class WorkInitializer {

    /* renamed from: a, reason: collision with root package name */
    private final Executor f10355a;

    /* renamed from: b, reason: collision with root package name */
    private final EventStore f10356b;

    /* renamed from: c, reason: collision with root package name */
    private final WorkScheduler f10357c;

    /* renamed from: d, reason: collision with root package name */
    private final SynchronizationGuard f10358d;

    WorkInitializer(Executor executor, EventStore eventStore, WorkScheduler workScheduler, SynchronizationGuard synchronizationGuard) {
        this.f10355a = executor;
        this.f10356b = eventStore;
        this.f10357c = workScheduler;
        this.f10358d = synchronizationGuard;
    }

    static /* synthetic */ Object b(WorkInitializer workInitializer) {
        Iterator it = workInitializer.f10356b.o().iterator();
        while (it.hasNext()) {
            workInitializer.f10357c.a((TransportContext) it.next(), 1);
        }
        return null;
    }

    public void a() {
        this.f10355a.execute(WorkInitializer$$Lambda$1.a(this));
    }
}
