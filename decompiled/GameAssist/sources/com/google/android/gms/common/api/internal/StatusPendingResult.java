package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

@KeepForSdk
/* loaded from: classes.dex */
public class StatusPendingResult extends BasePendingResult<Status> {
    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final /* bridge */ /* synthetic */ Result f(Status status) {
        return status;
    }
}
