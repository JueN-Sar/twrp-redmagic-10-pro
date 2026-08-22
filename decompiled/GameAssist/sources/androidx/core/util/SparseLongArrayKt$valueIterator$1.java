package androidx.core.util;

import android.util.SparseLongArray;
import kotlin.Metadata;
import kotlin.collections.LongIterator;

@Metadata
/* loaded from: classes.dex */
public final class SparseLongArrayKt$valueIterator$1 extends LongIterator {

    /* renamed from: c, reason: collision with root package name */
    private int f3302c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ SparseLongArray f3303h;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f3302c < this.f3303h.size();
    }

    @Override // kotlin.collections.LongIterator
    public long nextLong() {
        SparseLongArray sparseLongArray = this.f3303h;
        int i2 = this.f3302c;
        this.f3302c = i2 + 1;
        return sparseLongArray.valueAt(i2);
    }
}
