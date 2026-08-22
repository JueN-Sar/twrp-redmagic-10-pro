package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BasePendingResult;

/* loaded from: classes.dex */
final class zag<R extends Result> extends BasePendingResult<R> {

    /* renamed from: q, reason: collision with root package name */
    private final Result f10883q;

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final Result f(Status status) {
        return this.f10883q;
    }
}
