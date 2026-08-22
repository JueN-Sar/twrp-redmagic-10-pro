package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes.dex */
public final class zzg extends zza {

    /* renamed from: g, reason: collision with root package name */
    final /* synthetic */ BaseGmsClient f11103g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzg(BaseGmsClient baseGmsClient, int i2, Bundle bundle) {
        super(baseGmsClient, i2, null);
        this.f11103g = baseGmsClient;
    }

    @Override // com.google.android.gms.common.internal.zza
    protected final void f(ConnectionResult connectionResult) {
        if (this.f11103g.u() && BaseGmsClient.h0(this.f11103g)) {
            BaseGmsClient.d0(this.f11103g, 16);
        } else {
            this.f11103g.zzc.a(connectionResult);
            this.f11103g.M(connectionResult);
        }
    }

    @Override // com.google.android.gms.common.internal.zza
    protected final boolean g() {
        this.f11103g.zzc.a(ConnectionResult.f10484k);
        return true;
    }
}
