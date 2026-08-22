package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BasePendingResult;

/* loaded from: classes.dex */
final class zah<R extends Result> extends BasePendingResult<R> {
    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final Result f(Status status) {
        throw new UnsupportedOperationException("Creating failed results is not supported");
    }
}
