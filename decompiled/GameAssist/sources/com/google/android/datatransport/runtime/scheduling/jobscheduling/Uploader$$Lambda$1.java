package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;

/* loaded from: classes.dex */
final /* synthetic */ class Uploader$$Lambda$1 implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    private final Uploader f10333c;

    /* renamed from: h, reason: collision with root package name */
    private final TransportContext f10334h;

    /* renamed from: i, reason: collision with root package name */
    private final int f10335i;

    /* renamed from: j, reason: collision with root package name */
    private final Runnable f10336j;

    private Uploader$$Lambda$1(Uploader uploader, TransportContext transportContext, int i2, Runnable runnable) {
        this.f10333c = uploader;
        this.f10334h = transportContext;
        this.f10335i = i2;
        this.f10336j = runnable;
    }

    public static Runnable a(Uploader uploader, TransportContext transportContext, int i2, Runnable runnable) {
        return new Uploader$$Lambda$1(uploader, transportContext, i2, runnable);
    }

    @Override // java.lang.Runnable
    public void run() {
        Uploader.e(this.f10333c, this.f10334h, this.f10335i, this.f10336j);
    }
}
