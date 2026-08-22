package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlinx.coroutines.DebugStringsKt;

@Metadata
/* loaded from: classes2.dex */
public abstract class OpDescriptor {
    public abstract AtomicOp a();

    public final boolean b(OpDescriptor opDescriptor) {
        AtomicOp a2;
        AtomicOp a3 = a();
        return (a3 == null || (a2 = opDescriptor.a()) == null || a3.g() >= a2.g()) ? false : true;
    }

    public abstract Object c(Object obj);

    public String toString() {
        return DebugStringsKt.a(this) + '@' + DebugStringsKt.b(this);
    }
}
