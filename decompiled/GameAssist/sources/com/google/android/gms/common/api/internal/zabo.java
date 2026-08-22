package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;

/* loaded from: classes.dex */
final class zabo implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ zabp f10761c;

    zabo(zabp zabpVar) {
        this.f10761c = zabpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Api.Client client;
        Api.Client client2;
        zabq zabqVar = this.f10761c.f10762a;
        client = zabqVar.f10764b;
        client2 = zabqVar.f10764b;
        client.disconnect(client2.getClass().getName().concat(" disconnecting because it was signed out."));
    }
}
