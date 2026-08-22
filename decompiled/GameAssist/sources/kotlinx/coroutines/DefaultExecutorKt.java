package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.internal.MainDispatchersKt;
import kotlinx.coroutines.internal.SystemPropsKt;

@Metadata
/* loaded from: classes2.dex */
public final class DefaultExecutorKt {

    /* renamed from: a, reason: collision with root package name */
    private static final boolean f18863a = SystemPropsKt.e("kotlinx.coroutines.main.delay", false);

    /* renamed from: b, reason: collision with root package name */
    private static final Delay f18864b = b();

    public static final Delay a() {
        return f18864b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final Delay b() {
        if (!f18863a) {
            return DefaultExecutor.f18861n;
        }
        MainCoroutineDispatcher c2 = Dispatchers.c();
        return (MainDispatchersKt.c(c2) || !(c2 instanceof Delay)) ? DefaultExecutor.f18861n : (Delay) c2;
    }
}
