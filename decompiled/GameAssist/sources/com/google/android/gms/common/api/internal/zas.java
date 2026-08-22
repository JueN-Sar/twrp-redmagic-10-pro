package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Result;

/* loaded from: classes.dex */
final class zas {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ BasePendingResult f10863a;

    /* synthetic */ zas(BasePendingResult basePendingResult, zar zarVar) {
        this.f10863a = basePendingResult;
    }

    protected final void finalize() {
        Result result;
        result = this.f10863a.f10573h;
        BasePendingResult.o(result);
        super.finalize();
    }
}
