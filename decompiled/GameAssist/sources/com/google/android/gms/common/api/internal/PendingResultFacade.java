package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import java.util.concurrent.TimeUnit;

@KeepForSdk
/* loaded from: classes.dex */
public abstract class PendingResultFacade<A extends Result, B extends Result> extends PendingResult<B> {
    @Override // com.google.android.gms.common.api.PendingResult
    public final void b(PendingResult.StatusListener statusListener) {
        throw null;
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final Result c(long j2, TimeUnit timeUnit) {
        throw null;
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void d() {
        throw null;
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void e(ResultCallback resultCallback) {
        throw null;
    }
}
