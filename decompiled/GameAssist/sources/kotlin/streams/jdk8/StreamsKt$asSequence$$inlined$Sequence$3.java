package kotlin.streams.jdk8;

import java.util.Iterator;
import java.util.stream.LongStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class StreamsKt$asSequence$$inlined$Sequence$3 implements Sequence<Long> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ LongStream f18758a;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        Iterator<Long> it = this.f18758a.iterator();
        Intrinsics.d(it, "iterator()");
        return it;
    }
}
