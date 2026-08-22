package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.internal.BaseGmsClient;

/* loaded from: classes.dex */
final class zabp implements BaseGmsClient.SignOutCallbacks {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ zabq f10762a;

    zabp(zabq zabqVar) {
        this.f10762a = zabqVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.SignOutCallbacks
    public final void a() {
        this.f10762a.f10775m.t.post(new zabo(this));
    }
}
