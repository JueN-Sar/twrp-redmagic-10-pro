package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CharIterator;

@Metadata
/* loaded from: classes2.dex */
final class ArrayCharIterator extends CharIterator {

    /* renamed from: c, reason: collision with root package name */
    private final char[] f18533c;

    /* renamed from: h, reason: collision with root package name */
    private int f18534h;

    public ArrayCharIterator(char[] array) {
        Intrinsics.e(array, "array");
        this.f18533c = array;
    }

    @Override // kotlin.collections.CharIterator
    public char b() {
        try {
            char[] cArr = this.f18533c;
            int i2 = this.f18534h;
            this.f18534h = i2 + 1;
            return cArr[i2];
        } catch (ArrayIndexOutOfBoundsException e2) {
            this.f18534h--;
            throw new NoSuchElementException(e2.getMessage());
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18534h < this.f18533c.length;
    }
}
