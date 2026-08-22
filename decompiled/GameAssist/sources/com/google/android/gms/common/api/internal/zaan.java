package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* loaded from: classes.dex */
final class zaan extends zabg {

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ BaseGmsClient.ConnectionProgressReportCallbacks f10679b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zaan(zaao zaaoVar, zabf zabfVar, BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        super(zabfVar);
        this.f10679b = connectionProgressReportCallbacks;
    }

    @Override // com.google.android.gms.common.api.internal.zabg
    public final void a() {
        this.f10679b.a(new ConnectionResult(16, null));
    }
}
