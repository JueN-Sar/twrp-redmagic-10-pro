package com.google.android.gms.security;

import android.content.Context;
import android.os.AsyncTask;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

/* loaded from: classes.dex */
final class zza extends AsyncTask {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Context f13638a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ ProviderInstaller.ProviderInstallListener f13639b;

    @Override // android.os.AsyncTask
    protected final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
        try {
            ProviderInstaller.a(this.f13638a);
            return 0;
        } catch (GooglePlayServicesNotAvailableException e2) {
            return Integer.valueOf(e2.errorCode);
        } catch (GooglePlayServicesRepairableException e3) {
            return Integer.valueOf(e3.a());
        }
    }

    @Override // android.os.AsyncTask
    protected final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        GoogleApiAvailabilityLight googleApiAvailabilityLight;
        Integer num = (Integer) obj;
        if (num.intValue() == 0) {
            this.f13639b.a();
            return;
        }
        Context context = this.f13638a;
        googleApiAvailabilityLight = ProviderInstaller.f13634a;
        this.f13639b.b(num.intValue(), googleApiAvailabilityLight.d(context, num.intValue(), "pi"));
    }
}
