package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Base64;
import androidx.annotation.VisibleForTesting;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.util.PriorityMapping;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class AlarmManagerScheduler implements WorkScheduler {

    /* renamed from: a, reason: collision with root package name */
    private final Context f10305a;

    /* renamed from: b, reason: collision with root package name */
    private final EventStore f10306b;

    /* renamed from: c, reason: collision with root package name */
    private AlarmManager f10307c;

    /* renamed from: d, reason: collision with root package name */
    private final SchedulerConfig f10308d;

    /* renamed from: e, reason: collision with root package name */
    private final Clock f10309e;

    @VisibleForTesting
    AlarmManagerScheduler(Context context, EventStore eventStore, AlarmManager alarmManager, Clock clock, SchedulerConfig schedulerConfig) {
        this.f10305a = context;
        this.f10306b = eventStore;
        this.f10307c = alarmManager;
        this.f10309e = clock;
        this.f10308d = schedulerConfig;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler
    public void a(TransportContext transportContext, int i2) {
        b(transportContext, i2, false);
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler
    public void b(TransportContext transportContext, int i2, boolean z) {
        Uri.Builder builder = new Uri.Builder();
        builder.appendQueryParameter("backendName", transportContext.b());
        builder.appendQueryParameter("priority", String.valueOf(PriorityMapping.a(transportContext.d())));
        if (transportContext.c() != null) {
            builder.appendQueryParameter("extras", Base64.encodeToString(transportContext.c(), 0));
        }
        Intent intent = new Intent(this.f10305a, (Class<?>) AlarmManagerSchedulerBroadcastReceiver.class);
        intent.setData(builder.build());
        intent.putExtra("attemptNumber", i2);
        if (!z && isJobServiceOn(intent)) {
            Logging.a("AlarmManagerScheduler", "Upload for context %s is already scheduled. Returning...", transportContext);
            return;
        }
        long K = this.f10306b.K(transportContext);
        long g2 = this.f10308d.g(transportContext.d(), K, i2);
        Logging.b("AlarmManagerScheduler", "Scheduling upload for context %s in %dms(Backend next call timestamp %d). Attempt %d", transportContext, Long.valueOf(g2), Long.valueOf(K), Integer.valueOf(i2));
        this.f10307c.set(3, this.f10309e.a() + g2, PendingIntent.getBroadcast(this.f10305a, 0, intent, 0));
    }

    @VisibleForTesting
    boolean isJobServiceOn(Intent intent) {
        return PendingIntent.getBroadcast(this.f10305a, 0, intent, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_TRUSTED_OVERLAY) != null;
    }
}
