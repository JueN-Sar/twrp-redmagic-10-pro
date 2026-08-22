package com.google.android.gms.common.util.concurrent;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.common.zzi;
import java.util.concurrent.Executor;

@KeepForSdk
/* loaded from: classes.dex */
public class HandlerExecutor implements Executor {

    /* renamed from: c, reason: collision with root package name */
    private final Handler f11278c;

    public HandlerExecutor(Looper looper) {
        this.f11278c = new zzi(looper);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.f11278c.post(runnable);
    }
}
