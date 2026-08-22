package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import java.util.Map;

/* loaded from: classes.dex */
final class zabt implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ ConnectionResult f10778c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ zabu f10779h;

    zabt(zabu zabuVar, ConnectionResult connectionResult) {
        this.f10779h = zabuVar;
        this.f10778c = connectionResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Map map;
        ApiKey apiKey;
        Api.Client client;
        Api.Client client2;
        Api.Client client3;
        Api.Client client4;
        zabu zabuVar = this.f10779h;
        map = zabuVar.f10785f.f10593p;
        apiKey = zabuVar.f10781b;
        zabq zabqVar = (zabq) map.get(apiKey);
        if (zabqVar == null) {
            return;
        }
        if (!this.f10778c.W()) {
            zabqVar.G(this.f10778c, null);
            return;
        }
        this.f10779h.f10784e = true;
        client = this.f10779h.f10780a;
        if (client.g()) {
            this.f10779h.i();
            return;
        }
        try {
            zabu zabuVar2 = this.f10779h;
            client3 = zabuVar2.f10780a;
            client4 = zabuVar2.f10780a;
            client3.j(null, client4.i());
        } catch (SecurityException e2) {
            Log.e("GoogleApiManager", "Failed to get service from broker. ", e2);
            client2 = this.f10779h.f10780a;
            client2.disconnect("Failed to get service from broker.");
            zabqVar.G(new ConnectionResult(10), null);
        }
    }
}
