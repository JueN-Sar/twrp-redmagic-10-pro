package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.DoubleIterator;

@Metadata
/* loaded from: classes2.dex */
final class ArrayDoubleIterator extends DoubleIterator {

    /* renamed from: c, reason: collision with root package name */
    private final double[] f18535c;

    /* renamed from: h, reason: collision with root package name */
    private int f18536h;

    public ArrayDoubleIterator(double[] array) {
        Intrinsics.e(array, "array");
        this.f18535c = array;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18536h < this.f18535c.length;
    }

    @Override // kotlin.collections.DoubleIterator
    public double nextDouble() {
        try {
            double[] dArr = this.f18535c;
            int i2 = this.f18536h;
            this.f18536h = i2 + 1;
            return dArr[i2];
        } catch (ArrayIndexOutOfBoundsException e2) {
            this.f18536h--;
            throw new NoSuchElementException(e2.getMessage());
        }
    }
}
