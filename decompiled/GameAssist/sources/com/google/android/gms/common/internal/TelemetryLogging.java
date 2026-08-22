package com.google.android.gms.common.internal;

import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* loaded from: classes.dex */
public class TelemetryLogging {
    public static TelemetryLoggingClient a(Context context) {
        return b(context, TelemetryLoggingOptions.f11036h);
    }

    public static TelemetryLoggingClient b(Context context, TelemetryLoggingOptions telemetryLoggingOptions) {
        return new com.google.android.gms.common.internal.service.zao(context, telemetryLoggingOptions);
    }
}
