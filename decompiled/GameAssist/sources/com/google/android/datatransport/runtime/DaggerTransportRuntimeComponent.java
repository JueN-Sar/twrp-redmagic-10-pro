package com.google.android.datatransport.runtime;

import android.content.Context;
import com.google.android.datatransport.runtime.TransportRuntimeComponent;
import com.google.android.datatransport.runtime.backends.CreationContextFactory_Factory;
import com.google.android.datatransport.runtime.backends.MetadataBackendRegistry_Factory;
import com.google.android.datatransport.runtime.dagger.internal.DoubleCheck;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.InstanceFactory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import com.google.android.datatransport.runtime.scheduling.DefaultScheduler_Factory;
import com.google.android.datatransport.runtime.scheduling.SchedulingConfigModule_ConfigFactory;
import com.google.android.datatransport.runtime.scheduling.SchedulingModule_WorkSchedulerFactory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader_Factory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer_Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_DbNameFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_SchemaVersionFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_StoreConfigFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore_Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager_Factory;
import com.google.android.datatransport.runtime.time.TimeModule_EventClockFactory;
import com.google.android.datatransport.runtime.time.TimeModule_UptimeClockFactory;
import javax.inject.Provider;

/* loaded from: classes.dex */
final class DaggerTransportRuntimeComponent extends TransportRuntimeComponent {

    /* renamed from: c, reason: collision with root package name */
    private Provider f10207c;

    /* renamed from: h, reason: collision with root package name */
    private Provider f10208h;

    /* renamed from: i, reason: collision with root package name */
    private Provider f10209i;

    /* renamed from: j, reason: collision with root package name */
    private Provider f10210j;

    /* renamed from: k, reason: collision with root package name */
    private Provider f10211k;

    /* renamed from: l, reason: collision with root package name */
    private Provider f10212l;

    /* renamed from: m, reason: collision with root package name */
    private Provider f10213m;

    /* renamed from: n, reason: collision with root package name */
    private Provider f10214n;

    /* renamed from: o, reason: collision with root package name */
    private Provider f10215o;

    /* renamed from: p, reason: collision with root package name */
    private Provider f10216p;

    /* renamed from: q, reason: collision with root package name */
    private Provider f10217q;

    /* renamed from: r, reason: collision with root package name */
    private Provider f10218r;

    private static final class Builder implements TransportRuntimeComponent.Builder {

        /* renamed from: a, reason: collision with root package name */
        private Context f10219a;

        private Builder() {
        }

        @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent.Builder
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Builder a(Context context) {
            this.f10219a = (Context) Preconditions.b(context);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent.Builder
        public TransportRuntimeComponent build() {
            Preconditions.a(this.f10219a, Context.class);
            return new DaggerTransportRuntimeComponent(this.f10219a);
        }
    }

    public static TransportRuntimeComponent.Builder d() {
        return new Builder();
    }

    private void e(Context context) {
        this.f10207c = DoubleCheck.b(ExecutionModule_ExecutorFactory.a());
        Factory a2 = InstanceFactory.a(context);
        this.f10208h = a2;
        CreationContextFactory_Factory a3 = CreationContextFactory_Factory.a(a2, TimeModule_EventClockFactory.a(), TimeModule_UptimeClockFactory.a());
        this.f10209i = a3;
        this.f10210j = DoubleCheck.b(MetadataBackendRegistry_Factory.a(this.f10208h, a3));
        this.f10211k = SchemaManager_Factory.a(this.f10208h, EventStoreModule_DbNameFactory.a(), EventStoreModule_SchemaVersionFactory.a());
        this.f10212l = DoubleCheck.b(SQLiteEventStore_Factory.a(TimeModule_EventClockFactory.a(), TimeModule_UptimeClockFactory.a(), EventStoreModule_StoreConfigFactory.a(), this.f10211k));
        SchedulingConfigModule_ConfigFactory b2 = SchedulingConfigModule_ConfigFactory.b(TimeModule_EventClockFactory.a());
        this.f10213m = b2;
        SchedulingModule_WorkSchedulerFactory a4 = SchedulingModule_WorkSchedulerFactory.a(this.f10208h, this.f10212l, b2, TimeModule_UptimeClockFactory.a());
        this.f10214n = a4;
        Provider provider = this.f10207c;
        Provider provider2 = this.f10210j;
        Provider provider3 = this.f10212l;
        this.f10215o = DefaultScheduler_Factory.a(provider, provider2, a4, provider3, provider3);
        Provider provider4 = this.f10208h;
        Provider provider5 = this.f10210j;
        Provider provider6 = this.f10212l;
        this.f10216p = Uploader_Factory.a(provider4, provider5, provider6, this.f10214n, this.f10207c, provider6, TimeModule_EventClockFactory.a());
        Provider provider7 = this.f10207c;
        Provider provider8 = this.f10212l;
        this.f10217q = WorkInitializer_Factory.a(provider7, provider8, this.f10214n, provider8);
        this.f10218r = DoubleCheck.b(TransportRuntime_Factory.a(TimeModule_EventClockFactory.a(), TimeModule_UptimeClockFactory.a(), this.f10215o, this.f10216p, this.f10217q));
    }

    @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent
    EventStore a() {
        return (EventStore) this.f10212l.get();
    }

    @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent
    TransportRuntime c() {
        return (TransportRuntime) this.f10218r.get();
    }

    private DaggerTransportRuntimeComponent(Context context) {
        e(context);
    }
}
