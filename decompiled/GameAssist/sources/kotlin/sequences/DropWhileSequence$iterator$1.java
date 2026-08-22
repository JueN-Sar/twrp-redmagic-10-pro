package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata
/* loaded from: classes2.dex */
public final class DropWhileSequence$iterator$1<T> implements Iterator<T>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final Iterator f18680c;

    /* renamed from: h, reason: collision with root package name */
    private int f18681h;

    /* renamed from: i, reason: collision with root package name */
    private Object f18682i;

    /* renamed from: j, reason: collision with root package name */
    final /* synthetic */ DropWhileSequence f18683j;

    DropWhileSequence$iterator$1(DropWhileSequence dropWhileSequence) {
        Sequence sequence;
        this.f18683j = dropWhileSequence;
        sequence = dropWhileSequence.f18678a;
        this.f18680c = sequence.iterator();
        this.f18681h = -1;
    }

    private final void b() {
        Function1 function1;
        while (this.f18680c.hasNext()) {
            Object next = this.f18680c.next();
            function1 = this.f18683j.f18679b;
            if (!((Boolean) function1.c(next)).booleanValue()) {
                this.f18682i = next;
                this.f18681h = 1;
                return;
            }
        }
        this.f18681h = 0;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.f18681h == -1) {
            b();
        }
        return this.f18681h == 1 || this.f18680c.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        if (this.f18681h == -1) {
            b();
        }
        if (this.f18681h != 1) {
            return this.f18680c.next();
        }
        Object obj = this.f18682i;
        this.f18682i = null;
        this.f18681h = 0;
        return obj;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
