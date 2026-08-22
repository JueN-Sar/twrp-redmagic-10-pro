package kotlinx.coroutines.debug.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;

@Metadata
/* loaded from: classes2.dex */
public final class DebugProbesImpl$dumpDebuggerInfo$$inlined$dumpCoroutinesInfoImpl$1 extends Lambda implements Function1<DebugProbesImpl.CoroutineOwner<?>, DebuggerInfo> {
    public DebugProbesImpl$dumpDebuggerInfo$$inlined$dumpCoroutinesInfoImpl$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final Object c(DebugProbesImpl.CoroutineOwner coroutineOwner) {
        boolean f2;
        CoroutineContext c2;
        f2 = DebugProbesImpl.f19070a.f(coroutineOwner);
        if (f2 || (c2 = coroutineOwner.f19081h.c()) == null) {
            return null;
        }
        return new DebuggerInfo(coroutineOwner.f19081h, c2);
    }
}
