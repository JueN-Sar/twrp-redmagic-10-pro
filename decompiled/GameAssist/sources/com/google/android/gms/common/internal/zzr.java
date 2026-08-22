package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.HashMap;

/* loaded from: classes.dex */
final class zzr implements Handler.Callback {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ zzs f11122c;

    /* synthetic */ zzr(zzs zzsVar, zzq zzqVar) {
        this.f11122c = zzsVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        HashMap hashMap;
        HashMap hashMap2;
        HashMap hashMap3;
        HashMap hashMap4;
        HashMap hashMap5;
        int i2 = message.what;
        if (i2 == 0) {
            hashMap = this.f11122c.f11123e;
            synchronized (hashMap) {
                try {
                    zzo zzoVar = (zzo) message.obj;
                    hashMap2 = this.f11122c.f11123e;
                    zzp zzpVar = (zzp) hashMap2.get(zzoVar);
                    if (zzpVar != null && zzpVar.i()) {
                        if (zzpVar.j()) {
                            zzpVar.g("GmsClientSupervisor");
                        }
                        hashMap3 = this.f11122c.f11123e;
                        hashMap3.remove(zzoVar);
                    }
                } finally {
                }
            }
            return true;
        }
        if (i2 != 1) {
            return false;
        }
        hashMap4 = this.f11122c.f11123e;
        synchronized (hashMap4) {
            try {
                zzo zzoVar2 = (zzo) message.obj;
                hashMap5 = this.f11122c.f11123e;
                zzp zzpVar2 = (zzp) hashMap5.get(zzoVar2);
                if (zzpVar2 != null && zzpVar2.a() == 3) {
                    Log.e("GmsClientSupervisor", "Timeout waiting for ServiceConnection callback " + String.valueOf(zzoVar2), new Exception());
                    ComponentName b2 = zzpVar2.b();
                    if (b2 == null) {
                        b2 = zzoVar2.a();
                    }
                    if (b2 == null) {
                        String c2 = zzoVar2.c();
                        Preconditions.i(c2);
                        b2 = new ComponentName(c2, "unknown");
                    }
                    zzpVar2.onServiceDisconnected(b2);
                }
            } finally {
            }
        }
        return true;
    }
}
