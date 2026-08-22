package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

/* loaded from: classes.dex */
final /* synthetic */ class DefaultScheduler$$Lambda$2 implements SynchronizationGuard.CriticalSection {

    /* renamed from: a, reason: collision with root package name */
    private final DefaultScheduler f10292a;

    /* renamed from: b, reason: collision with root package name */
    private final TransportContext f10293b;

    /* renamed from: c, reason: collision with root package name */
    private final EventInternal f10294c;

    private DefaultScheduler$$Lambda$2(DefaultScheduler defaultScheduler, TransportContext transportContext, EventInternal eventInternal) {
        this.f10292a = defaultScheduler;
        this.f10293b = transportContext;
        this.f10294c = eventInternal;
    }

    public static SynchronizationGuard.CriticalSection b(DefaultScheduler defaultScheduler, TransportContext transportContext, EventInternal eventInternal) {
        return new DefaultScheduler$$Lambda$2(defaultScheduler, transportContext, eventInternal);
    }

    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
    public Object a() {
        return DefaultScheduler.b(this.f10292a, this.f10293b, this.f10294c);
    }
}
