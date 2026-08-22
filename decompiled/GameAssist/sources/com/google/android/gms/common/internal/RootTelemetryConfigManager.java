package com.google.android.gms.common.internal;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* loaded from: classes.dex */
public final class RootTelemetryConfigManager {

    /* renamed from: b, reason: collision with root package name */
    private static RootTelemetryConfigManager f11024b;

    /* renamed from: c, reason: collision with root package name */
    private static final RootTelemetryConfiguration f11025c = new RootTelemetryConfiguration(0, false, false, 0, 0);

    /* renamed from: a, reason: collision with root package name */
    private RootTelemetryConfiguration f11026a;

    private RootTelemetryConfigManager() {
    }

    public static synchronized RootTelemetryConfigManager b() {
        RootTelemetryConfigManager rootTelemetryConfigManager;
        synchronized (RootTelemetryConfigManager.class) {
            try {
                if (f11024b == null) {
                    f11024b = new RootTelemetryConfigManager();
                }
                rootTelemetryConfigManager = f11024b;
            } catch (Throwable th) {
                throw th;
            }
        }
        return rootTelemetryConfigManager;
    }

    public RootTelemetryConfiguration a() {
        return this.f11026a;
    }

    @VisibleForTesting
    public final synchronized void zza(@Nullable RootTelemetryConfiguration rootTelemetryConfiguration) {
        if (rootTelemetryConfiguration == null) {
            this.f11026a = f11025c;
            return;
        }
        RootTelemetryConfiguration rootTelemetryConfiguration2 = this.f11026a;
        if (rootTelemetryConfiguration2 == null || rootTelemetryConfiguration2.W() < rootTelemetryConfiguration.W()) {
            this.f11026a = rootTelemetryConfiguration;
        }
    }
}
