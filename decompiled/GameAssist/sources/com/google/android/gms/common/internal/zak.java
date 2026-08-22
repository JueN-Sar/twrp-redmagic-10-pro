package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public final class zak implements Handler.Callback {

    /* renamed from: c, reason: collision with root package name */
    private final zaj f11061c;

    /* renamed from: h, reason: collision with root package name */
    private final ArrayList f11062h;

    /* renamed from: i, reason: collision with root package name */
    private final ArrayList f11063i;

    /* renamed from: j, reason: collision with root package name */
    private volatile boolean f11064j;

    /* renamed from: k, reason: collision with root package name */
    private final AtomicInteger f11065k;

    /* renamed from: l, reason: collision with root package name */
    private boolean f11066l;

    /* renamed from: m, reason: collision with root package name */
    private final Handler f11067m;

    /* renamed from: n, reason: collision with root package name */
    private final Object f11068n;

    @VisibleForTesting
    final ArrayList zaa;

    public final void a() {
        this.f11064j = false;
        this.f11065k.incrementAndGet();
    }

    public final void b() {
        this.f11064j = true;
    }

    public final void c(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.i(onConnectionFailedListener);
        synchronized (this.f11068n) {
            try {
                if (!this.f11063i.remove(onConnectionFailedListener)) {
                    Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + String.valueOf(onConnectionFailedListener) + " not found");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int i2 = message.what;
        if (i2 != 1) {
            Log.wtf("GmsClientEvents", "Don't know how to handle message: " + i2, new Exception());
            return false;
        }
        GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) message.obj;
        synchronized (this.f11068n) {
            try {
                if (this.f11064j && this.f11061c.isConnected() && this.f11062h.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return true;
    }

    @VisibleForTesting
    public final void zac(ConnectionResult connectionResult) {
        Preconditions.e(this.f11067m, "onConnectionFailure must only be called on the Handler thread");
        this.f11067m.removeMessages(1);
        synchronized (this.f11068n) {
            try {
                ArrayList arrayList = new ArrayList(this.f11063i);
                int i2 = this.f11065k.get();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = (GoogleApiClient.OnConnectionFailedListener) it.next();
                    if (this.f11064j && this.f11065k.get() == i2) {
                        if (this.f11063i.contains(onConnectionFailedListener)) {
                            onConnectionFailedListener.onConnectionFailed(connectionResult);
                        }
                    }
                    return;
                }
            } finally {
            }
        }
    }

    @VisibleForTesting
    public final void zad(@Nullable Bundle bundle) {
        Preconditions.e(this.f11067m, "onConnectionSuccess must only be called on the Handler thread");
        synchronized (this.f11068n) {
            try {
                Preconditions.l(!this.f11066l);
                this.f11067m.removeMessages(1);
                this.f11066l = true;
                Preconditions.l(this.zaa.isEmpty());
                ArrayList arrayList = new ArrayList(this.f11062h);
                int i2 = this.f11065k.get();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) it.next();
                    if (!this.f11064j || !this.f11061c.isConnected() || this.f11065k.get() != i2) {
                        break;
                    } else if (!this.zaa.contains(connectionCallbacks)) {
                        connectionCallbacks.onConnected(bundle);
                    }
                }
                this.zaa.clear();
                this.f11066l = false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @VisibleForTesting
    public final void zae(int i2) {
        Preconditions.e(this.f11067m, "onUnintentionalDisconnection must only be called on the Handler thread");
        this.f11067m.removeMessages(1);
        synchronized (this.f11068n) {
            try {
                this.f11066l = true;
                ArrayList arrayList = new ArrayList(this.f11062h);
                int i3 = this.f11065k.get();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) it.next();
                    if (!this.f11064j || this.f11065k.get() != i3) {
                        break;
                    } else if (this.f11062h.contains(connectionCallbacks)) {
                        connectionCallbacks.onConnectionSuspended(i2);
                    }
                }
                this.zaa.clear();
                this.f11066l = false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
