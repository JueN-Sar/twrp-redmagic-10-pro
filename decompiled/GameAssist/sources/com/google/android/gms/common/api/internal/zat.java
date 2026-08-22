package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes.dex */
public final class zat implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    /* renamed from: a, reason: collision with root package name */
    public final Api f10864a;

    /* renamed from: b, reason: collision with root package name */
    private final boolean f10865b;

    /* renamed from: c, reason: collision with root package name */
    private zau f10866c;

    private final zau b() {
        Preconditions.j(this.f10866c, "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
        return this.f10866c;
    }

    public final void a(zau zauVar) {
        this.f10866c = zauVar;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        b().onConnected(bundle);
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        b().g(connectionResult, this.f10864a, this.f10865b);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i2) {
        b().onConnectionSuspended(i2);
    }
}
