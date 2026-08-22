package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.HashMap;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzs extends GmsClientSupervisor {

    /* renamed from: e, reason: collision with root package name */
    private final HashMap f11123e = new HashMap();

    /* renamed from: f, reason: collision with root package name */
    private final Context f11124f;

    /* renamed from: g, reason: collision with root package name */
    private volatile Handler f11125g;

    /* renamed from: h, reason: collision with root package name */
    private final zzr f11126h;

    /* renamed from: i, reason: collision with root package name */
    private final ConnectionTracker f11127i;

    /* renamed from: j, reason: collision with root package name */
    private final long f11128j;

    /* renamed from: k, reason: collision with root package name */
    private final long f11129k;

    /* renamed from: l, reason: collision with root package name */
    private volatile Executor f11130l;

    zzs(Context context, Looper looper, Executor executor) {
        zzr zzrVar = new zzr(this, null);
        this.f11126h = zzrVar;
        this.f11124f = context.getApplicationContext();
        this.f11125g = new com.google.android.gms.internal.common.zzi(looper, zzrVar);
        this.f11127i = ConnectionTracker.a();
        this.f11128j = 5000L;
        this.f11129k = 300000L;
        this.f11130l = executor;
    }

    @Override // com.google.android.gms.common.internal.GmsClientSupervisor
    protected final void d(zzo zzoVar, ServiceConnection serviceConnection, String str) {
        Preconditions.j(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.f11123e) {
            try {
                zzp zzpVar = (zzp) this.f11123e.get(zzoVar);
                if (zzpVar == null) {
                    throw new IllegalStateException("Nonexistent connection status for service config: " + zzoVar.toString());
                }
                if (!zzpVar.h(serviceConnection)) {
                    throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  config=" + zzoVar.toString());
                }
                zzpVar.f(serviceConnection, str);
                if (zzpVar.i()) {
                    this.f11125g.sendMessageDelayed(this.f11125g.obtainMessage(0, zzoVar), this.f11128j);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.common.internal.GmsClientSupervisor
    protected final boolean f(zzo zzoVar, ServiceConnection serviceConnection, String str, Executor executor) {
        boolean j2;
        Preconditions.j(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.f11123e) {
            try {
                zzp zzpVar = (zzp) this.f11123e.get(zzoVar);
                if (executor == null) {
                    executor = this.f11130l;
                }
                if (zzpVar == null) {
                    zzpVar = new zzp(this, zzoVar);
                    zzpVar.d(serviceConnection, serviceConnection, str);
                    zzpVar.e(str, executor);
                    this.f11123e.put(zzoVar, zzpVar);
                } else {
                    this.f11125g.removeMessages(0, zzoVar);
                    if (zzpVar.h(serviceConnection)) {
                        throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  config=" + zzoVar.toString());
                    }
                    zzpVar.d(serviceConnection, serviceConnection, str);
                    int a2 = zzpVar.a();
                    if (a2 == 1) {
                        serviceConnection.onServiceConnected(zzpVar.b(), zzpVar.c());
                    } else if (a2 == 2) {
                        zzpVar.e(str, executor);
                    }
                }
                j2 = zzpVar.j();
            } catch (Throwable th) {
                throw th;
            }
        }
        return j2;
    }
}
