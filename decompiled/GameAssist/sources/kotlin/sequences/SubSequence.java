package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class SubSequence<T> implements Sequence<T>, DropTakeSequence<T> {

    /* renamed from: a, reason: collision with root package name */
    private final Sequence f18731a;

    /* renamed from: b, reason: collision with root package name */
    private final int f18732b;

    /* renamed from: c, reason: collision with root package name */
    private final int f18733c;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new SubSequence$iterator$1(this);
    }
}
