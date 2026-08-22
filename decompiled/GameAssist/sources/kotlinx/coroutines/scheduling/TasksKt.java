package kotlinx.coroutines.scheduling;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.internal.SystemPropsKt;
import kotlinx.coroutines.internal.SystemPropsKt__SystemProps_commonKt;

@Metadata
/* loaded from: classes2.dex */
public final class TasksKt {

    /* renamed from: a, reason: collision with root package name */
    public static final long f19464a;

    /* renamed from: b, reason: collision with root package name */
    public static final int f19465b;

    /* renamed from: c, reason: collision with root package name */
    public static final int f19466c;

    /* renamed from: d, reason: collision with root package name */
    public static final long f19467d;

    /* renamed from: e, reason: collision with root package name */
    public static SchedulerTimeSource f19468e;

    /* renamed from: f, reason: collision with root package name */
    public static final TaskContext f19469f;

    /* renamed from: g, reason: collision with root package name */
    public static final TaskContext f19470g;

    static {
        long e2;
        int a2;
        int d2;
        int d3;
        long e3;
        e2 = SystemPropsKt__SystemProps_commonKt.e("kotlinx.coroutines.scheduler.resolution.ns", 100000L, 0L, 0L, 12, null);
        f19464a = e2;
        a2 = RangesKt___RangesKt.a(SystemPropsKt.a(), 2);
        d2 = SystemPropsKt__SystemProps_commonKt.d("kotlinx.coroutines.scheduler.core.pool.size", a2, 1, 0, 8, null);
        f19465b = d2;
        d3 = SystemPropsKt__SystemProps_commonKt.d("kotlinx.coroutines.scheduler.max.pool.size", 2097150, 0, 2097150, 4, null);
        f19466c = d3;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        e3 = SystemPropsKt__SystemProps_commonKt.e("kotlinx.coroutines.scheduler.keep.alive.sec", 60L, 0L, 0L, 12, null);
        f19467d = timeUnit.toNanos(e3);
        f19468e = NanoTimeSource.f19454a;
        f19469f = new TaskContextImpl(0);
        f19470g = new TaskContextImpl(1);
    }
}
