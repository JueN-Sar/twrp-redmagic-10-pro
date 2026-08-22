package com.google.android.gms.common.api.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

/* loaded from: classes.dex */
public final class zacc extends zap {

    /* renamed from: k, reason: collision with root package name */
    private TaskCompletionSource f10791k;

    @Override // com.google.android.gms.common.api.internal.zap
    protected final void b(ConnectionResult connectionResult, int i2) {
        String P = connectionResult.P();
        if (P == null) {
            P = "Error connecting to Google Play services";
        }
        this.f10791k.b(new ApiException(new Status(connectionResult, P, connectionResult.G())));
    }

    @Override // com.google.android.gms.common.api.internal.zap
    protected final void c() {
        Activity g2 = this.mLifecycleFragment.g();
        if (g2 == null) {
            this.f10791k.d(new ApiException(new Status(8)));
            return;
        }
        int i2 = this.f10862j.i(g2);
        if (i2 == 0) {
            this.f10791k.e(null);
        } else {
            if (this.f10791k.a().k()) {
                return;
            }
            h(new ConnectionResult(i2, null), 0);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onDestroy() {
        super.onDestroy();
        this.f10791k.d(new CancellationException("Host activity was destroyed before Google Play services could be made available."));
    }
}
