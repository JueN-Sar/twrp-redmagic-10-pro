package androidx.collection;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;

/* JADX INFO: Add missing generic type declarations: [K] */
@Metadata
/* loaded from: classes.dex */
public final class MutableScatterMap$MutableMapWrapper$keys$1$iterator$1<K> implements Iterator<K>, KMutableIterator {

    /* renamed from: c, reason: collision with root package name */
    private final Iterator f1350c;

    /* renamed from: h, reason: collision with root package name */
    private int f1351h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ MutableScatterMap f1352i;

    MutableScatterMap$MutableMapWrapper$keys$1$iterator$1(MutableScatterMap mutableScatterMap) {
        Iterator a2;
        this.f1352i = mutableScatterMap;
        a2 = SequencesKt__SequenceBuilderKt.a(new MutableScatterMap$MutableMapWrapper$keys$1$iterator$1$iterator$1(mutableScatterMap, null));
        this.f1350c = a2;
        this.f1351h = -1;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f1350c.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        int intValue = ((Number) this.f1350c.next()).intValue();
        this.f1351h = intValue;
        return this.f1352i.f1385b[intValue];
    }

    @Override // java.util.Iterator
    public void remove() {
        int i2 = this.f1351h;
        if (i2 >= 0) {
            this.f1352i.p(i2);
            this.f1351h = -1;
        }
    }
}
