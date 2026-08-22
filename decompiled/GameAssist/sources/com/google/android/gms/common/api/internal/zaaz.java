package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
final class zaaz implements GoogleApiClient.ConnectionCallbacks {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ AtomicReference f10709a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ StatusPendingResult f10710b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ zabe f10711c;

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        this.f10711c.v((GoogleApiClient) Preconditions.i((GoogleApiClient) this.f10709a.get()), this.f10710b, true);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i2) {
    }
}
