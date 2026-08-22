package kotlin.jvm.internal;

import java.io.Serializable;
import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public abstract class Lambda<R> implements FunctionBase<R>, Serializable {
    private final int arity;

    public Lambda(int i2) {
        this.arity = i2;
    }

    @Override // kotlin.jvm.internal.FunctionBase
    public int t() {
        return this.arity;
    }

    public String toString() {
        String l2 = Reflection.l(this);
        Intrinsics.d(l2, "renderLambdaToString(this)");
        return l2;
    }
}
