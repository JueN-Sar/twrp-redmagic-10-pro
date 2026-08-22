package kotlin.streams.jdk8;

import java.util.Iterator;
import java.util.stream.IntStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class StreamsKt$asSequence$$inlined$Sequence$2 implements Sequence<Integer> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ IntStream f18757a;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        Iterator<Integer> it = this.f18757a.iterator();
        Intrinsics.d(it, "iterator()");
        return it;
    }
}
