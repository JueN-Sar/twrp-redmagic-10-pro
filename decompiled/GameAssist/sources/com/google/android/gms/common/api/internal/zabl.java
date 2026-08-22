package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.BackgroundDetector;

/* loaded from: classes.dex */
final class zabl implements BackgroundDetector.BackgroundStateChangeListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ GoogleApiManager f10757a;

    zabl(GoogleApiManager googleApiManager) {
        this.f10757a = googleApiManager;
    }

    @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
    public final void a(boolean z) {
        GoogleApiManager googleApiManager = this.f10757a;
        googleApiManager.t.sendMessage(googleApiManager.t.obtainMessage(1, Boolean.valueOf(z)));
    }
}
