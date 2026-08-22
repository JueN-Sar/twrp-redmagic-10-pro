package androidx.core.util;

import android.util.SparseArray;
import kotlin.Metadata;
import kotlin.collections.IntIterator;

@Metadata
/* loaded from: classes.dex */
public final class SparseArrayKt$keyIterator$1 extends IntIterator {

    /* renamed from: c, reason: collision with root package name */
    private int f3288c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ SparseArray f3289h;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f3288c < this.f3289h.size();
    }

    @Override // kotlin.collections.IntIterator
    public int nextInt() {
        SparseArray sparseArray = this.f3289h;
        int i2 = this.f3288c;
        this.f3288c = i2 + 1;
        return sparseArray.keyAt(i2);
    }
}
