package kotlin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__AppendableKt;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public class ArraysKt___ArraysKt extends ArraysKt___ArraysJvmKt {
    public static final Collection A(Object[] objArr, Collection destination) {
        Intrinsics.e(objArr, "<this>");
        Intrinsics.e(destination, "destination");
        for (Object obj : objArr) {
            if (obj != null) {
                destination.add(obj);
            }
        }
        return destination;
    }

    public static final int B(Object[] objArr) {
        Intrinsics.e(objArr, "<this>");
        return objArr.length - 1;
    }

    public static Object C(Object[] objArr, int i2) {
        Intrinsics.e(objArr, "<this>");
        if (i2 < 0 || i2 > B(objArr)) {
            return null;
        }
        return objArr[i2];
    }

    public static int D(byte[] bArr, byte b2) {
        Intrinsics.e(bArr, "<this>");
        int length = bArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (b2 == bArr[i2]) {
                return i2;
            }
        }
        return -1;
    }

    public static final int E(char[] cArr, char c2) {
        Intrinsics.e(cArr, "<this>");
        int length = cArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (c2 == cArr[i2]) {
                return i2;
            }
        }
        return -1;
    }

    public static int F(int[] iArr, int i2) {
        Intrinsics.e(iArr, "<this>");
        int length = iArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (i2 == iArr[i3]) {
                return i3;
            }
        }
        return -1;
    }

    public static int G(long[] jArr, long j2) {
        Intrinsics.e(jArr, "<this>");
        int length = jArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (j2 == jArr[i2]) {
                return i2;
            }
        }
        return -1;
    }

    public static int H(Object[] objArr, Object obj) {
        Intrinsics.e(objArr, "<this>");
        int i2 = 0;
        if (obj == null) {
            int length = objArr.length;
            while (i2 < length) {
                if (objArr[i2] == null) {
                    return i2;
                }
                i2++;
            }
            return -1;
        }
        int length2 = objArr.length;
        while (i2 < length2) {
            if (Intrinsics.a(obj, objArr[i2])) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static int I(short[] sArr, short s2) {
        Intrinsics.e(sArr, "<this>");
        int length = sArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (s2 == sArr[i2]) {
                return i2;
            }
        }
        return -1;
    }

    public static final int J(boolean[] zArr, boolean z) {
        Intrinsics.e(zArr, "<this>");
        int length = zArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (z == zArr[i2]) {
                return i2;
            }
        }
        return -1;
    }

    public static final Appendable K(Object[] objArr, Appendable buffer, CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated, Function1 function1) {
        Intrinsics.e(objArr, "<this>");
        Intrinsics.e(buffer, "buffer");
        Intrinsics.e(separator, "separator");
        Intrinsics.e(prefix, "prefix");
        Intrinsics.e(postfix, "postfix");
        Intrinsics.e(truncated, "truncated");
        buffer.append(prefix);
        int i3 = 0;
        for (Object obj : objArr) {
            i3++;
            if (i3 > 1) {
                buffer.append(separator);
            }
            if (i2 >= 0 && i3 > i2) {
                break;
            }
            StringsKt__AppendableKt.a(buffer, obj, function1);
        }
        if (i2 >= 0 && i3 > i2) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    public static int M(byte[] bArr, byte b2) {
        Intrinsics.e(bArr, "<this>");
        int length = bArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (b2 == bArr[length]) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    public static final int N(char[] cArr, char c2) {
        Intrinsics.e(cArr, "<this>");
        int length = cArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (c2 == cArr[length]) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    public static int O(int[] iArr, int i2) {
        Intrinsics.e(iArr, "<this>");
        int length = iArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i3 = length - 1;
                if (i2 == iArr[length]) {
                    return length;
                }
                if (i3 < 0) {
                    break;
                }
                length = i3;
            }
        }
        return -1;
    }

    public static int P(long[] jArr, long j2) {
        Intrinsics.e(jArr, "<this>");
        int length = jArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (j2 == jArr[length]) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    public static int Q(short[] sArr, short s2) {
        Intrinsics.e(sArr, "<this>");
        int length = sArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (s2 == sArr[length]) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    public static final int R(boolean[] zArr, boolean z) {
        Intrinsics.e(zArr, "<this>");
        int length = zArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (z == zArr[length]) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    public static char S(char[] cArr) {
        Intrinsics.e(cArr, "<this>");
        int length = cArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return cArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }

    public static Object T(Object[] objArr) {
        Intrinsics.e(objArr, "<this>");
        if (objArr.length == 1) {
            return objArr[0];
        }
        return null;
    }

    public static final Object[] U(Object[] objArr, Comparator comparator) {
        Intrinsics.e(objArr, "<this>");
        Intrinsics.e(comparator, "comparator");
        if (objArr.length == 0) {
            return objArr;
        }
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.d(copyOf, "copyOf(this, size)");
        ArraysKt___ArraysJvmKt.r(copyOf, comparator);
        return copyOf;
    }

    public static List V(Object[] objArr, Comparator comparator) {
        Intrinsics.e(objArr, "<this>");
        Intrinsics.e(comparator, "comparator");
        return ArraysKt___ArraysJvmKt.c(U(objArr, comparator));
    }

    public static List W(Object[] objArr) {
        Intrinsics.e(objArr, "<this>");
        int length = objArr.length;
        return length != 0 ? length != 1 ? X(objArr) : CollectionsKt__CollectionsJVMKt.e(objArr[0]) : CollectionsKt__CollectionsKt.g();
    }

    public static final List X(Object[] objArr) {
        Intrinsics.e(objArr, "<this>");
        return new ArrayList(CollectionsKt__CollectionsKt.f(objArr));
    }

    public static boolean s(byte[] bArr, byte b2) {
        Intrinsics.e(bArr, "<this>");
        return D(bArr, b2) >= 0;
    }

    public static final boolean t(char[] cArr, char c2) {
        Intrinsics.e(cArr, "<this>");
        return E(cArr, c2) >= 0;
    }

    public static boolean u(int[] iArr, int i2) {
        Intrinsics.e(iArr, "<this>");
        return F(iArr, i2) >= 0;
    }

    public static boolean v(long[] jArr, long j2) {
        Intrinsics.e(jArr, "<this>");
        return G(jArr, j2) >= 0;
    }

    public static boolean w(Object[] objArr, Object obj) {
        Intrinsics.e(objArr, "<this>");
        return H(objArr, obj) >= 0;
    }

    public static boolean x(short[] sArr, short s2) {
        Intrinsics.e(sArr, "<this>");
        return I(sArr, s2) >= 0;
    }

    public static final boolean y(boolean[] zArr, boolean z) {
        Intrinsics.e(zArr, "<this>");
        return J(zArr, z) >= 0;
    }

    public static List z(Object[] objArr) {
        Intrinsics.e(objArr, "<this>");
        return (List) A(objArr, new ArrayList());
    }
}
