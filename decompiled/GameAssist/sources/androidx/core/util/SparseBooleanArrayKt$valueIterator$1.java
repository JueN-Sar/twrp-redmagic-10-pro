package androidx.core.util;

import android.util.SparseBooleanArray;
import kotlin.Metadata;
import kotlin.collections.BooleanIterator;

@Metadata
/* loaded from: classes.dex */
public final class SparseBooleanArrayKt$valueIterator$1 extends BooleanIterator {

    /* renamed from: c, reason: collision with root package name */
    private int f3294c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ SparseBooleanArray f3295h;

    @Override // kotlin.collections.BooleanIterator
    public boolean b() {
        SparseBooleanArray sparseBooleanArray = this.f3295h;
        int i2 = this.f3294c;
        this.f3294c = i2 + 1;
        return sparseBooleanArray.valueAt(i2);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f3294c < this.f3295h.size();
    }
}
