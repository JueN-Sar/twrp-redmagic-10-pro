package kotlinx.coroutines.debug.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class DebugProbesImpl$startWeakRefCleanerThread$1 extends Lambda implements Function0<Unit> {
    public static final DebugProbesImpl$startWeakRefCleanerThread$1 INSTANCE = new DebugProbesImpl$startWeakRefCleanerThread$1();

    DebugProbesImpl$startWeakRefCleanerThread$1() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Object a() {
        d();
        return Unit.f18288a;
    }

    public final void d() {
        ConcurrentWeakMap concurrentWeakMap;
        concurrentWeakMap = DebugProbesImpl.f19079j;
        concurrentWeakMap.j();
    }
}
