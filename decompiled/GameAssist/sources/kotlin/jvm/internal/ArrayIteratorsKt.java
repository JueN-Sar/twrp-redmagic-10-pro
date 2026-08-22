package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.collections.BooleanIterator;
import kotlin.collections.ByteIterator;
import kotlin.collections.CharIterator;
import kotlin.collections.DoubleIterator;
import kotlin.collections.FloatIterator;
import kotlin.collections.IntIterator;
import kotlin.collections.LongIterator;
import kotlin.collections.ShortIterator;

@Metadata
/* loaded from: classes2.dex */
public final class ArrayIteratorsKt {
    public static final BooleanIterator a(boolean[] array) {
        Intrinsics.e(array, "array");
        return new ArrayBooleanIterator(array);
    }

    public static final ByteIterator b(byte[] array) {
        Intrinsics.e(array, "array");
        return new ArrayByteIterator(array);
    }

    public static final CharIterator c(char[] array) {
        Intrinsics.e(array, "array");
        return new ArrayCharIterator(array);
    }

    public static final DoubleIterator d(double[] array) {
        Intrinsics.e(array, "array");
        return new ArrayDoubleIterator(array);
    }

    public static final FloatIterator e(float[] array) {
        Intrinsics.e(array, "array");
        return new ArrayFloatIterator(array);
    }

    public static final IntIterator f(int[] array) {
        Intrinsics.e(array, "array");
        return new ArrayIntIterator(array);
    }

    public static final LongIterator g(long[] array) {
        Intrinsics.e(array, "array");
        return new ArrayLongIterator(array);
    }

    public static final ShortIterator h(short[] array) {
        Intrinsics.e(array, "array");
        return new ArrayShortIterator(array);
    }
}
