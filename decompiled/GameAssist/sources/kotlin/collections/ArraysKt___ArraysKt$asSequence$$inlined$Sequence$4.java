package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysKt$asSequence$$inlined$Sequence$4 implements Sequence<Integer> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ int[] f18334a;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return ArrayIteratorsKt.f(this.f18334a);
    }
}
