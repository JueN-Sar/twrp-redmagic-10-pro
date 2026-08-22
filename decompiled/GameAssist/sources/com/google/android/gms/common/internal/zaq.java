package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;

/* loaded from: classes.dex */
final class zaq implements PendingResultUtil.ResultConverter {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Response f11074a;

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* bridge */ /* synthetic */ Object a(Result result) {
        this.f11074a.h(result);
        return this.f11074a;
    }
}
