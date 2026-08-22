package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

/* loaded from: classes.dex */
final /* synthetic */ class Uploader$$Lambda$5 implements SynchronizationGuard.CriticalSection {

    /* renamed from: a, reason: collision with root package name */
    private final Uploader f10345a;

    /* renamed from: b, reason: collision with root package name */
    private final TransportContext f10346b;

    /* renamed from: c, reason: collision with root package name */
    private final int f10347c;

    private Uploader$$Lambda$5(Uploader uploader, TransportContext transportContext, int i2) {
        this.f10345a = uploader;
        this.f10346b = transportContext;
        this.f10347c = i2;
    }

    public static SynchronizationGuard.CriticalSection b(Uploader uploader, TransportContext transportContext, int i2) {
        return new Uploader$$Lambda$5(uploader, transportContext, i2);
    }

    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
    public Object a() {
        return Uploader.d(this.f10345a, this.f10346b, this.f10347c);
    }
}
