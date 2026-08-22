package com.google.mlkit.common.sdkinternal;

import android.os.Handler;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
enum zzh implements Executor {
    INSTANCE;

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        Handler handler;
        handler = MLTaskExecutor.b().f15938a;
        handler.post(runnable);
    }
}
