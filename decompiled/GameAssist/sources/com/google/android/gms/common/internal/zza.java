package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes.dex */
abstract class zza extends zzc {

    /* renamed from: d, reason: collision with root package name */
    public final int f11089d;

    /* renamed from: e, reason: collision with root package name */
    public final Bundle f11090e;

    /* renamed from: f, reason: collision with root package name */
    final /* synthetic */ BaseGmsClient f11091f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    protected zza(BaseGmsClient baseGmsClient, int i2, Bundle bundle) {
        super(baseGmsClient, Boolean.TRUE);
        this.f11091f = baseGmsClient;
        this.f11089d = i2;
        this.f11090e = bundle;
    }

    @Override // com.google.android.gms.common.internal.zzc
    protected final /* bridge */ /* synthetic */ void a(Object obj) {
        if (this.f11089d != 0) {
            this.f11091f.i0(1, null);
            Bundle bundle = this.f11090e;
            f(new ConnectionResult(this.f11089d, bundle != null ? (PendingIntent) bundle.getParcelable("pendingIntent") : null));
        } else {
            if (g()) {
                return;
            }
            this.f11091f.i0(1, null);
            f(new ConnectionResult(8, null));
        }
    }

    @Override // com.google.android.gms.common.internal.zzc
    protected final void b() {
    }

    protected abstract void f(ConnectionResult connectionResult);

    protected abstract boolean g();
}
