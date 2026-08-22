package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@SinceKotlin
@Metadata
/* loaded from: classes2.dex */
public interface CoroutineContext {

    @Metadata
    public static final class DefaultImpls {
        public static CoroutineContext a(CoroutineContext coroutineContext, CoroutineContext context) {
            Intrinsics.e(context, "context");
            return context == EmptyCoroutineContext.INSTANCE ? coroutineContext : (CoroutineContext) context.e0(coroutineContext, new Function2<CoroutineContext, Element, CoroutineContext>() { // from class: kotlin.coroutines.CoroutineContext$plus$1
                @Override // kotlin.jvm.functions.Function2
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public final CoroutineContext y(CoroutineContext acc, CoroutineContext.Element element) {
                    CombinedContext combinedContext;
                    Intrinsics.e(acc, "acc");
                    Intrinsics.e(element, "element");
                    CoroutineContext Y = acc.Y(element.getKey());
                    EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
                    if (Y == emptyCoroutineContext) {
                        return element;
                    }
                    ContinuationInterceptor.Key key = ContinuationInterceptor.f18409d;
                    ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) Y.c(key);
                    if (continuationInterceptor == null) {
                        combinedContext = new CombinedContext(Y, element);
                    } else {
                        CoroutineContext Y2 = Y.Y(key);
                        if (Y2 == emptyCoroutineContext) {
                            return new CombinedContext(element, continuationInterceptor);
                        }
                        combinedContext = new CombinedContext(new CombinedContext(Y2, element), continuationInterceptor);
                    }
                    return combinedContext;
                }
            });
        }
    }

    @Metadata
    public interface Element extends CoroutineContext {

        @Metadata
        public static final class DefaultImpls {
            public static Object a(Element element, Object obj, Function2 operation) {
                Intrinsics.e(operation, "operation");
                return operation.y(obj, element);
            }

            public static Element b(Element element, Key key) {
                Intrinsics.e(key, "key");
                if (!Intrinsics.a(element.getKey(), key)) {
                    return null;
                }
                Intrinsics.c(element, "null cannot be cast to non-null type E of kotlin.coroutines.CoroutineContext.Element.get");
                return element;
            }

            public static CoroutineContext c(Element element, Key key) {
                Intrinsics.e(key, "key");
                return Intrinsics.a(element.getKey(), key) ? EmptyCoroutineContext.INSTANCE : element;
            }

            public static CoroutineContext d(Element element, CoroutineContext context) {
                Intrinsics.e(context, "context");
                return DefaultImpls.a(element, context);
            }
        }

        @Override // kotlin.coroutines.CoroutineContext
        Element c(Key key);

        Key getKey();
    }

    @Metadata
    public interface Key<E extends Element> {
    }

    CoroutineContext R(CoroutineContext coroutineContext);

    CoroutineContext Y(Key key);

    Element c(Key key);

    Object e0(Object obj, Function2 function2);
}
