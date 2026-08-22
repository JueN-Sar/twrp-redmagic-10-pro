package androidx.core.util;

import android.util.SparseIntArray;
import kotlin.Metadata;
import kotlin.collections.IntIterator;

@Metadata
/* loaded from: classes.dex */
public final class SparseIntArrayKt$valueIterator$1 extends IntIterator {

    /* renamed from: c, reason: collision with root package name */
    private int f3298c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ SparseIntArray f3299h;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f3298c < this.f3299h.size();
    }

    @Override // kotlin.collections.IntIterator
    public int nextInt() {
        SparseIntArray sparseIntArray = this.f3299h;
        int i2 = this.f3298c;
        this.f3298c = i2 + 1;
        return sparseIntArray.valueAt(i2);
    }
}
