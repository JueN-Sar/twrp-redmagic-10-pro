package com.google.android.gms.common.api.internal;

import android.content.Context;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
final class zaao extends zaav {

    /* renamed from: h, reason: collision with root package name */
    private final Map f10680h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ zaaw f10681i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaao(zaaw zaawVar, Map map) {
        super(zaawVar, null);
        this.f10681i = zaawVar;
        this.f10680h = map;
    }

    @Override // com.google.android.gms.common.api.internal.zaav
    public final void a() {
        GoogleApiAvailabilityLight googleApiAvailabilityLight;
        Context context;
        boolean z;
        Context context2;
        zabi zabiVar;
        com.google.android.gms.signin.zae zaeVar;
        com.google.android.gms.signin.zae zaeVar2;
        zabi zabiVar2;
        Context context3;
        boolean z2;
        googleApiAvailabilityLight = this.f10681i.f10691d;
        com.google.android.gms.common.internal.zal zalVar = new com.google.android.gms.common.internal.zal(googleApiAvailabilityLight);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Api.Client client : this.f10680h.keySet()) {
            if (client.e()) {
                z2 = ((zaal) this.f10680h.get(client)).f10676c;
                if (!z2) {
                    arrayList.add(client);
                }
            }
            arrayList2.add(client);
        }
        int i2 = 0;
        int i3 = -1;
        if (!arrayList.isEmpty()) {
            int size = arrayList.size();
            while (i2 < size) {
                Api.Client client2 = (Api.Client) arrayList.get(i2);
                context = this.f10681i.f10690c;
                i3 = zalVar.b(context, client2);
                i2++;
                if (i3 != 0) {
                    break;
                }
            }
        } else {
            int size2 = arrayList2.size();
            while (i2 < size2) {
                Api.Client client3 = (Api.Client) arrayList2.get(i2);
                context3 = this.f10681i.f10690c;
                i3 = zalVar.b(context3, client3);
                i2++;
                if (i3 == 0) {
                    break;
                }
            }
        }
        if (i3 != 0) {
            ConnectionResult connectionResult = new ConnectionResult(i3, null);
            zaaw zaawVar = this.f10681i;
            zabiVar2 = zaawVar.f10688a;
            zabiVar2.m(new zaam(this, zaawVar, connectionResult));
            return;
        }
        zaaw zaawVar2 = this.f10681i;
        z = zaawVar2.f10700m;
        if (z) {
            zaeVar = zaawVar2.f10698k;
            if (zaeVar != null) {
                zaeVar2 = zaawVar2.f10698k;
                zaeVar2.h();
            }
        }
        for (Api.Client client4 : this.f10680h.keySet()) {
            BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks = (BaseGmsClient.ConnectionProgressReportCallbacks) this.f10680h.get(client4);
            if (client4.e()) {
                context2 = this.f10681i.f10690c;
                if (zalVar.b(context2, client4) != 0) {
                    zaaw zaawVar3 = this.f10681i;
                    zabiVar = zaawVar3.f10688a;
                    zabiVar.m(new zaan(this, zaawVar3, connectionProgressReportCallbacks));
                }
            }
            client4.d(connectionProgressReportCallbacks);
        }
    }
}
