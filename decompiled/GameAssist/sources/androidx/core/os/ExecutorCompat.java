package androidx.core.os;

import android.os.Handler;
import androidx.core.util.Preconditions;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes.dex */
public final class ExecutorCompat {

    private static class HandlerExecutor implements Executor {

        /* renamed from: c, reason: collision with root package name */
        private final Handler f3110c;

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            if (this.f3110c.post((Runnable) Preconditions.h(runnable))) {
                return;
            }
            throw new RejectedExecutionException(this.f3110c + " is shutting down");
        }
    }
}
