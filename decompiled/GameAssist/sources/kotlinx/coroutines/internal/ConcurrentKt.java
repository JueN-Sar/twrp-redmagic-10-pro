package kotlinx.coroutines.internal;

import java.lang.reflect.Method;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public final class ConcurrentKt {

    /* renamed from: a, reason: collision with root package name */
    private static final Method f19344a;

    static {
        Method method;
        try {
            method = ScheduledThreadPoolExecutor.class.getMethod("setRemoveOnCancelPolicy", Boolean.TYPE);
        } catch (Throwable unused) {
            method = null;
        }
        f19344a = method;
    }
}
