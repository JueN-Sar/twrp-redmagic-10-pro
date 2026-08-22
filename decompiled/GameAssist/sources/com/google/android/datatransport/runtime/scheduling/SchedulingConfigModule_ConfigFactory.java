package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SchedulingConfigModule_ConfigFactory implements Factory<SchedulerConfig> {

    /* renamed from: a, reason: collision with root package name */
    private final Provider f10300a;

    public SchedulingConfigModule_ConfigFactory(Provider provider) {
        this.f10300a = provider;
    }

    public static SchedulerConfig a(Clock clock) {
        return (SchedulerConfig) Preconditions.c(SchedulingConfigModule.a(clock), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static SchedulingConfigModule_ConfigFactory b(Provider provider) {
        return new SchedulingConfigModule_ConfigFactory(provider);
    }

    @Override // javax.inject.Provider
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public SchedulerConfig get() {
        return a((Clock) this.f10300a.get());
    }
}
