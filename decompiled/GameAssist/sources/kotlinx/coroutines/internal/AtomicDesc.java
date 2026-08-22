package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
public abstract class AtomicDesc {

    /* renamed from: a, reason: collision with root package name */
    public AtomicOp f19338a;

    public abstract void a(AtomicOp atomicOp, Object obj);

    public final AtomicOp b() {
        AtomicOp atomicOp = this.f19338a;
        if (atomicOp != null) {
            return atomicOp;
        }
        Intrinsics.s("atomicOp");
        return null;
    }

    public abstract Object c(AtomicOp atomicOp);

    public final void d(AtomicOp atomicOp) {
        this.f19338a = atomicOp;
    }
}
