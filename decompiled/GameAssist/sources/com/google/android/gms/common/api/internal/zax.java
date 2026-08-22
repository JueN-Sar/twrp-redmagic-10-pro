package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.locks.Lock;

/* loaded from: classes.dex */
final class zax implements zabz {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ zaaa f10868a;

    /* synthetic */ zax(zaaa zaaaVar, zaw zawVar) {
        this.f10868a = zaaaVar;
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void a(Bundle bundle) {
        Lock lock;
        Lock lock2;
        lock = this.f10868a.f10656m;
        lock.lock();
        try {
            zaaa.u(this.f10868a, bundle);
            this.f10868a.f10653j = ConnectionResult.f10484k;
            zaaa.v(this.f10868a);
        } finally {
            lock2 = this.f10868a.f10656m;
            lock2.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void b(int i2, boolean z) {
        Lock lock;
        Lock lock2;
        boolean z2;
        Lock lock3;
        ConnectionResult connectionResult;
        ConnectionResult connectionResult2;
        zabi zabiVar;
        lock = this.f10868a.f10656m;
        lock.lock();
        try {
            zaaa zaaaVar = this.f10868a;
            z2 = zaaaVar.f10655l;
            if (!z2) {
                connectionResult = zaaaVar.f10654k;
                if (connectionResult != null) {
                    connectionResult2 = zaaaVar.f10654k;
                    if (connectionResult2.W()) {
                        this.f10868a.f10655l = true;
                        zabiVar = this.f10868a.f10648e;
                        zabiVar.onConnectionSuspended(i2);
                        lock3 = this.f10868a.f10656m;
                        lock3.unlock();
                    }
                }
            }
            this.f10868a.f10655l = false;
            zaaa.t(this.f10868a, i2, z);
            lock3 = this.f10868a.f10656m;
            lock3.unlock();
        } catch (Throwable th) {
            lock2 = this.f10868a.f10656m;
            lock2.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void c(ConnectionResult connectionResult) {
        Lock lock;
        Lock lock2;
        lock = this.f10868a.f10656m;
        lock.lock();
        try {
            this.f10868a.f10653j = connectionResult;
            zaaa.v(this.f10868a);
        } finally {
            lock2 = this.f10868a.f10656m;
            lock2.unlock();
        }
    }
}
