package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

/* loaded from: classes.dex */
final /* synthetic */ class Uploader$$Lambda$2 implements SynchronizationGuard.CriticalSection {

    /* renamed from: a, reason: collision with root package name */
    private final Uploader f10337a;

    /* renamed from: b, reason: collision with root package name */
    private final TransportContext f10338b;

    private Uploader$$Lambda$2(Uploader uploader, TransportContext transportContext) {
        this.f10337a = uploader;
        this.f10338b = transportContext;
    }

    public static SynchronizationGuard.CriticalSection b(Uploader uploader, TransportContext transportContext) {
        return new Uploader$$Lambda$2(uploader, transportContext);
    }

    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
    public Object a() {
        Iterable S;
        S = this.f10337a.f10328c.S(this.f10338b);
        return S;
    }
}
