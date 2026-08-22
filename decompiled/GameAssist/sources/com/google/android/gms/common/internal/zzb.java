package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* loaded from: classes.dex */
final class zzb extends com.google.android.gms.internal.common.zzi {

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ BaseGmsClient f11097b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzb(BaseGmsClient baseGmsClient, Looper looper) {
        super(looper);
        this.f11097b = baseGmsClient;
    }

    private static final void a(Message message) {
        zzc zzcVar = (zzc) message.obj;
        zzcVar.b();
        zzcVar.e();
    }

    private static final boolean b(Message message) {
        int i2 = message.what;
        return i2 == 2 || i2 == 1 || i2 == 7;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks;
        BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks2;
        ConnectionResult connectionResult;
        ConnectionResult connectionResult2;
        boolean z;
        if (this.f11097b.zzd.get() != message.arg1) {
            if (b(message)) {
                a(message);
                return;
            }
            return;
        }
        int i2 = message.what;
        if ((i2 == 1 || i2 == 7 || ((i2 == 4 && !this.f11097b.u()) || message.what == 5)) && !this.f11097b.b()) {
            a(message);
            return;
        }
        int i3 = message.what;
        if (i3 == 4) {
            this.f11097b.D = new ConnectionResult(message.arg2);
            if (BaseGmsClient.h0(this.f11097b)) {
                BaseGmsClient baseGmsClient = this.f11097b;
                z = baseGmsClient.E;
                if (!z) {
                    baseGmsClient.i0(3, null);
                    return;
                }
            }
            BaseGmsClient baseGmsClient2 = this.f11097b;
            connectionResult2 = baseGmsClient2.D;
            ConnectionResult connectionResult3 = connectionResult2 != null ? baseGmsClient2.D : new ConnectionResult(8);
            this.f11097b.zzc.a(connectionResult3);
            this.f11097b.M(connectionResult3);
            return;
        }
        if (i3 == 5) {
            BaseGmsClient baseGmsClient3 = this.f11097b;
            connectionResult = baseGmsClient3.D;
            ConnectionResult connectionResult4 = connectionResult != null ? baseGmsClient3.D : new ConnectionResult(8);
            this.f11097b.zzc.a(connectionResult4);
            this.f11097b.M(connectionResult4);
            return;
        }
        if (i3 == 3) {
            Object obj = message.obj;
            ConnectionResult connectionResult5 = new ConnectionResult(message.arg2, obj instanceof PendingIntent ? (PendingIntent) obj : null);
            this.f11097b.zzc.a(connectionResult5);
            this.f11097b.M(connectionResult5);
            return;
        }
        if (i3 == 6) {
            this.f11097b.i0(5, null);
            BaseGmsClient baseGmsClient4 = this.f11097b;
            baseConnectionCallbacks = baseGmsClient4.y;
            if (baseConnectionCallbacks != null) {
                baseConnectionCallbacks2 = baseGmsClient4.y;
                baseConnectionCallbacks2.onConnectionSuspended(message.arg2);
            }
            this.f11097b.N(message.arg2);
            BaseGmsClient.g0(this.f11097b, 5, 1, null);
            return;
        }
        if (i3 == 2 && !this.f11097b.isConnected()) {
            a(message);
            return;
        }
        if (b(message)) {
            ((zzc) message.obj).c();
            return;
        }
        Log.wtf("GmsClient", "Don't know how to handle message: " + message.what, new Exception());
    }
}
