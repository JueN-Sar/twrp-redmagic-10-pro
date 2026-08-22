package androidx.collection;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;

/* JADX INFO: Add missing generic type declarations: [V] */
@Metadata
/* loaded from: classes.dex */
public final class MutableScatterMap$MutableMapWrapper$values$1$iterator$1<V> implements Iterator<V>, KMutableIterator {

    /* renamed from: c, reason: collision with root package name */
    private final Iterator f1354c;

    /* renamed from: h, reason: collision with root package name */
    private int f1355h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ MutableScatterMap f1356i;

    MutableScatterMap$MutableMapWrapper$values$1$iterator$1(MutableScatterMap mutableScatterMap) {
        Iterator a2;
        this.f1356i = mutableScatterMap;
        a2 = SequencesKt__SequenceBuilderKt.a(new MutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1(mutableScatterMap, null));
        this.f1354c = a2;
        this.f1355h = -1;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f1354c.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        int intValue = ((Number) this.f1354c.next()).intValue();
        this.f1355h = intValue;
        return this.f1356i.f1386c[intValue];
    }

    @Override // java.util.Iterator
    public void remove() {
        int i2 = this.f1355h;
        if (i2 >= 0) {
            this.f1356i.p(i2);
            this.f1355h = -1;
        }
    }
}
