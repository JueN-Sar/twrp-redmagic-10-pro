package androidx.core.util;

import android.util.SparseIntArray;
import kotlin.Metadata;
import kotlin.collections.IntIterator;

@Metadata
/* loaded from: classes.dex */
public final class SparseIntArrayKt$keyIterator$1 extends IntIterator {

    /* renamed from: c, reason: collision with root package name */
    private int f3296c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ SparseIntArray f3297h;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f3296c < this.f3297h.size();
    }

    @Override // kotlin.collections.IntIterator
    public int nextInt() {
        SparseIntArray sparseIntArray = this.f3297h;
        int i2 = this.f3296c;
        this.f3296c = i2 + 1;
        return sparseIntArray.keyAt(i2);
    }
}
