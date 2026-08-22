package com.google.android.gms.common.api;

import android.app.Activity;
import android.content.IntentSender;
import android.util.Log;
import com.google.android.gms.common.api.Result;

/* loaded from: classes.dex */
public abstract class ResolvingResultCallbacks<R extends Result> extends ResultCallbacks<R> {

    /* renamed from: a, reason: collision with root package name */
    private final Activity f10537a;

    /* renamed from: b, reason: collision with root package name */
    private final int f10538b;

    @Override // com.google.android.gms.common.api.ResultCallbacks
    public final void b(Status status) {
        if (!status.T()) {
            d(status);
            return;
        }
        try {
            status.a0(this.f10537a, this.f10538b);
        } catch (IntentSender.SendIntentException e2) {
            Log.e("ResolvingResultCallback", "Failed to start resolution", e2);
            d(new Status(8));
        }
    }

    public abstract void d(Status status);
}
