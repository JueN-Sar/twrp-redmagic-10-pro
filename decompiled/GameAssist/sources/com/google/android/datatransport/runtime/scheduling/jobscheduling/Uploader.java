package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import com.google.android.datatransport.runtime.backends.BackendResponse;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent;
import com.google.android.datatransport.runtime.synchronization.SynchronizationException;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class Uploader {

    /* renamed from: a, reason: collision with root package name */
    private final Context f10326a;

    /* renamed from: b, reason: collision with root package name */
    private final BackendRegistry f10327b;

    /* renamed from: c, reason: collision with root package name */
    private final EventStore f10328c;

    /* renamed from: d, reason: collision with root package name */
    private final WorkScheduler f10329d;

    /* renamed from: e, reason: collision with root package name */
    private final Executor f10330e;

    /* renamed from: f, reason: collision with root package name */
    private final SynchronizationGuard f10331f;

    /* renamed from: g, reason: collision with root package name */
    private final Clock f10332g;

    public Uploader(Context context, BackendRegistry backendRegistry, EventStore eventStore, WorkScheduler workScheduler, Executor executor, SynchronizationGuard synchronizationGuard, Clock clock) {
        this.f10326a = context;
        this.f10327b = backendRegistry;
        this.f10328c = eventStore;
        this.f10329d = workScheduler;
        this.f10330e = executor;
        this.f10331f = synchronizationGuard;
        this.f10332g = clock;
    }

    static /* synthetic */ Object c(Uploader uploader, BackendResponse backendResponse, Iterable iterable, TransportContext transportContext, int i2) {
        if (backendResponse.c() == BackendResponse.Status.TRANSIENT_ERROR) {
            uploader.f10328c.N(iterable);
            uploader.f10329d.a(transportContext, i2 + 1);
            return null;
        }
        uploader.f10328c.g(iterable);
        if (backendResponse.c() == BackendResponse.Status.OK) {
            uploader.f10328c.l(transportContext, uploader.f10332g.a() + backendResponse.b());
        }
        if (!uploader.f10328c.M(transportContext)) {
            return null;
        }
        uploader.f10329d.b(transportContext, 1, true);
        return null;
    }

    static /* synthetic */ Object d(Uploader uploader, TransportContext transportContext, int i2) {
        uploader.f10329d.a(transportContext, i2 + 1);
        return null;
    }

    static /* synthetic */ void e(Uploader uploader, TransportContext transportContext, int i2, Runnable runnable) {
        try {
            try {
                SynchronizationGuard synchronizationGuard = uploader.f10331f;
                EventStore eventStore = uploader.f10328c;
                eventStore.getClass();
                synchronizationGuard.a(Uploader$$Lambda$4.b(eventStore));
                if (uploader.a()) {
                    uploader.f(transportContext, i2);
                } else {
                    uploader.f10331f.a(Uploader$$Lambda$5.b(uploader, transportContext, i2));
                }
            } catch (SynchronizationException unused) {
                uploader.f10329d.a(transportContext, i2 + 1);
            }
            runnable.run();
        } catch (Throwable th) {
            runnable.run();
            throw th;
        }
    }

    boolean a() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.f10326a.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    void f(TransportContext transportContext, int i2) {
        BackendResponse b2;
        TransportBackend a2 = this.f10327b.a(transportContext.b());
        Iterable iterable = (Iterable) this.f10331f.a(Uploader$$Lambda$2.b(this, transportContext));
        if (iterable.iterator().hasNext()) {
            if (a2 == null) {
                Logging.a("Uploader", "Unknown backend for %s, deleting event batch for it...", transportContext);
                b2 = BackendResponse.a();
            } else {
                ArrayList arrayList = new ArrayList();
                Iterator it = iterable.iterator();
                while (it.hasNext()) {
                    arrayList.add(((PersistedEvent) it.next()).b());
                }
                b2 = a2.b(BackendRequest.a().b(arrayList).c(transportContext.c()).a());
            }
            this.f10331f.a(Uploader$$Lambda$3.b(this, b2, iterable, transportContext, i2));
        }
    }

    public void g(TransportContext transportContext, int i2, Runnable runnable) {
        this.f10330e.execute(Uploader$$Lambda$1.a(this, transportContext, i2, runnable));
    }
}
