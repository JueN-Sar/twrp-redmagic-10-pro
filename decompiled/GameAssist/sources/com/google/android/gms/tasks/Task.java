package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public abstract class Task<TResult> {
    public Task a(Executor executor, OnCanceledListener onCanceledListener) {
        throw new UnsupportedOperationException("addOnCanceledListener is not implemented");
    }

    public Task b(OnCompleteListener onCompleteListener) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }

    public Task c(Executor executor, OnCompleteListener onCompleteListener) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }

    public abstract Task d(OnFailureListener onFailureListener);

    public abstract Task e(Executor executor, OnFailureListener onFailureListener);

    public abstract Task f(OnSuccessListener onSuccessListener);

    public abstract Task g(Executor executor, OnSuccessListener onSuccessListener);

    public abstract Exception h();

    public abstract Object i();

    public abstract boolean j();

    public abstract boolean k();

    public abstract boolean l();

    public Task m(SuccessContinuation successContinuation) {
        throw new UnsupportedOperationException("onSuccessTask is not implemented");
    }
}
