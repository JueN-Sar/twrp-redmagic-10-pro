package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;
import java.util.concurrent.locks.Lock;

/* loaded from: classes.dex */
final class zaal implements BaseGmsClient.ConnectionProgressReportCallbacks {

    /* renamed from: a, reason: collision with root package name */
    private final WeakReference f10674a;

    /* renamed from: b, reason: collision with root package name */
    private final Api f10675b;

    /* renamed from: c, reason: collision with root package name */
    private final boolean f10676c;

    public zaal(zaaw zaawVar, Api api, boolean z) {
        this.f10674a = new WeakReference(zaawVar);
        this.f10675b = api;
        this.f10676c = z;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
    public final void a(ConnectionResult connectionResult) {
        zabi zabiVar;
        Lock lock;
        Lock lock2;
        boolean n2;
        boolean o2;
        zaaw zaawVar = (zaaw) this.f10674a.get();
        if (zaawVar == null) {
            return;
        }
        Looper myLooper = Looper.myLooper();
        zabiVar = zaawVar.f10688a;
        Preconditions.m(myLooper == zabiVar.f10753n.h(), "onReportServiceBinding must be called on the GoogleApiClient handler thread");
        lock = zaawVar.f10689b;
        lock.lock();
        try {
            n2 = zaawVar.n(0);
            if (n2) {
                if (!connectionResult.W()) {
                    zaawVar.l(connectionResult, this.f10675b, this.f10676c);
                }
                o2 = zaawVar.o();
                if (o2) {
                    zaawVar.m();
                }
            }
        } finally {
            lock2 = zaawVar.f10689b;
            lock2.unlock();
        }
    }
}
