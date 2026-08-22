package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/* loaded from: classes.dex */
final class zaj implements GoogleApiClient.OnConnectionFailedListener {

    /* renamed from: a, reason: collision with root package name */
    public final int f10843a;

    /* renamed from: b, reason: collision with root package name */
    public final GoogleApiClient f10844b;

    /* renamed from: c, reason: collision with root package name */
    public final GoogleApiClient.OnConnectionFailedListener f10845c;

    /* renamed from: d, reason: collision with root package name */
    final /* synthetic */ zak f10846d;

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("AutoManageHelper", "beginFailureResolution for ".concat(String.valueOf(connectionResult)));
        this.f10846d.h(connectionResult, this.f10843a);
    }
}
