package com.google.android.gms.common.api.internal;

import android.app.Activity;
import androidx.annotation.VisibleForTesting;
import androidx.collection.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes.dex */
public final class zaae extends zap {

    /* renamed from: k, reason: collision with root package name */
    private final ArraySet f10664k;

    /* renamed from: l, reason: collision with root package name */
    private final GoogleApiManager f10665l;

    @VisibleForTesting
    zaae(LifecycleFragment lifecycleFragment, GoogleApiManager googleApiManager, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment, googleApiAvailability);
        this.f10664k = new ArraySet();
        this.f10665l = googleApiManager;
        this.mLifecycleFragment.b("ConnectionlessLifecycleHelper", this);
    }

    public static void j(Activity activity, GoogleApiManager googleApiManager, ApiKey apiKey) {
        LifecycleFragment fragment = LifecycleCallback.getFragment(activity);
        zaae zaaeVar = (zaae) fragment.c("ConnectionlessLifecycleHelper", zaae.class);
        if (zaaeVar == null) {
            zaaeVar = new zaae(fragment, googleApiManager, GoogleApiAvailability.q());
        }
        Preconditions.j(apiKey, "ApiKey cannot be null");
        zaaeVar.f10664k.add(apiKey);
        googleApiManager.a(zaaeVar);
    }

    private final void k() {
        if (this.f10664k.isEmpty()) {
            return;
        }
        this.f10665l.a(this);
    }

    @Override // com.google.android.gms.common.api.internal.zap
    protected final void b(ConnectionResult connectionResult, int i2) {
        this.f10665l.E(connectionResult, i2);
    }

    @Override // com.google.android.gms.common.api.internal.zap
    protected final void c() {
        this.f10665l.F();
    }

    final ArraySet i() {
        return this.f10664k;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onResume() {
        super.onResume();
        k();
    }

    @Override // com.google.android.gms.common.api.internal.zap, com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStart() {
        super.onStart();
        k();
    }

    @Override // com.google.android.gms.common.api.internal.zap, com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStop() {
        super.onStop();
        this.f10665l.b(this);
    }
}
