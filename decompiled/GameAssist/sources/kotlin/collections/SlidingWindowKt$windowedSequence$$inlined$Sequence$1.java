package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class SlidingWindowKt$windowedSequence$$inlined$Sequence$1 implements Sequence<List<Object>> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Sequence f18363a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ int f18364b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ int f18365c;

    /* renamed from: d, reason: collision with root package name */
    final /* synthetic */ boolean f18366d;

    /* renamed from: e, reason: collision with root package name */
    final /* synthetic */ boolean f18367e;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return SlidingWindowKt.a(this.f18363a.iterator(), this.f18364b, this.f18365c, this.f18366d, this.f18367e);
    }
}
