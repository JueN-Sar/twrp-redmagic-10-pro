package kotlin.ranges;

import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public class RangesKt___RangesKt extends RangesKt__RangesKt {
    public static int a(int i2, int i3) {
        return i2 < i3 ? i3 : i2;
    }

    public static long b(long j2, long j3) {
        return j2 < j3 ? j3 : j2;
    }

    public static int c(int i2, int i3) {
        return i2 > i3 ? i3 : i2;
    }

    public static long d(long j2, long j3) {
        return j2 > j3 ? j3 : j2;
    }

    public static int e(int i2, int i3, int i4) {
        if (i3 <= i4) {
            return i2 < i3 ? i3 : i2 > i4 ? i4 : i2;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + i4 + " is less than minimum " + i3 + '.');
    }

    public static long f(long j2, long j3, long j4) {
        if (j3 <= j4) {
            return j2 < j3 ? j3 : j2 > j4 ? j4 : j2;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + j4 + " is less than minimum " + j3 + '.');
    }

    public static IntProgression g(int i2, int i3) {
        return IntProgression.f18609j.a(i2, i3, -1);
    }

    public static IntRange h(int i2, int i3) {
        return i3 <= Integer.MIN_VALUE ? IntRange.f18617k.a() : new IntRange(i2, i3 - 1);
    }
}
