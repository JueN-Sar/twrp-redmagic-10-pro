package androidx.collection;

import java.lang.reflect.Array;

/* loaded from: classes.dex */
class ArraySetJvmUtil {
    static Object[] a(Object[] objArr, int i2) {
        if (objArr.length < i2) {
            return (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i2);
        }
        if (objArr.length > i2) {
            objArr[i2] = null;
        }
        return objArr;
    }
}
