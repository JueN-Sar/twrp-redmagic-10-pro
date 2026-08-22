package com.google.android.gms.common.api.internal;

import android.os.SystemClock;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ConnectionTelemetryConfiguration;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.RootTelemetryConfigManager;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* loaded from: classes.dex */
final class zacd implements OnCompleteListener {

    /* renamed from: a, reason: collision with root package name */
    private final GoogleApiManager f10792a;

    /* renamed from: b, reason: collision with root package name */
    private final int f10793b;

    /* renamed from: c, reason: collision with root package name */
    private final ApiKey f10794c;

    /* renamed from: d, reason: collision with root package name */
    private final long f10795d;

    /* renamed from: e, reason: collision with root package name */
    private final long f10796e;

    @VisibleForTesting
    zacd(GoogleApiManager googleApiManager, int i2, ApiKey apiKey, long j2, long j3, @Nullable String str, @Nullable String str2) {
        this.f10792a = googleApiManager;
        this.f10793b = i2;
        this.f10794c = apiKey;
        this.f10795d = j2;
        this.f10796e = j3;
    }

    static zacd b(GoogleApiManager googleApiManager, int i2, ApiKey apiKey) {
        boolean z;
        if (!googleApiManager.d()) {
            return null;
        }
        RootTelemetryConfiguration a2 = RootTelemetryConfigManager.b().a();
        if (a2 == null) {
            z = true;
        } else {
            if (!a2.R()) {
                return null;
            }
            z = a2.T();
            zabq s2 = googleApiManager.s(apiKey);
            if (s2 != null) {
                if (!(s2.u() instanceof BaseGmsClient)) {
                    return null;
                }
                BaseGmsClient baseGmsClient = (BaseGmsClient) s2.u();
                if (baseGmsClient.K() && !baseGmsClient.b()) {
                    ConnectionTelemetryConfiguration c2 = c(s2, baseGmsClient, i2);
                    if (c2 == null) {
                        return null;
                    }
                    s2.F();
                    z = c2.W();
                }
            }
        }
        return new zacd(googleApiManager, i2, apiKey, z ? System.currentTimeMillis() : 0L, z ? SystemClock.elapsedRealtime() : 0L, null, null);
    }

    private static ConnectionTelemetryConfiguration c(zabq zabqVar, BaseGmsClient baseGmsClient, int i2) {
        int[] P;
        int[] R;
        ConnectionTelemetryConfiguration I = baseGmsClient.I();
        if (I == null || !I.T() || ((P = I.P()) != null ? !ArrayUtils.a(P, i2) : !((R = I.R()) == null || !ArrayUtils.a(R, i2))) || zabqVar.r() >= I.G()) {
            return null;
        }
        return I;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void a(Task task) {
        zabq s2;
        int i2;
        int i3;
        int i4;
        int G;
        long j2;
        long j3;
        int i5;
        if (this.f10792a.d()) {
            RootTelemetryConfiguration a2 = RootTelemetryConfigManager.b().a();
            if ((a2 == null || a2.R()) && (s2 = this.f10792a.s(this.f10794c)) != null && (s2.u() instanceof BaseGmsClient)) {
                BaseGmsClient baseGmsClient = (BaseGmsClient) s2.u();
                int i6 = 0;
                boolean z = this.f10795d > 0;
                int A = baseGmsClient.A();
                int i7 = 100;
                if (a2 != null) {
                    z &= a2.T();
                    int G2 = a2.G();
                    int P = a2.P();
                    i2 = a2.W();
                    if (baseGmsClient.K() && !baseGmsClient.b()) {
                        ConnectionTelemetryConfiguration c2 = c(s2, baseGmsClient, this.f10793b);
                        if (c2 == null) {
                            return;
                        }
                        boolean z2 = c2.W() && this.f10795d > 0;
                        P = c2.G();
                        z = z2;
                    }
                    i4 = G2;
                    i3 = P;
                } else {
                    i2 = 0;
                    i3 = 100;
                    i4 = 5000;
                }
                GoogleApiManager googleApiManager = this.f10792a;
                if (task.l()) {
                    G = 0;
                } else {
                    if (!task.j()) {
                        Exception h2 = task.h();
                        if (h2 instanceof ApiException) {
                            Status a3 = ((ApiException) h2).a();
                            i7 = a3.P();
                            ConnectionResult G3 = a3.G();
                            if (G3 != null) {
                                G = G3.G();
                                i6 = i7;
                            }
                        } else {
                            i6 = 101;
                            G = -1;
                        }
                    }
                    i6 = i7;
                    G = -1;
                }
                if (z) {
                    long j4 = this.f10795d;
                    long j5 = this.f10796e;
                    long currentTimeMillis = System.currentTimeMillis();
                    i5 = (int) (SystemClock.elapsedRealtime() - j5);
                    j3 = currentTimeMillis;
                    j2 = j4;
                } else {
                    j2 = 0;
                    j3 = 0;
                    i5 = -1;
                }
                googleApiManager.D(new MethodInvocation(this.f10793b, i6, G, j2, j3, null, null, A, i5), i2, i4, i3);
            }
        }
    }
}
