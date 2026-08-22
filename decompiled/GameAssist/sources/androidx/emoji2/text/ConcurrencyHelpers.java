package androidx.emoji2.text;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
class ConcurrencyHelpers {

    @RequiresApi
    static class Handler28Impl {
        @DoNotInline
        public static Handler a(Looper looper) {
            return Handler.createAsync(looper);
        }
    }

    static ThreadPoolExecutor b(final String str) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new ThreadFactory() { // from class: androidx.emoji2.text.a
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread c2;
                c2 = ConcurrencyHelpers.c(str, runnable);
                return c2;
            }
        });
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Thread c(String str, Runnable runnable) {
        Thread thread = new Thread(runnable, str);
        thread.setPriority(10);
        return thread;
    }

    static Handler d() {
        return Handler28Impl.a(Looper.getMainLooper());
    }
}
