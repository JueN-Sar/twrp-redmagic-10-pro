package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysKt$asSequence$$inlined$Sequence$3 implements Sequence<Short> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ short[] f18333a;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return ArrayIteratorsKt.h(this.f18333a);
    }
}
