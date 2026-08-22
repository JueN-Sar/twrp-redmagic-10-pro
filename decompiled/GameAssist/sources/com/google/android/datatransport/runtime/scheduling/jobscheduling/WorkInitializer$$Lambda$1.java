package com.google.android.datatransport.runtime.scheduling.jobscheduling;

/* loaded from: classes.dex */
final /* synthetic */ class WorkInitializer$$Lambda$1 implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    private final WorkInitializer f10359c;

    private WorkInitializer$$Lambda$1(WorkInitializer workInitializer) {
        this.f10359c = workInitializer;
    }

    public static Runnable a(WorkInitializer workInitializer) {
        return new WorkInitializer$$Lambda$1(workInitializer);
    }

    @Override // java.lang.Runnable
    public void run() {
        r0.f10358d.a(WorkInitializer$$Lambda$2.b(this.f10359c));
    }
}
