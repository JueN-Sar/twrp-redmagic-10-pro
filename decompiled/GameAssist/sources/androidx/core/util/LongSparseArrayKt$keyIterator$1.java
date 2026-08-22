package androidx.core.util;

import android.util.LongSparseArray;
import kotlin.Metadata;
import kotlin.collections.LongIterator;

@Metadata
/* loaded from: classes.dex */
public final class LongSparseArrayKt$keyIterator$1 extends LongIterator {

    /* renamed from: c, reason: collision with root package name */
    private int f3263c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ LongSparseArray f3264h;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f3263c < this.f3264h.size();
    }

    @Override // kotlin.collections.LongIterator
    public long nextLong() {
        LongSparseArray longSparseArray = this.f3264h;
        int i2 = this.f3263c;
        this.f3263c = i2 + 1;
        return longSparseArray.keyAt(i2);
    }
}
