package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

/* loaded from: classes.dex */
final class zaba implements GoogleApiClient.OnConnectionFailedListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ StatusPendingResult f10713a;

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.f10713a.j(new Status(8));
    }
}
