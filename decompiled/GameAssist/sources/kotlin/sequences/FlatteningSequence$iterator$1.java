package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Add missing generic type declarations: [E] */
@Metadata
/* loaded from: classes2.dex */
public final class FlatteningSequence$iterator$1<E> implements Iterator<E>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final Iterator f18695c;

    /* renamed from: h, reason: collision with root package name */
    private Iterator f18696h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ FlatteningSequence f18697i;

    FlatteningSequence$iterator$1(FlatteningSequence flatteningSequence) {
        Sequence sequence;
        this.f18697i = flatteningSequence;
        sequence = flatteningSequence.f18692a;
        this.f18695c = sequence.iterator();
    }

    private final boolean b() {
        Function1 function1;
        Function1 function12;
        Iterator it = this.f18696h;
        if (it != null && !it.hasNext()) {
            this.f18696h = null;
        }
        while (true) {
            if (this.f18696h != null) {
                break;
            }
            if (!this.f18695c.hasNext()) {
                return false;
            }
            Object next = this.f18695c.next();
            function1 = this.f18697i.f18694c;
            function12 = this.f18697i.f18693b;
            Iterator it2 = (Iterator) function1.c(function12.c(next));
            if (it2.hasNext()) {
                this.f18696h = it2;
                break;
            }
        }
        return true;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return b();
    }

    @Override // java.util.Iterator
    public Object next() {
        if (!b()) {
            throw new NoSuchElementException();
        }
        Iterator it = this.f18696h;
        Intrinsics.b(it);
        return it.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
