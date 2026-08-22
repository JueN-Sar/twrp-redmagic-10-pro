package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.Context;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Uploader_Factory implements Factory<Uploader> {

    /* renamed from: a, reason: collision with root package name */
    private final Provider f10348a;

    /* renamed from: b, reason: collision with root package name */
    private final Provider f10349b;

    /* renamed from: c, reason: collision with root package name */
    private final Provider f10350c;

    /* renamed from: d, reason: collision with root package name */
    private final Provider f10351d;

    /* renamed from: e, reason: collision with root package name */
    private final Provider f10352e;

    /* renamed from: f, reason: collision with root package name */
    private final Provider f10353f;

    /* renamed from: g, reason: collision with root package name */
    private final Provider f10354g;

    public Uploader_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7) {
        this.f10348a = provider;
        this.f10349b = provider2;
        this.f10350c = provider3;
        this.f10351d = provider4;
        this.f10352e = provider5;
        this.f10353f = provider6;
        this.f10354g = provider7;
    }

    public static Uploader_Factory a(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7) {
        return new Uploader_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static Uploader c(Context context, BackendRegistry backendRegistry, EventStore eventStore, WorkScheduler workScheduler, Executor executor, SynchronizationGuard synchronizationGuard, Clock clock) {
        return new Uploader(context, backendRegistry, eventStore, workScheduler, executor, synchronizationGuard, clock);
    }

    @Override // javax.inject.Provider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Uploader get() {
        return c((Context) this.f10348a.get(), (BackendRegistry) this.f10349b.get(), (EventStore) this.f10350c.get(), (WorkScheduler) this.f10351d.get(), (Executor) this.f10352e.get(), (SynchronizationGuard) this.f10353f.get(), (Clock) this.f10354g.get());
    }
}
