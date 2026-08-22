package androidx.core.util;

import android.util.SparseLongArray;
import kotlin.Metadata;
import kotlin.collections.IntIterator;

@Metadata
/* loaded from: classes.dex */
public final class SparseLongArrayKt$keyIterator$1 extends IntIterator {

    /* renamed from: c, reason: collision with root package name */
    private int f3300c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ SparseLongArray f3301h;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f3300c < this.f3301h.size();
    }

    @Override // kotlin.collections.IntIterator
    public int nextInt() {
        SparseLongArray sparseLongArray = this.f3301h;
        int i2 = this.f3300c;
        this.f3300c = i2 + 1;
        return sparseLongArray.keyAt(i2);
    }
}
