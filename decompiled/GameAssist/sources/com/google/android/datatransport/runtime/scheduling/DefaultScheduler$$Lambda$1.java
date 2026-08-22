package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;

/* loaded from: classes.dex */
final /* synthetic */ class DefaultScheduler$$Lambda$1 implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    private final DefaultScheduler f10288c;

    /* renamed from: h, reason: collision with root package name */
    private final TransportContext f10289h;

    /* renamed from: i, reason: collision with root package name */
    private final TransportScheduleCallback f10290i;

    /* renamed from: j, reason: collision with root package name */
    private final EventInternal f10291j;

    private DefaultScheduler$$Lambda$1(DefaultScheduler defaultScheduler, TransportContext transportContext, TransportScheduleCallback transportScheduleCallback, EventInternal eventInternal) {
        this.f10288c = defaultScheduler;
        this.f10289h = transportContext;
        this.f10290i = transportScheduleCallback;
        this.f10291j = eventInternal;
    }

    public static Runnable a(DefaultScheduler defaultScheduler, TransportContext transportContext, TransportScheduleCallback transportScheduleCallback, EventInternal eventInternal) {
        return new DefaultScheduler$$Lambda$1(defaultScheduler, transportContext, transportScheduleCallback, eventInternal);
    }

    @Override // java.lang.Runnable
    public void run() {
        DefaultScheduler.c(this.f10288c, this.f10289h, this.f10290i, this.f10291j);
    }
}
