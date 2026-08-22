package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
final class zabu implements BaseGmsClient.ConnectionProgressReportCallbacks, zacs {

    /* renamed from: a, reason: collision with root package name */
    private final Api.Client f10780a;

    /* renamed from: b, reason: collision with root package name */
    private final ApiKey f10781b;

    /* renamed from: c, reason: collision with root package name */
    private IAccountAccessor f10782c = null;

    /* renamed from: d, reason: collision with root package name */
    private Set f10783d = null;

    /* renamed from: e, reason: collision with root package name */
    private boolean f10784e = false;

    /* renamed from: f, reason: collision with root package name */
    final /* synthetic */ GoogleApiManager f10785f;

    public zabu(GoogleApiManager googleApiManager, Api.Client client, ApiKey apiKey) {
        this.f10785f = googleApiManager;
        this.f10780a = client;
        this.f10781b = apiKey;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void i() {
        IAccountAccessor iAccountAccessor;
        if (!this.f10784e || (iAccountAccessor = this.f10782c) == null) {
            return;
        }
        this.f10780a.j(iAccountAccessor, this.f10783d);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
    public final void a(ConnectionResult connectionResult) {
        this.f10785f.t.post(new zabt(this, connectionResult));
    }

    @Override // com.google.android.gms.common.api.internal.zacs
    public final void b(IAccountAccessor iAccountAccessor, Set set) {
        if (iAccountAccessor == null || set == null) {
            Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
            c(new ConnectionResult(4));
        } else {
            this.f10782c = iAccountAccessor;
            this.f10783d = set;
            i();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zacs
    public final void c(ConnectionResult connectionResult) {
        Map map;
        map = this.f10785f.f10593p;
        zabq zabqVar = (zabq) map.get(this.f10781b);
        if (zabqVar != null) {
            zabqVar.H(connectionResult);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zacs
    public final void d(int i2) {
        Map map;
        boolean z;
        map = this.f10785f.f10593p;
        zabq zabqVar = (zabq) map.get(this.f10781b);
        if (zabqVar != null) {
            z = zabqVar.f10771i;
            if (z) {
                zabqVar.H(new ConnectionResult(17));
            } else {
                zabqVar.onConnectionSuspended(i2);
            }
        }
    }
}
