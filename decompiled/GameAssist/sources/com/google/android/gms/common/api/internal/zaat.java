package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.locks.Lock;

/* loaded from: classes.dex */
final class zaat implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ zaaw f10686a;

    /* synthetic */ zaat(zaaw zaawVar, zaas zaasVar) {
        this.f10686a = zaawVar;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        ClientSettings clientSettings;
        com.google.android.gms.signin.zae zaeVar;
        clientSettings = this.f10686a.f10705r;
        zaeVar = this.f10686a.f10698k;
        ((com.google.android.gms.signin.zae) Preconditions.i(zaeVar)).m(new zaar(this.f10686a));
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        Lock lock;
        Lock lock2;
        boolean p2;
        Lock lock3;
        lock = this.f10686a.f10689b;
        lock.lock();
        try {
            p2 = this.f10686a.p(connectionResult);
            if (p2) {
                this.f10686a.h();
                this.f10686a.m();
            } else {
                this.f10686a.k(connectionResult);
            }
            lock3 = this.f10686a.f10689b;
            lock3.unlock();
        } catch (Throwable th) {
            lock2 = this.f10686a.f10689b;
            lock2.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i2) {
    }
}
