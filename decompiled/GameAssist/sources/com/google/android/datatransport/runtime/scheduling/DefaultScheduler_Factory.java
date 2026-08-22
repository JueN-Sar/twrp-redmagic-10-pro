package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DefaultScheduler_Factory implements Factory<DefaultScheduler> {

    /* renamed from: a, reason: collision with root package name */
    private final Provider f10295a;

    /* renamed from: b, reason: collision with root package name */
    private final Provider f10296b;

    /* renamed from: c, reason: collision with root package name */
    private final Provider f10297c;

    /* renamed from: d, reason: collision with root package name */
    private final Provider f10298d;

    /* renamed from: e, reason: collision with root package name */
    private final Provider f10299e;

    public DefaultScheduler_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5) {
        this.f10295a = provider;
        this.f10296b = provider2;
        this.f10297c = provider3;
        this.f10298d = provider4;
        this.f10299e = provider5;
    }

    public static DefaultScheduler_Factory a(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5) {
        return new DefaultScheduler_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static DefaultScheduler c(Executor executor, BackendRegistry backendRegistry, WorkScheduler workScheduler, EventStore eventStore, SynchronizationGuard synchronizationGuard) {
        return new DefaultScheduler(executor, backendRegistry, workScheduler, eventStore, synchronizationGuard);
    }

    @Override // javax.inject.Provider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public DefaultScheduler get() {
        return c((Executor) this.f10295a.get(), (BackendRegistry) this.f10296b.get(), (WorkScheduler) this.f10297c.get(), (EventStore) this.f10298d.get(), (SynchronizationGuard) this.f10299e.get());
    }
}
