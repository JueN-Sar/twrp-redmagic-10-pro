package com.google.android.gms.common.internal;

import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
final class zzj {

    /* renamed from: a, reason: collision with root package name */
    static final ExecutorService f11104a;

    static {
        com.google.android.gms.internal.common.zzh.a();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new NamedThreadFactory("CallbackExecutor"));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        f11104a = Executors.unconfigurableExecutorService(threadPoolExecutor);
    }
}
