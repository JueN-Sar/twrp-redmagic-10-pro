package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.backends.BackendResponse;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

/* loaded from: classes.dex */
final /* synthetic */ class Uploader$$Lambda$3 implements SynchronizationGuard.CriticalSection {

    /* renamed from: a, reason: collision with root package name */
    private final Uploader f10339a;

    /* renamed from: b, reason: collision with root package name */
    private final BackendResponse f10340b;

    /* renamed from: c, reason: collision with root package name */
    private final Iterable f10341c;

    /* renamed from: d, reason: collision with root package name */
    private final TransportContext f10342d;

    /* renamed from: e, reason: collision with root package name */
    private final int f10343e;

    private Uploader$$Lambda$3(Uploader uploader, BackendResponse backendResponse, Iterable iterable, TransportContext transportContext, int i2) {
        this.f10339a = uploader;
        this.f10340b = backendResponse;
        this.f10341c = iterable;
        this.f10342d = transportContext;
        this.f10343e = i2;
    }

    public static SynchronizationGuard.CriticalSection b(Uploader uploader, BackendResponse backendResponse, Iterable iterable, TransportContext transportContext, int i2) {
        return new Uploader$$Lambda$3(uploader, backendResponse, iterable, transportContext, i2);
    }

    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
    public Object a() {
        return Uploader.c(this.f10339a, this.f10340b, this.f10341c, this.f10342d, this.f10343e);
    }
}
