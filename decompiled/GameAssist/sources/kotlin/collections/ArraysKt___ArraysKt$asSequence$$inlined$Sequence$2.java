package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysKt$asSequence$$inlined$Sequence$2 implements Sequence<Byte> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ byte[] f18332a;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return ArrayIteratorsKt.b(this.f18332a);
    }
}
