package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzav;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@KeepForSdk
/* loaded from: classes.dex */
public class MlKitThreadPool extends zzav {

    /* renamed from: h, reason: collision with root package name */
    private static final ThreadLocal f15942h = new ThreadLocal();

    /* renamed from: c, reason: collision with root package name */
    private final ThreadPoolExecutor f15943c;

    public MlKitThreadPool() {
        final ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(availableProcessors, availableProcessors, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() { // from class: com.google.mlkit.common.sdkinternal.zzj
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(final Runnable runnable) {
                return defaultThreadFactory.newThread(new Runnable() { // from class: com.google.mlkit.common.sdkinternal.zzk
                    @Override // java.lang.Runnable
                    public final void run() {
                        MlKitThreadPool.h(runnable);
                    }
                });
            }
        });
        this.f15943c = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    static /* synthetic */ void h(Runnable runnable) {
        f15942h.set(new ArrayDeque());
        runnable.run();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void i(Deque deque, Runnable runnable) {
        Preconditions.i(deque);
        deque.add(runnable);
        if (deque.size() <= 1) {
            do {
                runnable.run();
                deque.removeFirst();
                runnable = (Runnable) deque.peekFirst();
            } while (runnable != null);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzav, com.google.android.gms.internal.mlkit_common.zzx
    protected final /* synthetic */ Object a() {
        return this.f15943c;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzav
    protected final ExecutorService c() {
        return this.f15943c;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzav, java.util.concurrent.Executor
    public final void execute(final Runnable runnable) {
        Deque deque = (Deque) f15942h.get();
        if (deque == null || deque.size() > 1) {
            this.f15943c.execute(new Runnable() { // from class: com.google.mlkit.common.sdkinternal.zzi
                @Override // java.lang.Runnable
                public final void run() {
                    MlKitThreadPool.i((Deque) MlKitThreadPool.f15942h.get(), runnable);
                }
            });
        } else {
            i(deque, runnable);
        }
    }
}
