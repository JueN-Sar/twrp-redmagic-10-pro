package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public class SimpleArrayMap<K, V> {

    /* renamed from: c, reason: collision with root package name */
    private int[] f1401c;

    /* renamed from: h, reason: collision with root package name */
    private Object[] f1402h;

    /* renamed from: i, reason: collision with root package name */
    private int f1403i;

    public SimpleArrayMap() {
        this(0, 1, null);
    }

    private final int c(Object obj, int i2) {
        int i3 = this.f1403i;
        if (i3 == 0) {
            return -1;
        }
        int a2 = ContainerHelpersKt.a(this.f1401c, i3, i2);
        if (a2 < 0 || Intrinsics.a(obj, this.f1402h[a2 << 1])) {
            return a2;
        }
        int i4 = a2 + 1;
        while (i4 < i3 && this.f1401c[i4] == i2) {
            if (Intrinsics.a(obj, this.f1402h[i4 << 1])) {
                return i4;
            }
            i4++;
        }
        for (int i5 = a2 - 1; i5 >= 0 && this.f1401c[i5] == i2; i5--) {
            if (Intrinsics.a(obj, this.f1402h[i5 << 1])) {
                return i5;
            }
        }
        return ~i4;
    }

    private final int e() {
        int i2 = this.f1403i;
        if (i2 == 0) {
            return -1;
        }
        int a2 = ContainerHelpersKt.a(this.f1401c, i2, 0);
        if (a2 < 0 || this.f1402h[a2 << 1] == null) {
            return a2;
        }
        int i3 = a2 + 1;
        while (i3 < i2 && this.f1401c[i3] == 0) {
            if (this.f1402h[i3 << 1] == null) {
                return i3;
            }
            i3++;
        }
        for (int i4 = a2 - 1; i4 >= 0 && this.f1401c[i4] == 0; i4--) {
            if (this.f1402h[i4 << 1] == null) {
                return i4;
            }
        }
        return ~i3;
    }

    public final int a(Object obj) {
        int i2 = this.f1403i * 2;
        Object[] objArr = this.f1402h;
        if (obj == null) {
            for (int i3 = 1; i3 < i2; i3 += 2) {
                if (objArr[i3] == null) {
                    return i3 >> 1;
                }
            }
            return -1;
        }
        for (int i4 = 1; i4 < i2; i4 += 2) {
            if (Intrinsics.a(obj, objArr[i4])) {
                return i4 >> 1;
            }
        }
        return -1;
    }

    public void b(int i2) {
        int i3 = this.f1403i;
        int[] iArr = this.f1401c;
        if (iArr.length < i2) {
            int[] copyOf = Arrays.copyOf(iArr, i2);
            Intrinsics.d(copyOf, "copyOf(this, newSize)");
            this.f1401c = copyOf;
            Object[] copyOf2 = Arrays.copyOf(this.f1402h, i2 * 2);
            Intrinsics.d(copyOf2, "copyOf(this, newSize)");
            this.f1402h = copyOf2;
        }
        if (this.f1403i != i3) {
            throw new ConcurrentModificationException();
        }
    }

    public void clear() {
        if (this.f1403i > 0) {
            this.f1401c = ContainerHelpersKt.f1413a;
            this.f1402h = ContainerHelpersKt.f1415c;
            this.f1403i = 0;
        }
        if (this.f1403i > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return d(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return a(obj) >= 0;
    }

    public int d(Object obj) {
        return obj == null ? e() : c(obj, obj.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        try {
            if (obj instanceof SimpleArrayMap) {
                if (size() != ((SimpleArrayMap) obj).size()) {
                    return false;
                }
                SimpleArrayMap simpleArrayMap = (SimpleArrayMap) obj;
                int i2 = this.f1403i;
                for (int i3 = 0; i3 < i2; i3++) {
                    Object f2 = f(i3);
                    Object j2 = j(i3);
                    Object obj2 = simpleArrayMap.get(f2);
                    if (j2 == null) {
                        if (obj2 != null || !simpleArrayMap.containsKey(f2)) {
                            return false;
                        }
                    } else if (!Intrinsics.a(j2, obj2)) {
                        return false;
                    }
                }
                return true;
            }
            if (!(obj instanceof Map) || size() != ((Map) obj).size()) {
                return false;
            }
            int i4 = this.f1403i;
            for (int i5 = 0; i5 < i4; i5++) {
                Object f3 = f(i5);
                Object j3 = j(i5);
                Object obj3 = ((Map) obj).get(f3);
                if (j3 == null) {
                    if (obj3 != null || !((Map) obj).containsKey(f3)) {
                        return false;
                    }
                } else if (!Intrinsics.a(j3, obj3)) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    public Object f(int i2) {
        if (i2 >= 0 && i2 < this.f1403i) {
            return this.f1402h[i2 << 1];
        }
        throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i2).toString());
    }

    public void g(SimpleArrayMap map) {
        Intrinsics.e(map, "map");
        int i2 = map.f1403i;
        b(this.f1403i + i2);
        if (this.f1403i != 0) {
            for (int i3 = 0; i3 < i2; i3++) {
                put(map.f(i3), map.j(i3));
            }
        } else if (i2 > 0) {
            ArraysKt___ArraysJvmKt.e(map.f1401c, this.f1401c, 0, 0, i2);
            ArraysKt___ArraysJvmKt.g(map.f1402h, this.f1402h, 0, 0, i2 << 1);
            this.f1403i = i2;
        }
    }

    public Object get(Object obj) {
        int d2 = d(obj);
        if (d2 >= 0) {
            return this.f1402h[(d2 << 1) + 1];
        }
        return null;
    }

    public Object getOrDefault(Object obj, Object obj2) {
        int d2 = d(obj);
        return d2 >= 0 ? this.f1402h[(d2 << 1) + 1] : obj2;
    }

    public Object h(int i2) {
        int i3;
        if (i2 < 0 || i2 >= (i3 = this.f1403i)) {
            throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i2).toString());
        }
        Object[] objArr = this.f1402h;
        int i4 = i2 << 1;
        Object obj = objArr[i4 + 1];
        if (i3 <= 1) {
            clear();
        } else {
            int i5 = i3 - 1;
            int[] iArr = this.f1401c;
            if (iArr.length <= 8 || i3 >= iArr.length / 3) {
                if (i2 < i5) {
                    int i6 = i2 + 1;
                    ArraysKt___ArraysJvmKt.e(iArr, iArr, i2, i6, i3);
                    Object[] objArr2 = this.f1402h;
                    ArraysKt___ArraysJvmKt.g(objArr2, objArr2, i4, i6 << 1, i3 << 1);
                }
                Object[] objArr3 = this.f1402h;
                int i7 = i5 << 1;
                objArr3[i7] = null;
                objArr3[i7 + 1] = null;
            } else {
                int i8 = i3 > 8 ? i3 + (i3 >> 1) : 8;
                int[] copyOf = Arrays.copyOf(iArr, i8);
                Intrinsics.d(copyOf, "copyOf(this, newSize)");
                this.f1401c = copyOf;
                Object[] copyOf2 = Arrays.copyOf(this.f1402h, i8 << 1);
                Intrinsics.d(copyOf2, "copyOf(this, newSize)");
                this.f1402h = copyOf2;
                if (i3 != this.f1403i) {
                    throw new ConcurrentModificationException();
                }
                if (i2 > 0) {
                    ArraysKt___ArraysJvmKt.e(iArr, this.f1401c, 0, 0, i2);
                    ArraysKt___ArraysJvmKt.g(objArr, this.f1402h, 0, 0, i4);
                }
                if (i2 < i5) {
                    int i9 = i2 + 1;
                    ArraysKt___ArraysJvmKt.e(iArr, this.f1401c, i2, i9, i3);
                    ArraysKt___ArraysJvmKt.g(objArr, this.f1402h, i4, i9 << 1, i3 << 1);
                }
            }
            if (i3 != this.f1403i) {
                throw new ConcurrentModificationException();
            }
            this.f1403i = i5;
        }
        return obj;
    }

    public int hashCode() {
        int[] iArr = this.f1401c;
        Object[] objArr = this.f1402h;
        int i2 = this.f1403i;
        int i3 = 1;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            Object obj = objArr[i3];
            i5 += (obj != null ? obj.hashCode() : 0) ^ iArr[i4];
            i4++;
            i3 += 2;
        }
        return i5;
    }

    public Object i(int i2, Object obj) {
        if (i2 < 0 || i2 >= this.f1403i) {
            throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i2).toString());
        }
        int i3 = (i2 << 1) + 1;
        Object[] objArr = this.f1402h;
        Object obj2 = objArr[i3];
        objArr[i3] = obj;
        return obj2;
    }

    public boolean isEmpty() {
        return this.f1403i <= 0;
    }

    public Object j(int i2) {
        if (i2 >= 0 && i2 < this.f1403i) {
            return this.f1402h[(i2 << 1) + 1];
        }
        throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i2).toString());
    }

    public Object put(Object obj, Object obj2) {
        int i2 = this.f1403i;
        int hashCode = obj != null ? obj.hashCode() : 0;
        int c2 = obj != null ? c(obj, hashCode) : e();
        if (c2 >= 0) {
            int i3 = (c2 << 1) + 1;
            Object[] objArr = this.f1402h;
            Object obj3 = objArr[i3];
            objArr[i3] = obj2;
            return obj3;
        }
        int i4 = ~c2;
        int[] iArr = this.f1401c;
        if (i2 >= iArr.length) {
            int i5 = 8;
            if (i2 >= 8) {
                i5 = (i2 >> 1) + i2;
            } else if (i2 < 4) {
                i5 = 4;
            }
            int[] copyOf = Arrays.copyOf(iArr, i5);
            Intrinsics.d(copyOf, "copyOf(this, newSize)");
            this.f1401c = copyOf;
            Object[] copyOf2 = Arrays.copyOf(this.f1402h, i5 << 1);
            Intrinsics.d(copyOf2, "copyOf(this, newSize)");
            this.f1402h = copyOf2;
            if (i2 != this.f1403i) {
                throw new ConcurrentModificationException();
            }
        }
        if (i4 < i2) {
            int[] iArr2 = this.f1401c;
            int i6 = i4 + 1;
            ArraysKt___ArraysJvmKt.e(iArr2, iArr2, i6, i4, i2);
            Object[] objArr2 = this.f1402h;
            ArraysKt___ArraysJvmKt.g(objArr2, objArr2, i6 << 1, i4 << 1, this.f1403i << 1);
        }
        int i7 = this.f1403i;
        if (i2 == i7) {
            int[] iArr3 = this.f1401c;
            if (i4 < iArr3.length) {
                iArr3[i4] = hashCode;
                Object[] objArr3 = this.f1402h;
                int i8 = i4 << 1;
                objArr3[i8] = obj;
                objArr3[i8 + 1] = obj2;
                this.f1403i = i7 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public Object putIfAbsent(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 == null ? put(obj, obj2) : obj3;
    }

    public Object remove(Object obj) {
        int d2 = d(obj);
        if (d2 >= 0) {
            return h(d2);
        }
        return null;
    }

    public Object replace(Object obj, Object obj2) {
        int d2 = d(obj);
        if (d2 >= 0) {
            return i(d2, obj2);
        }
        return null;
    }

    public int size() {
        return this.f1403i;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f1403i * 28);
        sb.append('{');
        int i2 = this.f1403i;
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            Object f2 = f(i3);
            if (f2 != sb) {
                sb.append(f2);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object j2 = j(i3);
            if (j2 != sb) {
                sb.append(j2);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String sb2 = sb.toString();
        Intrinsics.d(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    public SimpleArrayMap(int i2) {
        this.f1401c = i2 == 0 ? ContainerHelpersKt.f1413a : new int[i2];
        this.f1402h = i2 == 0 ? ContainerHelpersKt.f1415c : new Object[i2 << 1];
    }

    public boolean remove(Object obj, Object obj2) {
        int d2 = d(obj);
        if (d2 < 0 || !Intrinsics.a(obj2, j(d2))) {
            return false;
        }
        h(d2);
        return true;
    }

    public boolean replace(Object obj, Object obj2, Object obj3) {
        int d2 = d(obj);
        if (d2 < 0 || !Intrinsics.a(obj2, j(d2))) {
            return false;
        }
        i(d2, obj3);
        return true;
    }

    public /* synthetic */ SimpleArrayMap(int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i2);
    }

    public SimpleArrayMap(SimpleArrayMap simpleArrayMap) {
        this(0, 1, null);
        if (simpleArrayMap != null) {
            g(simpleArrayMap);
        }
    }
}
