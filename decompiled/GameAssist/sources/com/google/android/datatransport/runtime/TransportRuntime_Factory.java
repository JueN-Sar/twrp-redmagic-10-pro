package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.Scheduler;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class TransportRuntime_Factory implements Factory<TransportRuntime> {

    /* renamed from: a, reason: collision with root package name */
    private final Provider f10239a;

    /* renamed from: b, reason: collision with root package name */
    private final Provider f10240b;

    /* renamed from: c, reason: collision with root package name */
    private final Provider f10241c;

    /* renamed from: d, reason: collision with root package name */
    private final Provider f10242d;

    /* renamed from: e, reason: collision with root package name */
    private final Provider f10243e;

    public TransportRuntime_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5) {
        this.f10239a = provider;
        this.f10240b = provider2;
        this.f10241c = provider3;
        this.f10242d = provider4;
        this.f10243e = provider5;
    }

    public static TransportRuntime_Factory a(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5) {
        return new TransportRuntime_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static TransportRuntime c(Clock clock, Clock clock2, Scheduler scheduler, Uploader uploader, WorkInitializer workInitializer) {
        return new TransportRuntime(clock, clock2, scheduler, uploader, workInitializer);
    }

    @Override // javax.inject.Provider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public TransportRuntime get() {
        return c((Clock) this.f10239a.get(), (Clock) this.f10240b.get(), (Scheduler) this.f10241c.get(), (Uploader) this.f10242d.get(), (WorkInitializer) this.f10243e.get());
    }
}
