package com.google.android.gms.common.api;

import android.util.Log;
import com.google.android.gms.common.api.Result;

/* loaded from: classes.dex */
public abstract class ResultCallbacks<R extends Result> implements ResultCallback<R> {
    @Override // com.google.android.gms.common.api.ResultCallback
    public final void a(Result result) {
        Status a2 = result.a();
        if (a2.Y()) {
            c(result);
            return;
        }
        b(a2);
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e2) {
                Log.w("ResultCallbacks", "Unable to release ".concat(String.valueOf(result)), e2);
            }
        }
    }

    public abstract void b(Status status);

    public abstract void c(Result result);
}
