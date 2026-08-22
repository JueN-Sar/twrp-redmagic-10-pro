package kotlin.streams.jdk8;

import java.util.Iterator;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class StreamsKt$asSequence$$inlined$Sequence$1 implements Sequence<Object> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Stream f18756a;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        Iterator it = this.f18756a.iterator();
        Intrinsics.d(it, "iterator()");
        return it;
    }
}
