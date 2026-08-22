package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.ArrayList;

/* loaded from: classes.dex */
final class zaap extends zaav {

    /* renamed from: h, reason: collision with root package name */
    private final ArrayList f10682h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ zaaw f10683i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaap(zaaw zaawVar, ArrayList arrayList) {
        super(zaawVar, null);
        this.f10683i = zaawVar;
        this.f10682h = arrayList;
    }

    @Override // com.google.android.gms.common.api.internal.zaav
    public final void a() {
        zabi zabiVar;
        IAccountAccessor iAccountAccessor;
        zabi zabiVar2;
        zaaw zaawVar = this.f10683i;
        zabiVar = zaawVar.f10688a;
        zabiVar.f10753n.f10732n = zaaw.x(zaawVar);
        ArrayList arrayList = this.f10682h;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            Api.Client client = (Api.Client) arrayList.get(i2);
            zaaw zaawVar2 = this.f10683i;
            iAccountAccessor = zaawVar2.f10702o;
            zabiVar2 = zaawVar2.f10688a;
            client.j(iAccountAccessor, zabiVar2.f10753n.f10732n);
        }
    }
}
