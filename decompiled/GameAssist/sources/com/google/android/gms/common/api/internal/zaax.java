package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.BaseImplementation;
import java.util.Collections;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class zaax implements zabf {

    /* renamed from: a, reason: collision with root package name */
    private final zabi f10707a;

    public zaax(zabi zabiVar) {
        this.f10707a = zabiVar;
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void a(Bundle bundle) {
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void b() {
        Iterator it = this.f10707a.f10745f.values().iterator();
        while (it.hasNext()) {
            ((Api.Client) it.next()).disconnect();
        }
        this.f10707a.f10753n.f10732n = Collections.emptySet();
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void c() {
        this.f10707a.k();
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void d(ConnectionResult connectionResult, Api api, boolean z) {
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void e(int i2) {
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final boolean f() {
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final BaseImplementation.ApiMethodImpl g(BaseImplementation.ApiMethodImpl apiMethodImpl) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
