package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.ConcurrentModificationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public final class ArraySetKt {
    public static final void a(ArraySet arraySet, int i2) {
        Intrinsics.e(arraySet, "<this>");
        arraySet.k(new int[i2]);
        arraySet.j(new Object[i2]);
    }

    public static final int b(ArraySet arraySet, int i2) {
        Intrinsics.e(arraySet, "<this>");
        try {
            return ContainerHelpersKt.a(arraySet.f(), arraySet.h(), i2);
        } catch (IndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public static final int c(ArraySet arraySet, Object obj, int i2) {
        Intrinsics.e(arraySet, "<this>");
        int h2 = arraySet.h();
        if (h2 == 0) {
            return -1;
        }
        int b2 = b(arraySet, i2);
        if (b2 < 0 || Intrinsics.a(obj, arraySet.d()[b2])) {
            return b2;
        }
        int i3 = b2 + 1;
        while (i3 < h2 && arraySet.f()[i3] == i2) {
            if (Intrinsics.a(obj, arraySet.d()[i3])) {
                return i3;
            }
            i3++;
        }
        for (int i4 = b2 - 1; i4 >= 0 && arraySet.f()[i4] == i2; i4--) {
            if (Intrinsics.a(obj, arraySet.d()[i4])) {
                return i4;
            }
        }
        return ~i3;
    }

    public static final int d(ArraySet arraySet) {
        Intrinsics.e(arraySet, "<this>");
        return c(arraySet, null, 0);
    }
}
