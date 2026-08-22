package com.google.mlkit.common.sdkinternal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.mlkit.common.MlKitException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

@KeepForSdk
/* loaded from: classes.dex */
public class MLTaskExecutor {

    /* renamed from: b, reason: collision with root package name */
    private static final Object f15936b = new Object();

    /* renamed from: c, reason: collision with root package name */
    private static MLTaskExecutor f15937c;

    /* renamed from: a, reason: collision with root package name */
    private final Handler f15938a;

    private MLTaskExecutor(Looper looper) {
        this.f15938a = new com.google.android.gms.internal.mlkit_common.zza(looper);
    }

    public static MLTaskExecutor b() {
        MLTaskExecutor mLTaskExecutor;
        synchronized (f15936b) {
            try {
                if (f15937c == null) {
                    HandlerThread handlerThread = new HandlerThread("MLHandler", 9);
                    handlerThread.start();
                    f15937c = new MLTaskExecutor(handlerThread.getLooper());
                }
                mLTaskExecutor = f15937c;
            } catch (Throwable th) {
                throw th;
            }
        }
        return mLTaskExecutor;
    }

    public static Executor e() {
        return zzh.zza;
    }

    public Handler a() {
        return this.f15938a;
    }

    public Task c(final Callable callable) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        d(new Runnable() { // from class: com.google.mlkit.common.sdkinternal.zzf
            @Override // java.lang.Runnable
            public final void run() {
                Callable callable2 = callable;
                TaskCompletionSource taskCompletionSource2 = taskCompletionSource;
                try {
                    taskCompletionSource2.c(callable2.call());
                } catch (MlKitException e2) {
                    taskCompletionSource2.b(e2);
                } catch (Exception e3) {
                    taskCompletionSource2.b(new MlKitException("Internal error has occurred when executing ML Kit tasks", 13, e3));
                }
            }
        });
        return taskCompletionSource.a();
    }

    public void d(Runnable runnable) {
        e().execute(runnable);
    }
}
