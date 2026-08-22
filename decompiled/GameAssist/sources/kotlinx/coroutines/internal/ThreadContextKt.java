package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ThreadContextElement;

@Metadata
/* loaded from: classes2.dex */
public final class ThreadContextKt {

    /* renamed from: a, reason: collision with root package name */
    public static final Symbol f19407a = new Symbol("NO_THREAD_ELEMENTS");

    /* renamed from: b, reason: collision with root package name */
    private static final Function2 f19408b = new Function2<Object, CoroutineContext.Element, Object>() { // from class: kotlinx.coroutines.internal.ThreadContextKt$countAll$1
        @Override // kotlin.jvm.functions.Function2
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public final Object y(Object obj, CoroutineContext.Element element) {
            if (!(element instanceof ThreadContextElement)) {
                return obj;
            }
            Integer num = obj instanceof Integer ? (Integer) obj : null;
            int intValue = num != null ? num.intValue() : 1;
            return intValue == 0 ? element : Integer.valueOf(intValue + 1);
        }
    };

    /* renamed from: c, reason: collision with root package name */
    private static final Function2 f19409c = new Function2<ThreadContextElement<?>, CoroutineContext.Element, ThreadContextElement<?>>() { // from class: kotlinx.coroutines.internal.ThreadContextKt$findOne$1
        @Override // kotlin.jvm.functions.Function2
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public final ThreadContextElement y(ThreadContextElement threadContextElement, CoroutineContext.Element element) {
            if (threadContextElement != null) {
                return threadContextElement;
            }
            if (element instanceof ThreadContextElement) {
                return (ThreadContextElement) element;
            }
            return null;
        }
    };

    /* renamed from: d, reason: collision with root package name */
    private static final Function2 f19410d = new Function2<ThreadState, CoroutineContext.Element, ThreadState>() { // from class: kotlinx.coroutines.internal.ThreadContextKt$updateState$1
        @Override // kotlin.jvm.functions.Function2
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public final ThreadState y(ThreadState threadState, CoroutineContext.Element element) {
            if (element instanceof ThreadContextElement) {
                ThreadContextElement threadContextElement = (ThreadContextElement) element;
                threadState.a(threadContextElement, threadContextElement.f0(threadState.f19416a));
            }
            return threadState;
        }
    };

    public static final void a(CoroutineContext coroutineContext, Object obj) {
        if (obj == f19407a) {
            return;
        }
        if (obj instanceof ThreadState) {
            ((ThreadState) obj).b(coroutineContext);
            return;
        }
        Object e0 = coroutineContext.e0(null, f19409c);
        if (e0 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
        }
        ((ThreadContextElement) e0).E(coroutineContext, obj);
    }

    public static final Object b(CoroutineContext coroutineContext) {
        Object e0 = coroutineContext.e0(0, f19408b);
        Intrinsics.b(e0);
        return e0;
    }

    public static final Object c(CoroutineContext coroutineContext, Object obj) {
        if (obj == null) {
            obj = b(coroutineContext);
        }
        return obj == 0 ? f19407a : obj instanceof Integer ? coroutineContext.e0(new ThreadState(coroutineContext, ((Number) obj).intValue()), f19410d) : ((ThreadContextElement) obj).f0(coroutineContext);
    }
}
