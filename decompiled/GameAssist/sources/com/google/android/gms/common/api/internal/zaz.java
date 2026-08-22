package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.locks.Lock;

/* loaded from: classes.dex */
final class zaz implements zabz {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ zaaa f10869a;

    /* synthetic */ zaz(zaaa zaaaVar, zay zayVar) {
        this.f10869a = zaaaVar;
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void a(Bundle bundle) {
        Lock lock;
        Lock lock2;
        lock = this.f10869a.f10656m;
        lock.lock();
        try {
            this.f10869a.f10654k = ConnectionResult.f10484k;
            zaaa.v(this.f10869a);
        } finally {
            lock2 = this.f10869a.f10656m;
            lock2.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void b(int i2, boolean z) {
        Lock lock;
        Lock lock2;
        boolean z2;
        zabi zabiVar;
        Lock lock3;
        lock = this.f10869a.f10656m;
        lock.lock();
        try {
            zaaa zaaaVar = this.f10869a;
            z2 = zaaaVar.f10655l;
            if (z2) {
                zaaaVar.f10655l = false;
                zaaa.t(this.f10869a, i2, z);
            } else {
                zaaaVar.f10655l = true;
                zabiVar = this.f10869a.f10647d;
                zabiVar.onConnectionSuspended(i2);
            }
            lock3 = this.f10869a.f10656m;
            lock3.unlock();
        } catch (Throwable th) {
            lock2 = this.f10869a.f10656m;
            lock2.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void c(ConnectionResult connectionResult) {
        Lock lock;
        Lock lock2;
        lock = this.f10869a.f10656m;
        lock.lock();
        try {
            this.f10869a.f10654k = connectionResult;
            zaaa.v(this.f10869a);
        } finally {
            lock2 = this.f10869a.f10656m;
            lock2.unlock();
        }
    }
}
