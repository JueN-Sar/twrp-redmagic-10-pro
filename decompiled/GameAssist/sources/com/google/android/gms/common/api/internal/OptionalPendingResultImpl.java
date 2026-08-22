package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import java.util.concurrent.TimeUnit;

@KeepForSdk
/* loaded from: classes.dex */
public final class OptionalPendingResultImpl<R extends Result> extends OptionalPendingResult<R> {

    /* renamed from: a, reason: collision with root package name */
    private final BasePendingResult f10621a;

    @Override // com.google.android.gms.common.api.PendingResult
    public final void b(PendingResult.StatusListener statusListener) {
        this.f10621a.b(statusListener);
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final Result c(long j2, TimeUnit timeUnit) {
        return this.f10621a.c(j2, timeUnit);
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void d() {
        this.f10621a.d();
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void e(ResultCallback resultCallback) {
        this.f10621a.e(resultCallback);
    }
}
