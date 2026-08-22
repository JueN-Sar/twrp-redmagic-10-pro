package com.google.android.gms.common.api.internal;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailabilityLight;

/* loaded from: classes.dex */
final class zaak implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ zaaw f10673c;

    zaak(zaaw zaawVar) {
        this.f10673c = zaawVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        GoogleApiAvailabilityLight googleApiAvailabilityLight;
        Context context;
        zaaw zaawVar = this.f10673c;
        googleApiAvailabilityLight = zaawVar.f10691d;
        context = zaawVar.f10690c;
        googleApiAvailabilityLight.a(context);
    }
}
