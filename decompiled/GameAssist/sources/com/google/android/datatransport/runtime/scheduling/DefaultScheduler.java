package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class DefaultScheduler implements Scheduler {

    /* renamed from: f, reason: collision with root package name */
    private static final Logger f10282f = Logger.getLogger(TransportRuntime.class.getName());

    /* renamed from: a, reason: collision with root package name */
    private final WorkScheduler f10283a;

    /* renamed from: b, reason: collision with root package name */
    private final Executor f10284b;

    /* renamed from: c, reason: collision with root package name */
    private final BackendRegistry f10285c;

    /* renamed from: d, reason: collision with root package name */
    private final EventStore f10286d;

    /* renamed from: e, reason: collision with root package name */
    private final SynchronizationGuard f10287e;

    public DefaultScheduler(Executor executor, BackendRegistry backendRegistry, WorkScheduler workScheduler, EventStore eventStore, SynchronizationGuard synchronizationGuard) {
        this.f10284b = executor;
        this.f10285c = backendRegistry;
        this.f10283a = workScheduler;
        this.f10286d = eventStore;
        this.f10287e = synchronizationGuard;
    }

    static /* synthetic */ Object b(DefaultScheduler defaultScheduler, TransportContext transportContext, EventInternal eventInternal) {
        defaultScheduler.f10286d.c0(transportContext, eventInternal);
        defaultScheduler.f10283a.a(transportContext, 1);
        return null;
    }

    static /* synthetic */ void c(DefaultScheduler defaultScheduler, TransportContext transportContext, TransportScheduleCallback transportScheduleCallback, EventInternal eventInternal) {
        try {
            TransportBackend a2 = defaultScheduler.f10285c.a(transportContext.b());
            if (a2 == null) {
                String format = String.format("Transport backend '%s' is not registered", transportContext.b());
                f10282f.warning(format);
                transportScheduleCallback.a(new IllegalArgumentException(format));
            } else {
                defaultScheduler.f10287e.a(DefaultScheduler$$Lambda$2.b(defaultScheduler, transportContext, a2.a(eventInternal)));
                transportScheduleCallback.a(null);
            }
        } catch (Exception e2) {
            f10282f.warning("Error scheduling event " + e2.getMessage());
            transportScheduleCallback.a(e2);
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.Scheduler
    public void a(TransportContext transportContext, EventInternal eventInternal, TransportScheduleCallback transportScheduleCallback) {
        this.f10284b.execute(DefaultScheduler$$Lambda$1.a(this, transportContext, transportScheduleCallback, eventInternal));
    }
}
