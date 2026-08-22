package androidx.collection;

import kotlin.Metadata;
import kotlin.collections.LongIterator;

@Metadata
/* loaded from: classes.dex */
public final class LongSparseArrayKt$keyIterator$1 extends LongIterator {

    /* renamed from: c, reason: collision with root package name */
    private int f1298c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ LongSparseArray f1299h;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f1298c < this.f1299h.n();
    }

    @Override // kotlin.collections.LongIterator
    public long nextLong() {
        LongSparseArray longSparseArray = this.f1299h;
        int i2 = this.f1298c;
        this.f1298c = i2 + 1;
        return longSparseArray.j(i2);
    }
}
