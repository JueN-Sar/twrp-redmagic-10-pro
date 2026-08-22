package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.logging.Logging;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
class SafeLoggingExecutor implements Executor {

    /* renamed from: c, reason: collision with root package name */
    private final Executor f10223c;

    static class SafeLoggingRunnable implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        private final Runnable f10224c;

        SafeLoggingRunnable(Runnable runnable) {
            this.f10224c = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f10224c.run();
            } catch (Exception e2) {
                Logging.c("Executor", "Background execution failure.", e2);
            }
        }
    }

    SafeLoggingExecutor(Executor executor) {
        this.f10223c = executor;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.f10223c.execute(new SafeLoggingRunnable(runnable));
    }
}
