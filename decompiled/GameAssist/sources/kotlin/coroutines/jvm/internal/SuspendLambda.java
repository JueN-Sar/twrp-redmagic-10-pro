package kotlin.coroutines.jvm.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.FunctionBase;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.Nullable;

@SinceKotlin
@Metadata
/* loaded from: classes2.dex */
public abstract class SuspendLambda extends ContinuationImpl implements FunctionBase<Object>, SuspendFunction {
    private final int arity;

    public SuspendLambda(int i2, @Nullable Continuation<Object> continuation) {
        super(continuation);
        this.arity = i2;
    }

    @Override // kotlin.jvm.internal.FunctionBase
    public int t() {
        return this.arity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public String toString() {
        if (s() != null) {
            return super.toString();
        }
        String k2 = Reflection.k(this);
        Intrinsics.d(k2, "renderLambdaToString(this)");
        return k2;
    }

    public SuspendLambda(int i2) {
        this(i2, null);
    }
}
