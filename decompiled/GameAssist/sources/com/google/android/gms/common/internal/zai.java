package com.google.android.gms.common.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.BaseGmsClient;

/* loaded from: classes.dex */
final class zai implements BaseGmsClient.BaseOnConnectionFailedListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ OnConnectionFailedListener f11060a;

    zai(OnConnectionFailedListener onConnectionFailedListener) {
        this.f11060a = onConnectionFailedListener;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.f11060a.onConnectionFailed(connectionResult);
    }
}
