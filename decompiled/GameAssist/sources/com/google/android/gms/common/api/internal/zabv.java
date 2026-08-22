package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* loaded from: classes.dex */
public final class zabv extends zaag {

    /* renamed from: c, reason: collision with root package name */
    private final GoogleApi f10786c;

    public zabv(GoogleApi googleApi) {
        super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
        this.f10786c = googleApi;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final BaseImplementation.ApiMethodImpl g(BaseImplementation.ApiMethodImpl apiMethodImpl) {
        return this.f10786c.h(apiMethodImpl);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final Looper h() {
        return this.f10786c.k();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void j(zada zadaVar) {
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void k(zada zadaVar) {
    }
}
