package com.google.android.gms.internal.tasks;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes.dex */
public final class zza extends Handler {

    /* renamed from: a, reason: collision with root package name */
    private final Looper f13633a;

    public zza(Looper looper) {
        super(looper);
        this.f13633a = Looper.getMainLooper();
    }
}
