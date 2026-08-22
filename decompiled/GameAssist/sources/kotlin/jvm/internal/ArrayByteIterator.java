package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.ByteIterator;

@Metadata
/* loaded from: classes2.dex */
final class ArrayByteIterator extends ByteIterator {

    /* renamed from: c, reason: collision with root package name */
    private final byte[] f18531c;

    /* renamed from: h, reason: collision with root package name */
    private int f18532h;

    public ArrayByteIterator(byte[] array) {
        Intrinsics.e(array, "array");
        this.f18531c = array;
    }

    @Override // kotlin.collections.ByteIterator
    public byte b() {
        try {
            byte[] bArr = this.f18531c;
            int i2 = this.f18532h;
            this.f18532h = i2 + 1;
            return bArr[i2];
        } catch (ArrayIndexOutOfBoundsException e2) {
            this.f18532h--;
            throw new NoSuchElementException(e2.getMessage());
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18532h < this.f18531c.length;
    }
}
