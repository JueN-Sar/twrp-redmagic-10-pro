package kotlinx.coroutines.scheduling;

import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.internal.SystemPropsKt;
import kotlinx.coroutines.internal.SystemPropsKt__SystemProps_commonKt;

@Metadata
/* loaded from: classes2.dex */
public final class DefaultIoScheduler extends ExecutorCoroutineDispatcher implements Executor {

    /* renamed from: j, reason: collision with root package name */
    public static final DefaultIoScheduler f19444j = new DefaultIoScheduler();

    /* renamed from: k, reason: collision with root package name */
    private static final CoroutineDispatcher f19445k;

    static {
        int a2;
        int d2;
        UnlimitedIoScheduler unlimitedIoScheduler = UnlimitedIoScheduler.f19471i;
        a2 = RangesKt___RangesKt.a(64, SystemPropsKt.a());
        d2 = SystemPropsKt__SystemProps_commonKt.d("kotlinx.coroutines.io.parallelism", a2, 0, 0, 12, null);
        f19445k = unlimitedIoScheduler.m0(d2);
    }

    private DefaultIoScheduler() {
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new IllegalStateException("Cannot be invoked on Dispatchers.IO".toString());
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        j0(EmptyCoroutineContext.INSTANCE, runnable);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void j0(CoroutineContext coroutineContext, Runnable runnable) {
        f19445k.j0(coroutineContext, runnable);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void k0(CoroutineContext coroutineContext, Runnable runnable) {
        f19445k.k0(coroutineContext, runnable);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        return "Dispatchers.IO";
    }
}
