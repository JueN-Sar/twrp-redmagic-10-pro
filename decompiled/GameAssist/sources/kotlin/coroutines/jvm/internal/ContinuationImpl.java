package kotlin.coroutines.jvm.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.Nullable;

@SinceKotlin
@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public abstract class ContinuationImpl extends BaseContinuationImpl {

    @Nullable
    private final CoroutineContext _context;

    @Nullable
    private transient Continuation<Object> intercepted;

    public ContinuationImpl(@Nullable Continuation<Object> continuation, @Nullable CoroutineContext coroutineContext) {
        super(continuation);
        this._context = coroutineContext;
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        CoroutineContext coroutineContext = this._context;
        Intrinsics.b(coroutineContext);
        return coroutineContext;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    protected void w() {
        Continuation<Object> continuation = this.intercepted;
        if (continuation != null && continuation != this) {
            CoroutineContext.Element c2 = getContext().c(ContinuationInterceptor.f18409d);
            Intrinsics.b(c2);
            ((ContinuationInterceptor) c2).e(continuation);
        }
        this.intercepted = CompletedContinuation.f18416c;
    }

    public final Continuation x() {
        Continuation continuation = this.intercepted;
        if (continuation == null) {
            ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) getContext().c(ContinuationInterceptor.f18409d);
            if (continuationInterceptor == null || (continuation = continuationInterceptor.i(this)) == null) {
                continuation = this;
            }
            this.intercepted = continuation;
        }
        return continuation;
    }

    public ContinuationImpl(@Nullable Continuation<Object> continuation) {
        this(continuation, continuation != null ? continuation.getContext() : null);
    }
}
