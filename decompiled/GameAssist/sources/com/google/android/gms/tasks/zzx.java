package com.google.android.gms.tasks;

import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public final /* synthetic */ class zzx implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f13732c;

    @Override // java.lang.Runnable
    public final void run() {
        this.f13732c.d(new TimeoutException());
    }
}
