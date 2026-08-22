package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BasePendingResult;

/* loaded from: classes.dex */
final class zaf<R extends Result> extends BasePendingResult<R> {

    /* renamed from: q, reason: collision with root package name */
    private final Result f10882q;

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final Result f(Status status) {
        if (status.P() == this.f10882q.a().P()) {
            return this.f10882q;
        }
        throw new UnsupportedOperationException("Creating failed results is not supported");
    }
}
