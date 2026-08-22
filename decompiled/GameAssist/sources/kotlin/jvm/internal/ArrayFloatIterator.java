package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.FloatIterator;

@Metadata
/* loaded from: classes2.dex */
final class ArrayFloatIterator extends FloatIterator {

    /* renamed from: c, reason: collision with root package name */
    private final float[] f18537c;

    /* renamed from: h, reason: collision with root package name */
    private int f18538h;

    public ArrayFloatIterator(float[] array) {
        Intrinsics.e(array, "array");
        this.f18537c = array;
    }

    @Override // kotlin.collections.FloatIterator
    public float b() {
        try {
            float[] fArr = this.f18537c;
            int i2 = this.f18538h;
            this.f18538h = i2 + 1;
            return fArr[i2];
        } catch (ArrayIndexOutOfBoundsException e2) {
            this.f18538h--;
            throw new NoSuchElementException(e2.getMessage());
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18538h < this.f18537c.length;
    }
}
