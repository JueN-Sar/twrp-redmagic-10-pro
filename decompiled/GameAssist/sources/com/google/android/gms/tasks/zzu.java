package com.google.android.gms.tasks;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzu implements Executor {

    /* renamed from: c, reason: collision with root package name */
    private final Handler f13724c = new com.google.android.gms.internal.tasks.zza(Looper.getMainLooper());

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.f13724c.post(runnable);
    }
}
