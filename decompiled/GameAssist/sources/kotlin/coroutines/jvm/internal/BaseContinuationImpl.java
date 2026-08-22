package kotlin.coroutines.jvm.internal;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.SinceKotlin;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@SinceKotlin
@Metadata
/* loaded from: classes2.dex */
public abstract class BaseContinuationImpl implements Continuation<Object>, CoroutineStackFrame, Serializable {

    @Nullable
    private final Continuation<Object> completion;

    public BaseContinuationImpl(@Nullable Continuation<Object> continuation) {
        this.completion = continuation;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement B() {
        return DebugMetadataKt.d(this);
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame f() {
        Continuation<Object> continuation = this.completion;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, kotlin.coroutines.Continuation, kotlin.coroutines.Continuation<java.lang.Object>] */
    @Override // kotlin.coroutines.Continuation
    public final void g(Object obj) {
        Object v;
        Object d2;
        while (true) {
            DebugProbesKt.b(this);
            BaseContinuationImpl baseContinuationImpl = this;
            ?? r0 = baseContinuationImpl.completion;
            Intrinsics.b(r0);
            try {
                v = baseContinuationImpl.v(obj);
                d2 = IntrinsicsKt__IntrinsicsKt.d();
            } catch (Throwable th) {
                Result.Companion companion = Result.Companion;
                obj = Result.b(ResultKt.a(th));
            }
            if (v == d2) {
                return;
            }
            obj = Result.b(v);
            baseContinuationImpl.w();
            if (!(r0 instanceof BaseContinuationImpl)) {
                r0.g(obj);
                return;
            }
            this = r0;
        }
    }

    public Continuation o(Object obj, Continuation completion) {
        Intrinsics.e(completion, "completion");
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }

    public Continuation p(Continuation completion) {
        Intrinsics.e(completion, "completion");
        throw new UnsupportedOperationException("create(Continuation) has not been overridden");
    }

    public final Continuation s() {
        return this.completion;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Continuation at ");
        Object B = B();
        if (B == null) {
            B = getClass().getName();
        }
        sb.append(B);
        return sb.toString();
    }

    protected abstract Object v(Object obj);

    protected void w() {
    }
}
