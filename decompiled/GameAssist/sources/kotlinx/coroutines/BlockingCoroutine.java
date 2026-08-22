package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
final class BlockingCoroutine<T> extends AbstractCoroutine<T> {

    /* renamed from: i, reason: collision with root package name */
    private final Thread f18826i;

    @Override // kotlinx.coroutines.JobSupport
    protected void W(Object obj) {
        Unit unit;
        if (Intrinsics.a(Thread.currentThread(), this.f18826i)) {
            return;
        }
        Thread thread = this.f18826i;
        AbstractTimeSource a2 = AbstractTimeSourceKt.a();
        if (a2 != null) {
            a2.f(thread);
            unit = Unit.f18288a;
        } else {
            unit = null;
        }
        if (unit == null) {
            LockSupport.unpark(thread);
        }
    }

    @Override // kotlinx.coroutines.JobSupport
    protected boolean y0() {
        return true;
    }
}
