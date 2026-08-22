package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient;

/* loaded from: classes.dex */
final class zah implements BaseGmsClient.BaseConnectionCallbacks {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ ConnectionCallbacks f11059a;

    zah(ConnectionCallbacks connectionCallbacks) {
        this.f11059a = connectionCallbacks;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        this.f11059a.onConnected(bundle);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnectionSuspended(int i2) {
        this.f11059a.onConnectionSuspended(i2);
    }
}
