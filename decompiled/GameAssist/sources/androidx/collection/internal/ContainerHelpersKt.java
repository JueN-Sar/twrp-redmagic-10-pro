package androidx.collection.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public final class ContainerHelpersKt {

    /* renamed from: a, reason: collision with root package name */
    public static final int[] f1413a = new int[0];

    /* renamed from: b, reason: collision with root package name */
    public static final long[] f1414b = new long[0];

    /* renamed from: c, reason: collision with root package name */
    public static final Object[] f1415c = new Object[0];

    public static final int a(int[] array, int i2, int i3) {
        Intrinsics.e(array, "array");
        int i4 = i2 - 1;
        int i5 = 0;
        while (i5 <= i4) {
            int i6 = (i5 + i4) >>> 1;
            int i7 = array[i6];
            if (i7 < i3) {
                i5 = i6 + 1;
            } else {
                if (i7 <= i3) {
                    return i6;
                }
                i4 = i6 - 1;
            }
        }
        return ~i5;
    }

    public static final int b(long[] array, int i2, long j2) {
        Intrinsics.e(array, "array");
        int i3 = i2 - 1;
        int i4 = 0;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            long j3 = array[i5];
            if (j3 < j2) {
                i4 = i5 + 1;
            } else {
                if (j3 <= j2) {
                    return i5;
                }
                i3 = i5 - 1;
            }
        }
        return ~i4;
    }

    public static final boolean c(Object obj, Object obj2) {
        return Intrinsics.a(obj, obj2);
    }

    public static final int d(int i2) {
        for (int i3 = 4; i3 < 32; i3++) {
            int i4 = (1 << i3) - 12;
            if (i2 <= i4) {
                return i4;
            }
        }
        return i2;
    }

    public static final int e(int i2) {
        return d(i2 * 4) / 4;
    }

    public static final int f(int i2) {
        return d(i2 * 8) / 8;
    }
}
