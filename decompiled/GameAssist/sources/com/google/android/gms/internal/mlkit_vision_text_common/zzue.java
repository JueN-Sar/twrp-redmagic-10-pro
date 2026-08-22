package com.google.android.gms.internal.mlkit_vision_text_common;

import android.content.Context;
import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.TelemetryLogging;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.internal.TelemetryLoggingOptions;
import com.google.android.gms.tasks.OnFailureListener;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes.dex */
public final class zzue {

    /* renamed from: a, reason: collision with root package name */
    private final TelemetryLoggingClient f13580a;

    /* renamed from: b, reason: collision with root package name */
    private final AtomicLong f13581b = new AtomicLong(-1);

    @VisibleForTesting
    zzue(Context context, String str) {
        this.f13580a = TelemetryLogging.b(context, TelemetryLoggingOptions.a().b("mlkit:vision").a());
    }

    public static zzue a(Context context) {
        return new zzue(context, "mlkit:vision");
    }

    final /* synthetic */ void b(long j2, Exception exc) {
        this.f13581b.set(j2);
    }

    public final synchronized void c(int i2, int i3, long j2, long j3) {
        AtomicLong atomicLong = this.f13581b;
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        if (atomicLong.get() != -1 && elapsedRealtime - this.f13581b.get() <= TimeUnit.MINUTES.toMillis(30L)) {
            return;
        }
        this.f13580a.a(new TelemetryData(0, Arrays.asList(new MethodInvocation(i2, i3, 0, j2, j3, null, null, 0, -1)))).d(new OnFailureListener() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzud
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void d(Exception exc) {
                zzue.this.b(elapsedRealtime, exc);
            }
        });
    }
}
