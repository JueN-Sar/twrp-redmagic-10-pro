package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes.dex */
final class zaam extends zabg {

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ ConnectionResult f10677b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ zaao f10678c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zaam(zaao zaaoVar, zabf zabfVar, ConnectionResult connectionResult) {
        super(zabfVar);
        this.f10678c = zaaoVar;
        this.f10677b = connectionResult;
    }

    @Override // com.google.android.gms.common.api.internal.zabg
    public final void a() {
        this.f10678c.f10681i.k(this.f10677b);
    }
}
