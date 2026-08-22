package androidx.collection;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;

/* JADX INFO: Add missing generic type declarations: [E] */
@Metadata
/* loaded from: classes.dex */
public final class MutableScatterSet$MutableSetWrapper$iterator$1<E> implements Iterator<E>, KMutableIterator {

    /* renamed from: c, reason: collision with root package name */
    private int f1359c = -1;

    /* renamed from: h, reason: collision with root package name */
    private final Iterator f1360h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ MutableScatterSet f1361i;

    MutableScatterSet$MutableSetWrapper$iterator$1(MutableScatterSet mutableScatterSet) {
        Iterator a2;
        this.f1361i = mutableScatterSet;
        a2 = SequencesKt__SequenceBuilderKt.a(new MutableScatterSet$MutableSetWrapper$iterator$1$iterator$1(mutableScatterSet, this, null));
        this.f1360h = a2;
    }

    public final void b(int i2) {
        this.f1359c = i2;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f1360h.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        return this.f1360h.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        int i2 = this.f1359c;
        if (i2 != -1) {
            this.f1361i.u(i2);
            this.f1359c = -1;
        }
    }
}
