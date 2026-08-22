package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class WorkInitializer_Factory implements Factory<WorkInitializer> {

    /* renamed from: a, reason: collision with root package name */
    private final Provider f10361a;

    /* renamed from: b, reason: collision with root package name */
    private final Provider f10362b;

    /* renamed from: c, reason: collision with root package name */
    private final Provider f10363c;

    /* renamed from: d, reason: collision with root package name */
    private final Provider f10364d;

    public WorkInitializer_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        this.f10361a = provider;
        this.f10362b = provider2;
        this.f10363c = provider3;
        this.f10364d = provider4;
    }

    public static WorkInitializer_Factory a(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        return new WorkInitializer_Factory(provider, provider2, provider3, provider4);
    }

    public static WorkInitializer c(Executor executor, EventStore eventStore, WorkScheduler workScheduler, SynchronizationGuard synchronizationGuard) {
        return new WorkInitializer(executor, eventStore, workScheduler, synchronizationGuard);
    }

    @Override // javax.inject.Provider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public WorkInitializer get() {
        return c((Executor) this.f10361a.get(), (EventStore) this.f10362b.get(), (WorkScheduler) this.f10363c.get(), (SynchronizationGuard) this.f10364d.get());
    }
}
