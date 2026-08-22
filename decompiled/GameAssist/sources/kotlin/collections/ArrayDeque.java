package kotlin.collections;

import com.google.android.gms.common.api.Api;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt___RangesKt;

@SinceKotlin
@Metadata
@WasExperimental
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ArrayDeque<E> extends AbstractMutableList<E> {

    /* renamed from: j, reason: collision with root package name */
    public static final Companion f18309j = new Companion(null);

    /* renamed from: k, reason: collision with root package name */
    private static final Object[] f18310k = new Object[0];

    /* renamed from: c, reason: collision with root package name */
    private int f18311c;

    /* renamed from: h, reason: collision with root package name */
    private Object[] f18312h = f18310k;

    /* renamed from: i, reason: collision with root package name */
    private int f18313i;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public final int a(int i2, int i3) {
            int i4 = i2 + (i2 >> 1);
            if (i4 - i3 < 0) {
                i4 = i3;
            }
            if (i4 - 2147483639 > 0) {
                return i3 > 2147483639 ? Api.BaseClientBuilder.API_PRIORITY_OTHER : 2147483639;
            }
            return i4;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final void f(int i2, Collection collection) {
        Iterator<E> it = collection.iterator();
        int length = this.f18312h.length;
        while (i2 < length && it.hasNext()) {
            this.f18312h[i2] = it.next();
            i2++;
        }
        int i3 = this.f18311c;
        for (int i4 = 0; i4 < i3 && it.hasNext(); i4++) {
            this.f18312h[i4] = it.next();
        }
        this.f18313i = size() + collection.size();
    }

    private final void g(int i2) {
        Object[] objArr = new Object[i2];
        Object[] objArr2 = this.f18312h;
        ArraysKt___ArraysJvmKt.g(objArr2, objArr, 0, this.f18311c, objArr2.length);
        Object[] objArr3 = this.f18312h;
        int length = objArr3.length;
        int i3 = this.f18311c;
        ArraysKt___ArraysJvmKt.g(objArr3, objArr, length - i3, 0, i3);
        this.f18311c = 0;
        this.f18312h = objArr;
    }

    private final int h(int i2) {
        return i2 == 0 ? ArraysKt___ArraysKt.B(this.f18312h) : i2 - 1;
    }

    private final void i(int i2) {
        int a2;
        if (i2 < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] objArr = this.f18312h;
        if (i2 <= objArr.length) {
            return;
        }
        if (objArr != f18310k) {
            g(f18309j.a(objArr.length, i2));
        } else {
            a2 = RangesKt___RangesKt.a(i2, 10);
            this.f18312h = new Object[a2];
        }
    }

    private final int j(int i2) {
        if (i2 == ArraysKt___ArraysKt.B(this.f18312h)) {
            return 0;
        }
        return i2 + 1;
    }

    private final int k(int i2) {
        return i2 < 0 ? i2 + this.f18312h.length : i2;
    }

    private final int l(int i2) {
        Object[] objArr = this.f18312h;
        return i2 >= objArr.length ? i2 - objArr.length : i2;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(Object obj) {
        addLast(obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        if (elements.isEmpty()) {
            return false;
        }
        i(size() + elements.size());
        f(l(this.f18311c + size()), elements);
        return true;
    }

    public final void addFirst(Object obj) {
        i(size() + 1);
        int h2 = h(this.f18311c);
        this.f18311c = h2;
        this.f18312h[h2] = obj;
        this.f18313i = size() + 1;
    }

    public final void addLast(Object obj) {
        i(size() + 1);
        this.f18312h[l(this.f18311c + size())] = obj;
        this.f18313i = size() + 1;
    }

    @Override // kotlin.collections.AbstractMutableList
    public int b() {
        return this.f18313i;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        int l2 = l(this.f18311c + size());
        int i2 = this.f18311c;
        if (i2 < l2) {
            ArraysKt___ArraysJvmKt.m(this.f18312h, null, i2, l2);
        } else if (!isEmpty()) {
            Object[] objArr = this.f18312h;
            ArraysKt___ArraysJvmKt.m(objArr, null, this.f18311c, objArr.length);
            ArraysKt___ArraysJvmKt.m(this.f18312h, null, 0, l2);
        }
        this.f18311c = 0;
        this.f18313i = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // kotlin.collections.AbstractMutableList
    public Object d(int i2) {
        AbstractList.Companion.b(i2, size());
        if (i2 == CollectionsKt__CollectionsKt.i(this)) {
            return removeLast();
        }
        if (i2 == 0) {
            return removeFirst();
        }
        int l2 = l(this.f18311c + i2);
        Object obj = this.f18312h[l2];
        if (i2 < (size() >> 1)) {
            int i3 = this.f18311c;
            if (l2 >= i3) {
                Object[] objArr = this.f18312h;
                ArraysKt___ArraysJvmKt.g(objArr, objArr, i3 + 1, i3, l2);
            } else {
                Object[] objArr2 = this.f18312h;
                ArraysKt___ArraysJvmKt.g(objArr2, objArr2, 1, 0, l2);
                Object[] objArr3 = this.f18312h;
                objArr3[0] = objArr3[objArr3.length - 1];
                int i4 = this.f18311c;
                ArraysKt___ArraysJvmKt.g(objArr3, objArr3, i4 + 1, i4, objArr3.length - 1);
            }
            Object[] objArr4 = this.f18312h;
            int i5 = this.f18311c;
            objArr4[i5] = null;
            this.f18311c = j(i5);
        } else {
            int l3 = l(this.f18311c + CollectionsKt__CollectionsKt.i(this));
            if (l2 <= l3) {
                Object[] objArr5 = this.f18312h;
                ArraysKt___ArraysJvmKt.g(objArr5, objArr5, l2, l2 + 1, l3 + 1);
            } else {
                Object[] objArr6 = this.f18312h;
                ArraysKt___ArraysJvmKt.g(objArr6, objArr6, l2, l2 + 1, objArr6.length);
                Object[] objArr7 = this.f18312h;
                objArr7[objArr7.length - 1] = objArr7[0];
                ArraysKt___ArraysJvmKt.g(objArr7, objArr7, 0, 1, l3 + 1);
            }
            this.f18312h[l3] = null;
        }
        this.f18313i = size() - 1;
        return obj;
    }

    @Override // java.util.AbstractList, java.util.List
    public Object get(int i2) {
        AbstractList.Companion.b(i2, size());
        return this.f18312h[l(this.f18311c + i2)];
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        int i2;
        int l2 = l(this.f18311c + size());
        int i3 = this.f18311c;
        if (i3 < l2) {
            while (i3 < l2) {
                if (Intrinsics.a(obj, this.f18312h[i3])) {
                    i2 = this.f18311c;
                } else {
                    i3++;
                }
            }
            return -1;
        }
        if (i3 < l2) {
            return -1;
        }
        int length = this.f18312h.length;
        while (true) {
            if (i3 >= length) {
                for (int i4 = 0; i4 < l2; i4++) {
                    if (Intrinsics.a(obj, this.f18312h[i4])) {
                        i3 = i4 + this.f18312h.length;
                        i2 = this.f18311c;
                    }
                }
                return -1;
            }
            if (Intrinsics.a(obj, this.f18312h[i3])) {
                i2 = this.f18311c;
                break;
            }
            i3++;
        }
        return i3 - i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return size() == 0;
    }

    public final Object last() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        return this.f18312h[l(this.f18311c + CollectionsKt__CollectionsKt.i(this))];
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object obj) {
        int B;
        int i2;
        int l2 = l(this.f18311c + size());
        int i3 = this.f18311c;
        if (i3 < l2) {
            B = l2 - 1;
            if (i3 <= B) {
                while (!Intrinsics.a(obj, this.f18312h[B])) {
                    if (B != i3) {
                        B--;
                    }
                }
                i2 = this.f18311c;
                return B - i2;
            }
            return -1;
        }
        if (i3 > l2) {
            int i4 = l2 - 1;
            while (true) {
                if (-1 >= i4) {
                    B = ArraysKt___ArraysKt.B(this.f18312h);
                    int i5 = this.f18311c;
                    if (i5 <= B) {
                        while (!Intrinsics.a(obj, this.f18312h[B])) {
                            if (B != i5) {
                                B--;
                            }
                        }
                        i2 = this.f18311c;
                    }
                } else {
                    if (Intrinsics.a(obj, this.f18312h[i4])) {
                        B = i4 + this.f18312h.length;
                        i2 = this.f18311c;
                        break;
                    }
                    i4--;
                }
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return false;
        }
        remove(indexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection elements) {
        int l2;
        Intrinsics.e(elements, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.f18312h.length != 0) {
            int l3 = l(this.f18311c + size());
            int i2 = this.f18311c;
            if (i2 < l3) {
                l2 = i2;
                while (i2 < l3) {
                    Object obj = this.f18312h[i2];
                    if (!elements.contains(obj)) {
                        this.f18312h[l2] = obj;
                        l2++;
                    } else {
                        z = true;
                    }
                    i2++;
                }
                ArraysKt___ArraysJvmKt.m(this.f18312h, null, l2, l3);
            } else {
                int length = this.f18312h.length;
                boolean z2 = false;
                int i3 = i2;
                while (i2 < length) {
                    Object[] objArr = this.f18312h;
                    Object obj2 = objArr[i2];
                    objArr[i2] = null;
                    if (!elements.contains(obj2)) {
                        this.f18312h[i3] = obj2;
                        i3++;
                    } else {
                        z2 = true;
                    }
                    i2++;
                }
                l2 = l(i3);
                for (int i4 = 0; i4 < l3; i4++) {
                    Object[] objArr2 = this.f18312h;
                    Object obj3 = objArr2[i4];
                    objArr2[i4] = null;
                    if (!elements.contains(obj3)) {
                        this.f18312h[l2] = obj3;
                        l2 = j(l2);
                    } else {
                        z2 = true;
                    }
                }
                z = z2;
            }
            if (z) {
                this.f18313i = k(l2 - this.f18311c);
            }
        }
        return z;
    }

    public final Object removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        Object[] objArr = this.f18312h;
        int i2 = this.f18311c;
        Object obj = objArr[i2];
        objArr[i2] = null;
        this.f18311c = j(i2);
        this.f18313i = size() - 1;
        return obj;
    }

    public final Object removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        int l2 = l(this.f18311c + CollectionsKt__CollectionsKt.i(this));
        Object[] objArr = this.f18312h;
        Object obj = objArr[l2];
        objArr[l2] = null;
        this.f18313i = size() - 1;
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection elements) {
        int l2;
        Intrinsics.e(elements, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.f18312h.length != 0) {
            int l3 = l(this.f18311c + size());
            int i2 = this.f18311c;
            if (i2 < l3) {
                l2 = i2;
                while (i2 < l3) {
                    Object obj = this.f18312h[i2];
                    if (elements.contains(obj)) {
                        this.f18312h[l2] = obj;
                        l2++;
                    } else {
                        z = true;
                    }
                    i2++;
                }
                ArraysKt___ArraysJvmKt.m(this.f18312h, null, l2, l3);
            } else {
                int length = this.f18312h.length;
                boolean z2 = false;
                int i3 = i2;
                while (i2 < length) {
                    Object[] objArr = this.f18312h;
                    Object obj2 = objArr[i2];
                    objArr[i2] = null;
                    if (elements.contains(obj2)) {
                        this.f18312h[i3] = obj2;
                        i3++;
                    } else {
                        z2 = true;
                    }
                    i2++;
                }
                l2 = l(i3);
                for (int i4 = 0; i4 < l3; i4++) {
                    Object[] objArr2 = this.f18312h;
                    Object obj3 = objArr2[i4];
                    objArr2[i4] = null;
                    if (elements.contains(obj3)) {
                        this.f18312h[l2] = obj3;
                        l2 = j(l2);
                    } else {
                        z2 = true;
                    }
                }
                z = z2;
            }
            if (z) {
                this.f18313i = k(l2 - this.f18311c);
            }
        }
        return z;
    }

    @Override // java.util.AbstractList, java.util.List
    public Object set(int i2, Object obj) {
        AbstractList.Companion.b(i2, size());
        int l2 = l(this.f18311c + i2);
        Object[] objArr = this.f18312h;
        Object obj2 = objArr[l2];
        objArr[l2] = obj;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray(Object[] array) {
        Intrinsics.e(array, "array");
        if (array.length < size()) {
            array = ArraysKt__ArraysJVMKt.a(array, size());
        }
        int l2 = l(this.f18311c + size());
        int i2 = this.f18311c;
        if (i2 < l2) {
            ArraysKt___ArraysJvmKt.i(this.f18312h, array, 0, i2, l2, 2, null);
        } else if (!isEmpty()) {
            Object[] objArr = this.f18312h;
            ArraysKt___ArraysJvmKt.g(objArr, array, 0, this.f18311c, objArr.length);
            Object[] objArr2 = this.f18312h;
            ArraysKt___ArraysJvmKt.g(objArr2, array, objArr2.length - this.f18311c, 0, l2);
        }
        if (array.length > size()) {
            array[size()] = null;
        }
        return array;
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i2, Object obj) {
        AbstractList.Companion.c(i2, size());
        if (i2 == size()) {
            addLast(obj);
            return;
        }
        if (i2 == 0) {
            addFirst(obj);
            return;
        }
        i(size() + 1);
        int l2 = l(this.f18311c + i2);
        if (i2 < ((size() + 1) >> 1)) {
            int h2 = h(l2);
            int h3 = h(this.f18311c);
            int i3 = this.f18311c;
            if (h2 >= i3) {
                Object[] objArr = this.f18312h;
                objArr[h3] = objArr[i3];
                ArraysKt___ArraysJvmKt.g(objArr, objArr, i3, i3 + 1, h2 + 1);
            } else {
                Object[] objArr2 = this.f18312h;
                ArraysKt___ArraysJvmKt.g(objArr2, objArr2, i3 - 1, i3, objArr2.length);
                Object[] objArr3 = this.f18312h;
                objArr3[objArr3.length - 1] = objArr3[0];
                ArraysKt___ArraysJvmKt.g(objArr3, objArr3, 0, 1, h2 + 1);
            }
            this.f18312h[h2] = obj;
            this.f18311c = h3;
        } else {
            int l3 = l(this.f18311c + size());
            if (l2 < l3) {
                Object[] objArr4 = this.f18312h;
                ArraysKt___ArraysJvmKt.g(objArr4, objArr4, l2 + 1, l2, l3);
            } else {
                Object[] objArr5 = this.f18312h;
                ArraysKt___ArraysJvmKt.g(objArr5, objArr5, 1, 0, l3);
                Object[] objArr6 = this.f18312h;
                objArr6[0] = objArr6[objArr6.length - 1];
                ArraysKt___ArraysJvmKt.g(objArr6, objArr6, l2 + 1, l2, objArr6.length - 1);
            }
            this.f18312h[l2] = obj;
        }
        this.f18313i = size() + 1;
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int i2, Collection elements) {
        Intrinsics.e(elements, "elements");
        AbstractList.Companion.c(i2, size());
        if (elements.isEmpty()) {
            return false;
        }
        if (i2 == size()) {
            return addAll(elements);
        }
        i(size() + elements.size());
        int l2 = l(this.f18311c + size());
        int l3 = l(this.f18311c + i2);
        int size = elements.size();
        if (i2 < ((size() + 1) >> 1)) {
            int i3 = this.f18311c;
            int i4 = i3 - size;
            if (l3 < i3) {
                Object[] objArr = this.f18312h;
                ArraysKt___ArraysJvmKt.g(objArr, objArr, i4, i3, objArr.length);
                if (size >= l3) {
                    Object[] objArr2 = this.f18312h;
                    ArraysKt___ArraysJvmKt.g(objArr2, objArr2, objArr2.length - size, 0, l3);
                } else {
                    Object[] objArr3 = this.f18312h;
                    ArraysKt___ArraysJvmKt.g(objArr3, objArr3, objArr3.length - size, 0, size);
                    Object[] objArr4 = this.f18312h;
                    ArraysKt___ArraysJvmKt.g(objArr4, objArr4, 0, size, l3);
                }
            } else if (i4 >= 0) {
                Object[] objArr5 = this.f18312h;
                ArraysKt___ArraysJvmKt.g(objArr5, objArr5, i4, i3, l3);
            } else {
                Object[] objArr6 = this.f18312h;
                i4 += objArr6.length;
                int i5 = l3 - i3;
                int length = objArr6.length - i4;
                if (length >= i5) {
                    ArraysKt___ArraysJvmKt.g(objArr6, objArr6, i4, i3, l3);
                } else {
                    ArraysKt___ArraysJvmKt.g(objArr6, objArr6, i4, i3, i3 + length);
                    Object[] objArr7 = this.f18312h;
                    ArraysKt___ArraysJvmKt.g(objArr7, objArr7, 0, this.f18311c + length, l3);
                }
            }
            this.f18311c = i4;
            f(k(l3 - size), elements);
        } else {
            int i6 = l3 + size;
            if (l3 < l2) {
                int i7 = size + l2;
                Object[] objArr8 = this.f18312h;
                if (i7 <= objArr8.length) {
                    ArraysKt___ArraysJvmKt.g(objArr8, objArr8, i6, l3, l2);
                } else if (i6 >= objArr8.length) {
                    ArraysKt___ArraysJvmKt.g(objArr8, objArr8, i6 - objArr8.length, l3, l2);
                } else {
                    int length2 = l2 - (i7 - objArr8.length);
                    ArraysKt___ArraysJvmKt.g(objArr8, objArr8, 0, length2, l2);
                    Object[] objArr9 = this.f18312h;
                    ArraysKt___ArraysJvmKt.g(objArr9, objArr9, i6, l3, length2);
                }
            } else {
                Object[] objArr10 = this.f18312h;
                ArraysKt___ArraysJvmKt.g(objArr10, objArr10, size, 0, l2);
                Object[] objArr11 = this.f18312h;
                if (i6 >= objArr11.length) {
                    ArraysKt___ArraysJvmKt.g(objArr11, objArr11, i6 - objArr11.length, l3, objArr11.length);
                } else {
                    ArraysKt___ArraysJvmKt.g(objArr11, objArr11, 0, objArr11.length - size, objArr11.length);
                    Object[] objArr12 = this.f18312h;
                    ArraysKt___ArraysJvmKt.g(objArr12, objArr12, i6, l3, objArr12.length - size);
                }
            }
            f(l3, elements);
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }
}
