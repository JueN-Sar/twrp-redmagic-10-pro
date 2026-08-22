package kotlin.jvm.internal;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmName;

@Metadata
@JvmName
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class CollectionToArray {

    /* renamed from: a, reason: collision with root package name */
    private static final Object[] f18557a = new Object[0];

    public static final Object[] a(Collection collection) {
        Intrinsics.e(collection, "collection");
        int size = collection.size();
        if (size != 0) {
            Iterator it = collection.iterator();
            if (it.hasNext()) {
                Object[] objArr = new Object[size];
                int i2 = 0;
                while (true) {
                    int i3 = i2 + 1;
                    objArr[i2] = it.next();
                    if (i3 >= objArr.length) {
                        if (!it.hasNext()) {
                            return objArr;
                        }
                        int i4 = ((i3 * 3) + 1) >>> 1;
                        if (i4 <= i3) {
                            i4 = 2147483645;
                            if (i3 >= 2147483645) {
                                throw new OutOfMemoryError();
                            }
                        }
                        objArr = Arrays.copyOf(objArr, i4);
                        Intrinsics.d(objArr, "copyOf(result, newSize)");
                    } else if (!it.hasNext()) {
                        Object[] copyOf = Arrays.copyOf(objArr, i3);
                        Intrinsics.d(copyOf, "copyOf(result, size)");
                        return copyOf;
                    }
                    i2 = i3;
                }
            }
        }
        return f18557a;
    }

    public static final Object[] b(Collection collection, Object[] objArr) {
        Object[] objArr2;
        Intrinsics.e(collection, "collection");
        if (objArr == null) {
            throw null;
        }
        int size = collection.size();
        int i2 = 0;
        if (size == 0) {
            if (objArr.length <= 0) {
                return objArr;
            }
            objArr[0] = null;
            return objArr;
        }
        Iterator it = collection.iterator();
        if (!it.hasNext()) {
            if (objArr.length <= 0) {
                return objArr;
            }
            objArr[0] = null;
            return objArr;
        }
        if (size <= objArr.length) {
            objArr2 = objArr;
        } else {
            Object newInstance = Array.newInstance(objArr.getClass().getComponentType(), size);
            Intrinsics.c(newInstance, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            objArr2 = (Object[]) newInstance;
        }
        while (true) {
            int i3 = i2 + 1;
            objArr2[i2] = it.next();
            if (i3 >= objArr2.length) {
                if (!it.hasNext()) {
                    return objArr2;
                }
                int i4 = ((i3 * 3) + 1) >>> 1;
                if (i4 <= i3) {
                    i4 = 2147483645;
                    if (i3 >= 2147483645) {
                        throw new OutOfMemoryError();
                    }
                }
                objArr2 = Arrays.copyOf(objArr2, i4);
                Intrinsics.d(objArr2, "copyOf(result, newSize)");
            } else if (!it.hasNext()) {
                if (objArr2 == objArr) {
                    objArr[i3] = null;
                    return objArr;
                }
                Object[] copyOf = Arrays.copyOf(objArr2, i3);
                Intrinsics.d(copyOf, "copyOf(result, size)");
                return copyOf;
            }
            i2 = i3;
        }
    }
}
