package kotlin.coroutines;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@SinceKotlin
@Metadata
/* loaded from: classes2.dex */
public final class EmptyCoroutineContext implements CoroutineContext, Serializable {

    @NotNull
    public static final EmptyCoroutineContext INSTANCE = new EmptyCoroutineContext();
    private static final long serialVersionUID = 0;

    private EmptyCoroutineContext() {
    }

    private final Object readResolve() {
        return INSTANCE;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext R(CoroutineContext context) {
        Intrinsics.e(context, "context");
        return context;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext Y(CoroutineContext.Key key) {
        Intrinsics.e(key, "key");
        return this;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext.Element c(CoroutineContext.Key key) {
        Intrinsics.e(key, "key");
        return null;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public Object e0(Object obj, Function2 operation) {
        Intrinsics.e(operation, "operation");
        return obj;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "EmptyCoroutineContext";
    }
}
