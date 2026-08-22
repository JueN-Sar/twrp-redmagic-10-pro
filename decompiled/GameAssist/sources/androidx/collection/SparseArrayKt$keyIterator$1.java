package androidx.collection;

import kotlin.Metadata;
import kotlin.collections.IntIterator;

@Metadata
/* loaded from: classes.dex */
public final class SparseArrayKt$keyIterator$1 extends IntIterator {

    /* renamed from: c, reason: collision with root package name */
    private int f1409c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ SparseArrayCompat f1410h;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f1409c < this.f1410h.j();
    }

    @Override // kotlin.collections.IntIterator
    public int nextInt() {
        SparseArrayCompat sparseArrayCompat = this.f1410h;
        int i2 = this.f1409c;
        this.f1409c = i2 + 1;
        return sparseArrayCompat.h(i2);
    }
}
