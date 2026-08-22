package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;

@SinceKotlin
@Metadata
/* loaded from: classes2.dex */
public interface ContinuationInterceptor extends CoroutineContext.Element {

    /* renamed from: d, reason: collision with root package name */
    public static final Key f18409d = Key.f18410c;

    @Metadata
    public static final class DefaultImpls {
        public static CoroutineContext.Element a(ContinuationInterceptor continuationInterceptor, CoroutineContext.Key key) {
            Intrinsics.e(key, "key");
            if (!(key instanceof AbstractCoroutineContextKey)) {
                if (ContinuationInterceptor.f18409d != key) {
                    return null;
                }
                Intrinsics.c(continuationInterceptor, "null cannot be cast to non-null type E of kotlin.coroutines.ContinuationInterceptor.get");
                return continuationInterceptor;
            }
            AbstractCoroutineContextKey abstractCoroutineContextKey = (AbstractCoroutineContextKey) key;
            if (!abstractCoroutineContextKey.a(continuationInterceptor.getKey())) {
                return null;
            }
            CoroutineContext.Element b2 = abstractCoroutineContextKey.b(continuationInterceptor);
            if (b2 instanceof CoroutineContext.Element) {
                return b2;
            }
            return null;
        }

        public static CoroutineContext b(ContinuationInterceptor continuationInterceptor, CoroutineContext.Key key) {
            Intrinsics.e(key, "key");
            if (!(key instanceof AbstractCoroutineContextKey)) {
                return ContinuationInterceptor.f18409d == key ? EmptyCoroutineContext.INSTANCE : continuationInterceptor;
            }
            AbstractCoroutineContextKey abstractCoroutineContextKey = (AbstractCoroutineContextKey) key;
            return (!abstractCoroutineContextKey.a(continuationInterceptor.getKey()) || abstractCoroutineContextKey.b(continuationInterceptor) == null) ? continuationInterceptor : EmptyCoroutineContext.INSTANCE;
        }
    }

    @Metadata
    public static final class Key implements CoroutineContext.Key<ContinuationInterceptor> {

        /* renamed from: c, reason: collision with root package name */
        static final /* synthetic */ Key f18410c = new Key();

        private Key() {
        }
    }

    void e(Continuation continuation);

    Continuation i(Continuation continuation);
}
