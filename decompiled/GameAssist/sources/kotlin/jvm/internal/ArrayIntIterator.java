package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.IntIterator;

@Metadata
/* loaded from: classes2.dex */
final class ArrayIntIterator extends IntIterator {

    /* renamed from: c, reason: collision with root package name */
    private final int[] f18539c;

    /* renamed from: h, reason: collision with root package name */
    private int f18540h;

    public ArrayIntIterator(int[] array) {
        Intrinsics.e(array, "array");
        this.f18539c = array;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18540h < this.f18539c.length;
    }

    @Override // kotlin.collections.IntIterator
    public int nextInt() {
        try {
            int[] iArr = this.f18539c;
            int i2 = this.f18540h;
            this.f18540h = i2 + 1;
            return iArr[i2];
        } catch (ArrayIndexOutOfBoundsException e2) {
            this.f18540h--;
            throw new NoSuchElementException(e2.getMessage());
        }
    }
}
