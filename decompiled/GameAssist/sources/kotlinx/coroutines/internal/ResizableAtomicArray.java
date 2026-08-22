package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
public final class ResizableAtomicArray<T> {

    @NotNull
    private volatile AtomicReferenceArray<T> array;

    public ResizableAtomicArray(int i2) {
        this.array = new AtomicReferenceArray<>(i2);
    }

    public final int a() {
        return this.array.length();
    }

    public final Object b(int i2) {
        AtomicReferenceArray<T> atomicReferenceArray = this.array;
        if (i2 < atomicReferenceArray.length()) {
            return atomicReferenceArray.get(i2);
        }
        return null;
    }

    public final void c(int i2, Object obj) {
        int a2;
        AtomicReferenceArray<T> atomicReferenceArray = this.array;
        int length = atomicReferenceArray.length();
        if (i2 < length) {
            atomicReferenceArray.set(i2, obj);
            return;
        }
        a2 = RangesKt___RangesKt.a(i2 + 1, length * 2);
        AtomicReferenceArray<T> atomicReferenceArray2 = new AtomicReferenceArray<>(a2);
        for (int i3 = 0; i3 < length; i3++) {
            atomicReferenceArray2.set(i3, atomicReferenceArray.get(i3));
        }
        atomicReferenceArray2.set(i2, obj);
        this.array = atomicReferenceArray2;
    }
}
