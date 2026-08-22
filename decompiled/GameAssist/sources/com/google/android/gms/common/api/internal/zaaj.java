package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public final class zaaj implements zabf {

    /* renamed from: a, reason: collision with root package name */
    private final zabi f10671a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f10672b = false;

    public zaaj(zabi zabiVar) {
        this.f10671a = zabiVar;
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void a(Bundle bundle) {
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void b() {
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void c() {
        if (this.f10672b) {
            this.f10672b = false;
            this.f10671a.m(new zaai(this, this));
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void d(ConnectionResult connectionResult, Api api, boolean z) {
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void e(int i2) {
        this.f10671a.l(null);
        this.f10671a.f10754o.b(i2, this.f10672b);
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final boolean f() {
        if (this.f10672b) {
            return false;
        }
        Set set = this.f10671a.f10753n.u;
        if (set == null || set.isEmpty()) {
            this.f10671a.l(null);
            return true;
        }
        this.f10672b = true;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ((zada) it.next()).i();
        }
        return false;
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final BaseImplementation.ApiMethodImpl g(BaseImplementation.ApiMethodImpl apiMethodImpl) {
        try {
            this.f10671a.f10753n.v.a(apiMethodImpl);
            zabe zabeVar = this.f10671a.f10753n;
            Api.Client client = (Api.Client) zabeVar.f10731m.get(apiMethodImpl.t());
            Preconditions.j(client, "Appropriate Api was not requested.");
            if (client.isConnected() || !this.f10671a.f10746g.containsKey(apiMethodImpl.t())) {
                apiMethodImpl.v(client);
            } else {
                apiMethodImpl.x(new Status(17));
            }
        } catch (DeadObjectException unused) {
            this.f10671a.m(new zaah(this, this));
        }
        return apiMethodImpl;
    }

    final void i() {
        if (this.f10672b) {
            this.f10672b = false;
            this.f10671a.f10753n.v.b();
            f();
        }
    }
}
