package kotlinx.coroutines.debug.internal;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;

@Metadata
/* renamed from: kotlinx.coroutines.debug.internal.DebugProbesImpl$dumpCoroutinesInfoImpl$lambda-12$$inlined$sortedBy$1, reason: invalid class name */
/* loaded from: classes2.dex */
public final class DebugProbesImpl$dumpCoroutinesInfoImpl$lambda12$$inlined$sortedBy$1<T> implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int a2;
        a2 = ComparisonsKt__ComparisonsKt.a(Long.valueOf(((DebugProbesImpl.CoroutineOwner) obj).f19081h.f19065b), Long.valueOf(((DebugProbesImpl.CoroutineOwner) obj2).f19081h.f19065b));
        return a2;
    }
}
