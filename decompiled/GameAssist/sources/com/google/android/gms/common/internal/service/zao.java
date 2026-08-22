package com.google.android.gms.common.internal.service;

import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.internal.TelemetryLoggingOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
public final class zao extends GoogleApi implements TelemetryLoggingClient {

    /* renamed from: k, reason: collision with root package name */
    private static final Api.ClientKey f11044k;

    /* renamed from: l, reason: collision with root package name */
    private static final Api.AbstractClientBuilder f11045l;

    /* renamed from: m, reason: collision with root package name */
    private static final Api f11046m;

    /* renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ int f11047n = 0;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        f11044k = clientKey;
        zan zanVar = new zan();
        f11045l = zanVar;
        f11046m = new Api("ClientTelemetry.API", zanVar, clientKey);
    }

    public zao(Context context, TelemetryLoggingOptions telemetryLoggingOptions) {
        super(context, f11046m, telemetryLoggingOptions, GoogleApi.Settings.f10531c);
    }

    @Override // com.google.android.gms.common.internal.TelemetryLoggingClient
    public final Task a(final TelemetryData telemetryData) {
        TaskApiCall.Builder a2 = TaskApiCall.a();
        a2.d(com.google.android.gms.internal.base.zaf.f11366a);
        a2.c(false);
        a2.b(new RemoteCall() { // from class: com.google.android.gms.common.internal.service.zam
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                int i2 = zao.f11047n;
                ((zai) ((zap) obj).E()).zae(TelemetryData.this);
                ((TaskCompletionSource) obj2).c(null);
            }
        });
        return d(a2.a());
    }
}
