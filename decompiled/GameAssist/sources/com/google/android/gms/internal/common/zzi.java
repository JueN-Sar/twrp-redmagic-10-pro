package com.google.android.gms.internal.common;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes.dex */
public class zzi extends Handler {

    /* renamed from: a, reason: collision with root package name */
    private final Looper f11396a;

    public zzi(Looper looper) {
        super(looper);
        this.f11396a = Looper.getMainLooper();
    }

    public zzi(Looper looper, Handler.Callback callback) {
        super(looper, callback);
        this.f11396a = Looper.getMainLooper();
    }
}
