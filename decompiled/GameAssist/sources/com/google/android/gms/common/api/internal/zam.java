package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes.dex */
final class zam {

    /* renamed from: a, reason: collision with root package name */
    private final int f10853a;

    /* renamed from: b, reason: collision with root package name */
    private final ConnectionResult f10854b;

    zam(ConnectionResult connectionResult, int i2) {
        Preconditions.i(connectionResult);
        this.f10854b = connectionResult;
        this.f10853a = i2;
    }

    final int a() {
        return this.f10853a;
    }

    final ConnectionResult b() {
        return this.f10854b;
    }
}
