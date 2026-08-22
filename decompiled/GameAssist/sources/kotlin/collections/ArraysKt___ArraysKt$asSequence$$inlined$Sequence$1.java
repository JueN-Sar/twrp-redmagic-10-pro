package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysKt$asSequence$$inlined$Sequence$1 implements Sequence<Object> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Object[] f18331a;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return ArrayIteratorKt.a(this.f18331a);
    }
}
