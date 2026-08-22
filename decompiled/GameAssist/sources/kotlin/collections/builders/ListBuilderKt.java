package kotlin.collections.builders;

import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ListBuilderKt {
    public static final Object[] d(int i2) {
        if (i2 >= 0) {
            return new Object[i2];
        }
        throw new IllegalArgumentException("capacity must be non-negative.".toString());
    }

    public static final Object[] e(Object[] objArr, int i2) {
        Intrinsics.e(objArr, "<this>");
        Object[] copyOf = Arrays.copyOf(objArr, i2);
        Intrinsics.d(copyOf, "copyOf(this, newSize)");
        return copyOf;
    }

    public static final void f(Object[] objArr, int i2) {
        Intrinsics.e(objArr, "<this>");
        objArr[i2] = null;
    }

    public static final void g(Object[] objArr, int i2, int i3) {
        Intrinsics.e(objArr, "<this>");
        while (i2 < i3) {
            f(objArr, i2);
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean h(Object[] objArr, int i2, int i3, List list) {
        if (i3 != list.size()) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (!Intrinsics.a(objArr[i2 + i4], list.get(i4))) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int i(Object[] objArr, int i2, int i3) {
        int i4 = 1;
        for (int i5 = 0; i5 < i3; i5++) {
            Object obj = objArr[i2 + i5];
            i4 = (i4 * 31) + (obj != null ? obj.hashCode() : 0);
        }
        return i4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String j(Object[] objArr, int i2, int i3) {
        StringBuilder sb = new StringBuilder((i3 * 3) + 2);
        sb.append("[");
        for (int i4 = 0; i4 < i3; i4++) {
            if (i4 > 0) {
                sb.append(", ");
            }
            sb.append(objArr[i2 + i4]);
        }
        sb.append("]");
        String sb2 = sb.toString();
        Intrinsics.d(sb2, "sb.toString()");
        return sb2;
    }
}
