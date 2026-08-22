package kotlin.collections;

import java.lang.reflect.Array;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
class ArraysKt__ArraysJVMKt {
    public static final Object[] a(Object[] reference, int i2) {
        Intrinsics.e(reference, "reference");
        Object newInstance = Array.newInstance(reference.getClass().getComponentType(), i2);
        Intrinsics.c(newInstance, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.arrayOfNulls>");
        return (Object[]) newInstance;
    }

    public static final void b(int i2, int i3) {
        if (i2 <= i3) {
            return;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i2 + ") is greater than size (" + i3 + ").");
    }
}
