package cn.nubia.gameassist.common;

import com.zte.gameassist.utils.GaLog;
import java.lang.Thread;

/* loaded from: classes.dex */
public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {

    /* renamed from: a, reason: collision with root package name */
    private final Thread.UncaughtExceptionHandler f6144a = Thread.getDefaultUncaughtExceptionHandler();

    public void a() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        GaLog.d("GlobalExceptionHandler", "an unexpected error occurred: " + th.getMessage(), th);
        this.f6144a.uncaughtException(thread, th);
    }
}
