package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KFunction;

/* loaded from: classes2.dex */
public class FunctionReference extends CallableReference implements FunctionBase, KFunction {
    private final int arity;

    @SinceKotlin
    private final int flags;

    public FunctionReference(int i2) {
        this(i2, CallableReference.NO_RECEIVER, null, null, null, 0);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FunctionReference) {
            FunctionReference functionReference = (FunctionReference) obj;
            return k().equals(functionReference.k()) && o().equals(functionReference.o()) && this.flags == functionReference.flags && this.arity == functionReference.arity && Intrinsics.a(i(), functionReference.i()) && Intrinsics.a(m(), functionReference.m());
        }
        if (obj instanceof KFunction) {
            return obj.equals(g());
        }
        return false;
    }

    @Override // kotlin.jvm.internal.CallableReference
    protected KCallable h() {
        return Reflection.a(this);
    }

    public int hashCode() {
        return (((m() == null ? 0 : m().hashCode() * 31) + k().hashCode()) * 31) + o().hashCode();
    }

    @Override // kotlin.jvm.internal.FunctionBase
    public int t() {
        return this.arity;
    }

    public String toString() {
        KCallable g2 = g();
        if (g2 != this) {
            return g2.toString();
        }
        if ("<init>".equals(k())) {
            return "constructor (Kotlin reflection is not available)";
        }
        return "function " + k() + " (Kotlin reflection is not available)";
    }

    @SinceKotlin
    public FunctionReference(int i2, Object obj) {
        this(i2, obj, null, null, null, 0);
    }

    @SinceKotlin
    public FunctionReference(int i2, Object obj, Class cls, String str, String str2, int i3) {
        super(obj, cls, str, str2, (i3 & 1) == 1);
        this.arity = i2;
        this.flags = i3 >> 1;
    }
}
