package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.BooleanIterator;

@Metadata
/* loaded from: classes2.dex */
final class ArrayBooleanIterator extends BooleanIterator {

    /* renamed from: c, reason: collision with root package name */
    private final boolean[] f18529c;

    /* renamed from: h, reason: collision with root package name */
    private int f18530h;

    public ArrayBooleanIterator(boolean[] array) {
        Intrinsics.e(array, "array");
        this.f18529c = array;
    }

    @Override // kotlin.collections.BooleanIterator
    public boolean b() {
        try {
            boolean[] zArr = this.f18529c;
            int i2 = this.f18530h;
            this.f18530h = i2 + 1;
            return zArr[i2];
        } catch (ArrayIndexOutOfBoundsException e2) {
            this.f18530h--;
            throw new NoSuchElementException(e2.getMessage());
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18530h < this.f18529c.length;
    }
}
