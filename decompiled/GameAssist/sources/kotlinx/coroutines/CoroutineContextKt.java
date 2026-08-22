package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

@Metadata
/* loaded from: classes2.dex */
public final class CoroutineContextKt {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4, types: [T, java.lang.Object] */
    private static final CoroutineContext a(CoroutineContext coroutineContext, CoroutineContext coroutineContext2, final boolean z) {
        boolean c2 = c(coroutineContext);
        boolean c3 = c(coroutineContext2);
        if (!c2 && !c3) {
            return coroutineContext.R(coroutineContext2);
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = coroutineContext2;
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        CoroutineContext coroutineContext3 = (CoroutineContext) coroutineContext.e0(emptyCoroutineContext, new Function2<CoroutineContext, CoroutineContext.Element, CoroutineContext>() { // from class: kotlinx.coroutines.CoroutineContextKt$foldCopies$folded$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* JADX WARN: Type inference failed for: r1v3, types: [T, kotlin.coroutines.CoroutineContext] */
            @Override // kotlin.jvm.functions.Function2
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final CoroutineContext y(CoroutineContext coroutineContext4, CoroutineContext.Element element) {
                if (!(element instanceof CopyableThreadContextElement)) {
                    return coroutineContext4.R(element);
                }
                CoroutineContext.Element c4 = objectRef.element.c(element.getKey());
                if (c4 == null) {
                    return coroutineContext4.R(z ? ((CopyableThreadContextElement) element).C() : (CopyableThreadContextElement) element);
                }
                Ref.ObjectRef<CoroutineContext> objectRef2 = objectRef;
                objectRef2.element = objectRef2.element.Y(element.getKey());
                return coroutineContext4.R(((CopyableThreadContextElement) element).j(c4));
            }
        });
        if (c3) {
            objectRef.element = ((CoroutineContext) objectRef.element).e0(emptyCoroutineContext, new Function2<CoroutineContext, CoroutineContext.Element, CoroutineContext>() { // from class: kotlinx.coroutines.CoroutineContextKt$foldCopies$1
                @Override // kotlin.jvm.functions.Function2
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public final CoroutineContext y(CoroutineContext coroutineContext4, CoroutineContext.Element element) {
                    return element instanceof CopyableThreadContextElement ? coroutineContext4.R(((CopyableThreadContextElement) element).C()) : coroutineContext4.R(element);
                }
            });
        }
        return coroutineContext3.R((CoroutineContext) objectRef.element);
    }

    public static final String b(CoroutineContext coroutineContext) {
        return null;
    }

    private static final boolean c(CoroutineContext coroutineContext) {
        return ((Boolean) coroutineContext.e0(Boolean.FALSE, new Function2<Boolean, CoroutineContext.Element, Boolean>() { // from class: kotlinx.coroutines.CoroutineContextKt$hasCopyableElements$1
            public final Boolean d(boolean z, CoroutineContext.Element element) {
                return Boolean.valueOf(z || (element instanceof CopyableThreadContextElement));
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object y(Object obj, Object obj2) {
                return d(((Boolean) obj).booleanValue(), (CoroutineContext.Element) obj2);
            }
        })).booleanValue();
    }

    public static final CoroutineContext d(CoroutineContext coroutineContext, CoroutineContext coroutineContext2) {
        return !c(coroutineContext2) ? coroutineContext.R(coroutineContext2) : a(coroutineContext, coroutineContext2, false);
    }

    public static final CoroutineContext e(CoroutineScope coroutineScope, CoroutineContext coroutineContext) {
        CoroutineContext a2 = a(coroutineScope.K(), coroutineContext, true);
        return (a2 == Dispatchers.a() || a2.c(ContinuationInterceptor.f18409d) != null) ? a2 : a2.R(Dispatchers.a());
    }

    public static final UndispatchedCoroutine f(CoroutineStackFrame coroutineStackFrame) {
        while (!(coroutineStackFrame instanceof DispatchedCoroutine) && (coroutineStackFrame = coroutineStackFrame.f()) != null) {
            if (coroutineStackFrame instanceof UndispatchedCoroutine) {
                return (UndispatchedCoroutine) coroutineStackFrame;
            }
        }
        return null;
    }

    public static final UndispatchedCoroutine g(Continuation continuation, CoroutineContext coroutineContext, Object obj) {
        if (!(continuation instanceof CoroutineStackFrame) || coroutineContext.c(UndispatchedMarker.f18941c) == null) {
            return null;
        }
        UndispatchedCoroutine f2 = f((CoroutineStackFrame) continuation);
        if (f2 != null) {
            f2.i1(coroutineContext, obj);
        }
        return f2;
    }
}
