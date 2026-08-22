package kotlin.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public class ArraysKt___ArraysJvmKt extends ArraysKt__ArraysKt {
    public static List c(Object[] objArr) {
        Intrinsics.e(objArr, "<this>");
        List a2 = ArraysUtilJVM.a(objArr);
        Intrinsics.d(a2, "asList(this)");
        return a2;
    }

    public static byte[] d(byte[] bArr, byte[] destination, int i2, int i3, int i4) {
        Intrinsics.e(bArr, "<this>");
        Intrinsics.e(destination, "destination");
        System.arraycopy(bArr, i3, destination, i2, i4 - i3);
        return destination;
    }

    public static int[] e(int[] iArr, int[] destination, int i2, int i3, int i4) {
        Intrinsics.e(iArr, "<this>");
        Intrinsics.e(destination, "destination");
        System.arraycopy(iArr, i3, destination, i2, i4 - i3);
        return destination;
    }

    public static long[] f(long[] jArr, long[] destination, int i2, int i3, int i4) {
        Intrinsics.e(jArr, "<this>");
        Intrinsics.e(destination, "destination");
        System.arraycopy(jArr, i3, destination, i2, i4 - i3);
        return destination;
    }

    public static Object[] g(Object[] objArr, Object[] destination, int i2, int i3, int i4) {
        Intrinsics.e(objArr, "<this>");
        Intrinsics.e(destination, "destination");
        System.arraycopy(objArr, i3, destination, i2, i4 - i3);
        return destination;
    }

    public static /* synthetic */ int[] h(int[] iArr, int[] iArr2, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            i2 = 0;
        }
        if ((i5 & 4) != 0) {
            i3 = 0;
        }
        if ((i5 & 8) != 0) {
            i4 = iArr.length;
        }
        return e(iArr, iArr2, i2, i3, i4);
    }

    public static /* synthetic */ Object[] i(Object[] objArr, Object[] objArr2, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            i2 = 0;
        }
        if ((i5 & 4) != 0) {
            i3 = 0;
        }
        if ((i5 & 8) != 0) {
            i4 = objArr.length;
        }
        return g(objArr, objArr2, i2, i3, i4);
    }

    public static Object[] j(Object[] objArr, int i2, int i3) {
        Intrinsics.e(objArr, "<this>");
        ArraysKt__ArraysJVMKt.b(i3, objArr.length);
        Object[] copyOfRange = Arrays.copyOfRange(objArr, i2, i3);
        Intrinsics.d(copyOfRange, "copyOfRange(this, fromIndex, toIndex)");
        return copyOfRange;
    }

    public static void k(int[] iArr, int i2, int i3, int i4) {
        Intrinsics.e(iArr, "<this>");
        Arrays.fill(iArr, i3, i4, i2);
    }

    public static final void l(long[] jArr, long j2, int i2, int i3) {
        Intrinsics.e(jArr, "<this>");
        Arrays.fill(jArr, i2, i3, j2);
    }

    public static void m(Object[] objArr, Object obj, int i2, int i3) {
        Intrinsics.e(objArr, "<this>");
        Arrays.fill(objArr, i2, i3, obj);
    }

    public static /* synthetic */ void n(int[] iArr, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            i3 = 0;
        }
        if ((i5 & 4) != 0) {
            i4 = iArr.length;
        }
        k(iArr, i2, i3, i4);
    }

    public static /* synthetic */ void o(long[] jArr, long j2, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = jArr.length;
        }
        l(jArr, j2, i2, i3);
    }

    public static /* synthetic */ void p(Object[] objArr, Object obj, int i2, int i3, int i4, Object obj2) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = objArr.length;
        }
        m(objArr, obj, i2, i3);
    }

    public static Object[] q(Object[] objArr, Object obj) {
        Intrinsics.e(objArr, "<this>");
        int length = objArr.length;
        Object[] result = Arrays.copyOf(objArr, length + 1);
        result[length] = obj;
        Intrinsics.d(result, "result");
        return result;
    }

    public static final void r(Object[] objArr, Comparator comparator) {
        Intrinsics.e(objArr, "<this>");
        Intrinsics.e(comparator, "comparator");
        if (objArr.length > 1) {
            Arrays.sort(objArr, comparator);
        }
    }
}
