package kotlinx.coroutines;

import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.AbstractCoroutineContextKey;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.LimitedDispatcher;
import kotlinx.coroutines.internal.LimitedDispatcherKt;

@Metadata
/* loaded from: classes2.dex */
public abstract class CoroutineDispatcher extends AbstractCoroutineContextElement implements ContinuationInterceptor {

    /* renamed from: h, reason: collision with root package name */
    public static final Key f18848h = new Key(null);

    @Metadata
    @ExperimentalStdlibApi
    public static final class Key extends AbstractCoroutineContextKey<ContinuationInterceptor, CoroutineDispatcher> {
        public /* synthetic */ Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Key() {
            super(ContinuationInterceptor.f18409d, new Function1<CoroutineContext.Element, CoroutineDispatcher>() { // from class: kotlinx.coroutines.CoroutineDispatcher.Key.1
                @Override // kotlin.jvm.functions.Function1
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public final CoroutineDispatcher c(CoroutineContext.Element element) {
                    if (element instanceof CoroutineDispatcher) {
                        return (CoroutineDispatcher) element;
                    }
                    return null;
                }
            });
        }
    }

    public CoroutineDispatcher() {
        super(ContinuationInterceptor.f18409d);
    }

    @Override // kotlin.coroutines.AbstractCoroutineContextElement, kotlin.coroutines.CoroutineContext
    public CoroutineContext Y(CoroutineContext.Key key) {
        return ContinuationInterceptor.DefaultImpls.b(this, key);
    }

    @Override // kotlin.coroutines.AbstractCoroutineContextElement, kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public CoroutineContext.Element c(CoroutineContext.Key key) {
        return ContinuationInterceptor.DefaultImpls.a(this, key);
    }

    @Override // kotlin.coroutines.ContinuationInterceptor
    public final void e(Continuation continuation) {
        ((DispatchedContinuation) continuation).s();
    }

    @Override // kotlin.coroutines.ContinuationInterceptor
    public final Continuation i(Continuation continuation) {
        return new DispatchedContinuation(this, continuation);
    }

    public abstract void j0(CoroutineContext coroutineContext, Runnable runnable);

    public void k0(CoroutineContext coroutineContext, Runnable runnable) {
        j0(coroutineContext, runnable);
    }

    public boolean l0(CoroutineContext coroutineContext) {
        return true;
    }

    public CoroutineDispatcher m0(int i2) {
        LimitedDispatcherKt.a(i2);
        return new LimitedDispatcher(this, i2);
    }

    public String toString() {
        return DebugStringsKt.a(this) + '@' + DebugStringsKt.b(this);
    }
}
