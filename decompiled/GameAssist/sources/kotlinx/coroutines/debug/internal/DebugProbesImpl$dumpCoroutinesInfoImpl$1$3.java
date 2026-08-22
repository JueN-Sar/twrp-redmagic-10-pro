package kotlinx.coroutines.debug.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;

@Metadata
/* loaded from: classes2.dex */
public final class DebugProbesImpl$dumpCoroutinesInfoImpl$1$3 extends Lambda implements Function1<DebugProbesImpl.CoroutineOwner<?>, Object> {
    final /* synthetic */ Function2<DebugProbesImpl.CoroutineOwner<?>, CoroutineContext, Object> $create;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DebugProbesImpl$dumpCoroutinesInfoImpl$1$3(Function2<? super DebugProbesImpl.CoroutineOwner<?>, ? super CoroutineContext, Object> function2) {
        super(1);
        this.$create = function2;
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
        return this.$create.y(coroutineOwner, c2);
    }
}
