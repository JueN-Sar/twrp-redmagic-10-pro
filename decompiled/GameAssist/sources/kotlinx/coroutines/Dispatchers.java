package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.scheduling.DefaultIoScheduler;
import kotlinx.coroutines.scheduling.DefaultScheduler;

@Metadata
/* loaded from: classes2.dex */
public final class Dispatchers {

    /* renamed from: a, reason: collision with root package name */
    public static final Dispatchers f18868a = new Dispatchers();

    /* renamed from: b, reason: collision with root package name */
    private static final CoroutineDispatcher f18869b = DefaultScheduler.f19446o;

    /* renamed from: c, reason: collision with root package name */
    private static final CoroutineDispatcher f18870c = Unconfined.f18939i;

    /* renamed from: d, reason: collision with root package name */
    private static final CoroutineDispatcher f18871d = DefaultIoScheduler.f19444j;

    private Dispatchers() {
    }

    public static final CoroutineDispatcher a() {
        return f18869b;
    }

    public static final CoroutineDispatcher b() {
        return f18871d;
    }

    public static final MainCoroutineDispatcher c() {
        return MainDispatcherLoader.f19394b;
    }
}
