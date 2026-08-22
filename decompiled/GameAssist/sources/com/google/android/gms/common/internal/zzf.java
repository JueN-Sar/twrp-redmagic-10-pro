package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* loaded from: classes.dex */
public final class zzf extends zza {

    /* renamed from: g, reason: collision with root package name */
    public final IBinder f11101g;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ BaseGmsClient f11102h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzf(BaseGmsClient baseGmsClient, int i2, IBinder iBinder, Bundle bundle) {
        super(baseGmsClient, i2, bundle);
        this.f11102h = baseGmsClient;
        this.f11101g = iBinder;
    }

    @Override // com.google.android.gms.common.internal.zza
    protected final void f(ConnectionResult connectionResult) {
        if (this.f11102h.z != null) {
            this.f11102h.z.onConnectionFailed(connectionResult);
        }
        this.f11102h.M(connectionResult);
    }

    @Override // com.google.android.gms.common.internal.zza
    protected final boolean g() {
        BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks;
        BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks2;
        try {
            IBinder iBinder = this.f11101g;
            Preconditions.i(iBinder);
            String interfaceDescriptor = iBinder.getInterfaceDescriptor();
            if (!this.f11102h.F().equals(interfaceDescriptor)) {
                Log.w("GmsClient", "service descriptor mismatch: " + this.f11102h.F() + " vs. " + interfaceDescriptor);
                return false;
            }
            IInterface t = this.f11102h.t(this.f11101g);
            if (t == null || !(BaseGmsClient.g0(this.f11102h, 2, 4, t) || BaseGmsClient.g0(this.f11102h, 3, 4, t))) {
                return false;
            }
            this.f11102h.D = null;
            BaseGmsClient baseGmsClient = this.f11102h;
            Bundle y = baseGmsClient.y();
            baseConnectionCallbacks = baseGmsClient.y;
            if (baseConnectionCallbacks == null) {
                return true;
            }
            baseConnectionCallbacks2 = this.f11102h.y;
            baseConnectionCallbacks2.onConnected(y);
            return true;
        } catch (RemoteException unused) {
            Log.w("GmsClient", "service probably died");
            return false;
        }
    }
}
