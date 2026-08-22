package com.google.android.datatransport.runtime;

import android.content.Context;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.scheduling.Scheduler;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Callable;
import javax.inject.Singleton;

@Singleton
/* loaded from: classes.dex */
public class TransportRuntime implements TransportInternal {

    /* renamed from: e, reason: collision with root package name */
    private static volatile TransportRuntimeComponent f10234e;

    /* renamed from: a, reason: collision with root package name */
    private final Clock f10235a;

    /* renamed from: b, reason: collision with root package name */
    private final Clock f10236b;

    /* renamed from: c, reason: collision with root package name */
    private final Scheduler f10237c;

    /* renamed from: d, reason: collision with root package name */
    private final Uploader f10238d;

    TransportRuntime(Clock clock, Clock clock2, Scheduler scheduler, Uploader uploader, WorkInitializer workInitializer) {
        this.f10235a = clock;
        this.f10236b = clock2;
        this.f10237c = scheduler;
        this.f10238d = uploader;
        workInitializer.a();
    }

    private EventInternal b(SendRequest sendRequest) {
        return EventInternal.a().i(this.f10235a.a()).k(this.f10236b.a()).j(sendRequest.g()).h(new EncodedPayload(sendRequest.b(), sendRequest.d())).g(sendRequest.c().a()).d();
    }

    public static TransportRuntime c() {
        TransportRuntimeComponent transportRuntimeComponent = f10234e;
        if (transportRuntimeComponent != null) {
            return transportRuntimeComponent.c();
        }
        throw new IllegalStateException("Not initialized!");
    }

    private static Set d(Destination destination) {
        return destination instanceof EncodedDestination ? Collections.unmodifiableSet(((EncodedDestination) destination).a()) : Collections.singleton(Encoding.b("proto"));
    }

    public static void f(Context context) {
        if (f10234e == null) {
            synchronized (TransportRuntime.class) {
                try {
                    if (f10234e == null) {
                        f10234e = DaggerTransportRuntimeComponent.d().a(context).build();
                    }
                } finally {
                }
            }
        }
    }

    @RestrictTo
    @VisibleForTesting
    static void withInstance(TransportRuntimeComponent transportRuntimeComponent, Callable<Void> callable) {
        TransportRuntimeComponent transportRuntimeComponent2;
        synchronized (TransportRuntime.class) {
            transportRuntimeComponent2 = f10234e;
            f10234e = transportRuntimeComponent;
        }
        try {
            callable.call();
            synchronized (TransportRuntime.class) {
                f10234e = transportRuntimeComponent2;
            }
        } catch (Throwable th) {
            synchronized (TransportRuntime.class) {
                f10234e = transportRuntimeComponent2;
                throw th;
            }
        }
    }

    @Override // com.google.android.datatransport.runtime.TransportInternal
    public void a(SendRequest sendRequest, TransportScheduleCallback transportScheduleCallback) {
        this.f10237c.a(sendRequest.f().e(sendRequest.c().c()), b(sendRequest), transportScheduleCallback);
    }

    public Uploader e() {
        return this.f10238d;
    }

    public TransportFactory g(Destination destination) {
        return new TransportFactoryImpl(d(destination), TransportContext.a().b(destination.getName()).c(destination.getExtras()).a(), this);
    }
}
