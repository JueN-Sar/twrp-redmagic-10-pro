package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReference;

@KeepForSdk
/* loaded from: classes.dex */
public class TaskQueue {

    /* renamed from: b, reason: collision with root package name */
    private boolean f15973b;

    /* renamed from: a, reason: collision with root package name */
    private final Object f15972a = new Object();

    /* renamed from: c, reason: collision with root package name */
    private final Queue f15974c = new ArrayDeque();

    /* renamed from: d, reason: collision with root package name */
    private final AtomicReference f15975d = new AtomicReference();

    /* JADX INFO: Access modifiers changed from: private */
    public final void d() {
        synchronized (this.f15972a) {
            try {
                if (this.f15974c.isEmpty()) {
                    this.f15973b = false;
                } else {
                    zzv zzvVar = (zzv) this.f15974c.remove();
                    e(zzvVar.f16047a, zzvVar.f16048b);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final void e(Executor executor, final Runnable runnable) {
        try {
            executor.execute(new Runnable() { // from class: com.google.mlkit.common.sdkinternal.zzt
                @Override // java.lang.Runnable
                public final void run() {
                    zzx zzxVar = new zzx(TaskQueue.this, null);
                    try {
                        runnable.run();
                        zzxVar.close();
                    } catch (Throwable th) {
                        try {
                            zzxVar.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
            d();
        }
    }

    public void a(Executor executor, Runnable runnable) {
        synchronized (this.f15972a) {
            try {
                if (this.f15973b) {
                    this.f15974c.add(new zzv(executor, runnable, null));
                } else {
                    this.f15973b = true;
                    e(executor, runnable);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
