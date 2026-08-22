package com.google.android.datatransport.runtime.scheduling;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SchedulingModule_WorkSchedulerFactory implements Factory<WorkScheduler> {

    /* renamed from: a, reason: collision with root package name */
    private final Provider f10301a;

    /* renamed from: b, reason: collision with root package name */
    private final Provider f10302b;

    /* renamed from: c, reason: collision with root package name */
    private final Provider f10303c;

    /* renamed from: d, reason: collision with root package name */
    private final Provider f10304d;

    public SchedulingModule_WorkSchedulerFactory(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        this.f10301a = provider;
        this.f10302b = provider2;
        this.f10303c = provider3;
        this.f10304d = provider4;
    }

    public static SchedulingModule_WorkSchedulerFactory a(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        return new SchedulingModule_WorkSchedulerFactory(provider, provider2, provider3, provider4);
    }

    public static WorkScheduler c(Context context, EventStore eventStore, SchedulerConfig schedulerConfig, Clock clock) {
        return (WorkScheduler) Preconditions.c(SchedulingModule.a(context, eventStore, schedulerConfig, clock), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public WorkScheduler get() {
        return c((Context) this.f10301a.get(), (EventStore) this.f10302b.get(), (SchedulerConfig) this.f10303c.get(), (Clock) this.f10304d.get());
    }
}
