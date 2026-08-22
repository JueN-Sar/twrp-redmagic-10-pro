package kotlin.jvm.internal;

import java.util.Iterator;
import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public final class ArrayIteratorKt {
    public static final Iterator a(Object[] array) {
        Intrinsics.e(array, "array");
        return new ArrayIterator(array);
    }
}
