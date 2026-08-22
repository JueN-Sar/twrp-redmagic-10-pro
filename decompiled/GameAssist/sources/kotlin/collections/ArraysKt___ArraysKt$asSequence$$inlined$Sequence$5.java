package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysKt$asSequence$$inlined$Sequence$5 implements Sequence<Long> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ long[] f18335a;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return ArrayIteratorsKt.g(this.f18335a);
    }
}
