package kotlin.sequences;

import java.util.HashSet;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.AbstractIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
final class DistinctIterator<T, K> extends AbstractIterator<T> {

    /* renamed from: i, reason: collision with root package name */
    private final Iterator f18669i;

    /* renamed from: j, reason: collision with root package name */
    private final Function1 f18670j;

    /* renamed from: k, reason: collision with root package name */
    private final HashSet f18671k;

    public DistinctIterator(Iterator source, Function1 keySelector) {
        Intrinsics.e(source, "source");
        Intrinsics.e(keySelector, "keySelector");
        this.f18669i = source;
        this.f18670j = keySelector;
        this.f18671k = new HashSet();
    }

    @Override // kotlin.collections.AbstractIterator
    protected void b() {
        while (this.f18669i.hasNext()) {
            Object next = this.f18669i.next();
            if (this.f18671k.add(this.f18670j.c(next))) {
                d(next);
                return;
            }
        }
        c();
    }
}
