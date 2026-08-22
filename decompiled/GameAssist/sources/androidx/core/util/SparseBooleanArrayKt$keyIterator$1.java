package androidx.core.util;

import android.util.SparseBooleanArray;
import kotlin.Metadata;
import kotlin.collections.IntIterator;

@Metadata
/* loaded from: classes.dex */
public final class SparseBooleanArrayKt$keyIterator$1 extends IntIterator {

    /* renamed from: c, reason: collision with root package name */
    private int f3292c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ SparseBooleanArray f3293h;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f3292c < this.f3293h.size();
    }

    @Override // kotlin.collections.IntIterator
    public int nextInt() {
        SparseBooleanArray sparseBooleanArray = this.f3293h;
        int i2 = this.f3292c;
        this.f3292c = i2 + 1;
        return sparseBooleanArray.keyAt(i2);
    }
}
