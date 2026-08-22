package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata
/* loaded from: classes2.dex */
public final class FilteringSequence$iterator$1<T> implements Iterator<T>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final Iterator f18688c;

    /* renamed from: h, reason: collision with root package name */
    private int f18689h;

    /* renamed from: i, reason: collision with root package name */
    private Object f18690i;

    /* renamed from: j, reason: collision with root package name */
    final /* synthetic */ FilteringSequence f18691j;

    FilteringSequence$iterator$1(FilteringSequence filteringSequence) {
        Sequence sequence;
        this.f18691j = filteringSequence;
        sequence = filteringSequence.f18685a;
        this.f18688c = sequence.iterator();
        this.f18689h = -1;
    }

    private final void b() {
        Function1 function1;
        boolean z;
        while (this.f18688c.hasNext()) {
            Object next = this.f18688c.next();
            function1 = this.f18691j.f18687c;
            boolean booleanValue = ((Boolean) function1.c(next)).booleanValue();
            z = this.f18691j.f18686b;
            if (booleanValue == z) {
                this.f18690i = next;
                this.f18689h = 1;
                return;
            }
        }
        this.f18689h = 0;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.f18689h == -1) {
            b();
        }
        return this.f18689h == 1;
    }

    @Override // java.util.Iterator
    public Object next() {
        if (this.f18689h == -1) {
            b();
        }
        if (this.f18689h == 0) {
            throw new NoSuchElementException();
        }
        Object obj = this.f18690i;
        this.f18690i = null;
        this.f18689h = -1;
        return obj;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
