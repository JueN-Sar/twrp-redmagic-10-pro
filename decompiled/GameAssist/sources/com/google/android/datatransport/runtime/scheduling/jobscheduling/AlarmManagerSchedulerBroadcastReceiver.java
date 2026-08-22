package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.datatransport.runtime.util.PriorityMapping;

/* loaded from: classes.dex */
public class AlarmManagerSchedulerBroadcastReceiver extends BroadcastReceiver {
    static /* synthetic */ void a() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String queryParameter = intent.getData().getQueryParameter("backendName");
        String queryParameter2 = intent.getData().getQueryParameter("extras");
        int intValue = Integer.valueOf(intent.getData().getQueryParameter("priority")).intValue();
        int i2 = intent.getExtras().getInt("attemptNumber");
        TransportRuntime.f(context);
        TransportContext.Builder d2 = TransportContext.a().b(queryParameter).d(PriorityMapping.b(intValue));
        if (queryParameter2 != null) {
            d2.c(Base64.decode(queryParameter2, 0));
        }
        TransportRuntime.c().e().g(d2.a(), i2, AlarmManagerSchedulerBroadcastReceiver$$Lambda$1.a());
    }
}
