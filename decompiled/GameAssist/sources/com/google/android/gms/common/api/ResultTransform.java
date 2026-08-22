package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;

/* loaded from: classes.dex */
public abstract class ResultTransform<R extends Result, S extends Result> {
    public Status a(Status status) {
        return status;
    }

    public abstract PendingResult b(Result result);
}
