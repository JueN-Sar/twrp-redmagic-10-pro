package kotlin.streams.jdk8;

import java.util.Iterator;
import java.util.stream.DoubleStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class StreamsKt$asSequence$$inlined$Sequence$4 implements Sequence<Double> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ DoubleStream f18759a;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        Iterator<Double> it = this.f18759a.iterator();
        Intrinsics.d(it, "iterator()");
        return it;
    }
}
