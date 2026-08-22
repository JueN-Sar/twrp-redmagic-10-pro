package cn.nubia.gameassist.utils;

import com.zte.gameassist.utils.GaLog;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes.dex */
public class ThreadPoolUtils {

    /* renamed from: a, reason: collision with root package name */
    private static volatile ExecutorService f7692a;

    private static ExecutorService a() {
        if (f7692a == null) {
            f7692a = Executors.newCachedThreadPool();
        }
        return f7692a;
    }

    public static void b(Runnable runnable) {
        try {
            a().execute(runnable);
        } catch (RejectedExecutionException e2) {
            GaLog.b("ThreadPoolUtil", "ThreadPool name = " + runnable.getClass() + e2);
        }
    }
}
