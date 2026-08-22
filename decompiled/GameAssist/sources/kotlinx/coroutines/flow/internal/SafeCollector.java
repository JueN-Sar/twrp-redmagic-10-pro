package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__IndentKt;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata
/* loaded from: classes2.dex */
public final class SafeCollector<T> extends ContinuationImpl implements FlowCollector<T>, CoroutineStackFrame {

    @JvmField
    @NotNull
    public final CoroutineContext collectContext;

    @JvmField
    public final int collectContextSize;

    @JvmField
    @NotNull
    public final FlowCollector<T> collector;

    @Nullable
    private Continuation<? super Unit> completion;

    @Nullable
    private CoroutineContext lastEmissionContext;

    /* JADX WARN: Multi-variable type inference failed */
    public SafeCollector(@NotNull FlowCollector<? super T> flowCollector, @NotNull CoroutineContext coroutineContext) {
        super(NoOpContinuation.f19321c, EmptyCoroutineContext.INSTANCE);
        this.collector = flowCollector;
        this.collectContext = coroutineContext;
        this.collectContextSize = ((Number) coroutineContext.e0(0, new Function2<Integer, CoroutineContext.Element, Integer>() { // from class: kotlinx.coroutines.flow.internal.SafeCollector$collectContextSize$1
            public final Integer d(int i2, CoroutineContext.Element element) {
                return Integer.valueOf(i2 + 1);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object y(Object obj, Object obj2) {
                return d(((Number) obj).intValue(), (CoroutineContext.Element) obj2);
            }
        })).intValue();
    }

    private final void A(CoroutineContext coroutineContext, CoroutineContext coroutineContext2, Object obj) {
        if (coroutineContext2 instanceof DownstreamExceptionContext) {
            F((DownstreamExceptionContext) coroutineContext2, obj);
        }
        SafeCollector_commonKt.a(this, coroutineContext);
    }

    private final Object C(Continuation continuation, Object obj) {
        Function3 function3;
        Object d2;
        CoroutineContext context = continuation.getContext();
        JobKt.g(context);
        CoroutineContext coroutineContext = this.lastEmissionContext;
        if (coroutineContext != context) {
            A(context, coroutineContext, obj);
            this.lastEmissionContext = context;
        }
        this.completion = continuation;
        function3 = SafeCollectorKt.f19327a;
        Object u = function3.u(this.collector, obj, this);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        if (!Intrinsics.a(u, d2)) {
            this.completion = null;
        }
        return u;
    }

    private final void F(DownstreamExceptionContext downstreamExceptionContext, Object obj) {
        String e2;
        e2 = StringsKt__IndentKt.e("\n            Flow exception transparency is violated:\n                Previous 'emit' call has thrown exception " + downstreamExceptionContext.f19318c + ", but then emission attempt of value '" + obj + "' has been detected.\n                Emissions from 'catch' blocks are prohibited in order to avoid unspecified behaviour, 'Flow.catch' operator can be used instead.\n                For a more detailed explanation, please refer to Flow documentation.\n            ");
        throw new IllegalStateException(e2.toString());
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl, kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement B() {
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl, kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame f() {
        Continuation<? super Unit> continuation = this.completion;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.ContinuationImpl, kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        CoroutineContext coroutineContext = this.lastEmissionContext;
        return coroutineContext == null ? EmptyCoroutineContext.INSTANCE : coroutineContext;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object k(Object obj, Continuation continuation) {
        Object d2;
        Object d3;
        try {
            Object C = C(continuation, obj);
            d2 = IntrinsicsKt__IntrinsicsKt.d();
            if (C == d2) {
                DebugProbesKt.c(continuation);
            }
            d3 = IntrinsicsKt__IntrinsicsKt.d();
            return C == d3 ? C : Unit.f18288a;
        } catch (Throwable th) {
            this.lastEmissionContext = new DownstreamExceptionContext(th, continuation.getContext());
            throw th;
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object v(Object obj) {
        Object d2;
        Throwable d3 = Result.d(obj);
        if (d3 != null) {
            this.lastEmissionContext = new DownstreamExceptionContext(d3, getContext());
        }
        Continuation<? super Unit> continuation = this.completion;
        if (continuation != null) {
            continuation.g(obj);
        }
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return d2;
    }

    @Override // kotlin.coroutines.jvm.internal.ContinuationImpl, kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public void w() {
        super.w();
    }
}
