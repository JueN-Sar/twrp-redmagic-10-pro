package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableCollection;
import kotlin.jvm.internal.markers.KMutableSet;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class ArraySet<E> implements Collection<E>, Set<E>, KMutableCollection, KMutableSet {

    /* renamed from: c, reason: collision with root package name */
    private int[] f1182c;

    /* renamed from: h, reason: collision with root package name */
    private Object[] f1183h;

    /* renamed from: i, reason: collision with root package name */
    private int f1184i;

    @Metadata
    private final class ElementIterator extends IndexBasedArrayIterator<E> {
        public ElementIterator() {
            super(ArraySet.this.h());
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        protected Object b(int i2) {
            return ArraySet.this.m(i2);
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        protected void c(int i2) {
            ArraySet.this.i(i2);
        }
    }

    public ArraySet() {
        this(0, 1, null);
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(Object obj) {
        int i2;
        int c2;
        int h2 = h();
        if (obj == null) {
            c2 = ArraySetKt.d(this);
            i2 = 0;
        } else {
            int hashCode = obj.hashCode();
            i2 = hashCode;
            c2 = ArraySetKt.c(this, obj, hashCode);
        }
        if (c2 >= 0) {
            return false;
        }
        int i3 = ~c2;
        if (h2 >= f().length) {
            int i4 = 8;
            if (h2 >= 8) {
                i4 = (h2 >> 1) + h2;
            } else if (h2 < 4) {
                i4 = 4;
            }
            int[] f2 = f();
            Object[] d2 = d();
            ArraySetKt.a(this, i4);
            if (h2 != h()) {
                throw new ConcurrentModificationException();
            }
            if (!(f().length == 0)) {
                ArraysKt___ArraysJvmKt.h(f2, f(), 0, 0, f2.length, 6, null);
                ArraysKt___ArraysJvmKt.i(d2, d(), 0, 0, d2.length, 6, null);
            }
        }
        if (i3 < h2) {
            int i5 = i3 + 1;
            ArraysKt___ArraysJvmKt.e(f(), f(), i5, i3, h2);
            ArraysKt___ArraysJvmKt.g(d(), d(), i5, i3, h2);
        }
        if (h2 != h() || i3 >= f().length) {
            throw new ConcurrentModificationException();
        }
        f()[i3] = i2;
        d()[i3] = obj;
        l(h() + 1);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        b(h() + elements.size());
        Iterator<E> it = elements.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z |= add(it.next());
        }
        return z;
    }

    public final void b(int i2) {
        int h2 = h();
        if (f().length < i2) {
            int[] f2 = f();
            Object[] d2 = d();
            ArraySetKt.a(this, i2);
            if (h() > 0) {
                ArraysKt___ArraysJvmKt.h(f2, f(), 0, 0, h(), 6, null);
                ArraysKt___ArraysJvmKt.i(d2, d(), 0, 0, h(), 6, null);
            }
        }
        if (h() != h2) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        if (h() != 0) {
            k(ContainerHelpersKt.f1413a);
            j(ContainerHelpersKt.f1415c);
            l(0);
        }
        if (h() != 0) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        Iterator<E> it = elements.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final Object[] d() {
        return this.f1183h;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof Set) && size() == ((Set) obj).size()) {
            try {
                int h2 = h();
                for (int i2 = 0; i2 < h2; i2++) {
                    if (((Set) obj).contains(m(i2))) {
                    }
                }
                return true;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public final int[] f() {
        return this.f1182c;
    }

    public int g() {
        return this.f1184i;
    }

    public final int h() {
        return this.f1184i;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] f2 = f();
        int h2 = h();
        int i2 = 0;
        for (int i3 = 0; i3 < h2; i3++) {
            i2 += f2[i3];
        }
        return i2;
    }

    public final Object i(int i2) {
        int h2 = h();
        Object obj = d()[i2];
        if (h2 <= 1) {
            clear();
        } else {
            int i3 = h2 - 1;
            if (f().length <= 8 || h() >= f().length / 3) {
                if (i2 < i3) {
                    int i4 = i2 + 1;
                    ArraysKt___ArraysJvmKt.e(f(), f(), i2, i4, h2);
                    ArraysKt___ArraysJvmKt.g(d(), d(), i2, i4, h2);
                }
                d()[i3] = null;
            } else {
                int h3 = h() > 8 ? h() + (h() >> 1) : 8;
                int[] f2 = f();
                Object[] d2 = d();
                ArraySetKt.a(this, h3);
                if (i2 > 0) {
                    ArraysKt___ArraysJvmKt.h(f2, f(), 0, 0, i2, 6, null);
                    ArraysKt___ArraysJvmKt.i(d2, d(), 0, 0, i2, 6, null);
                }
                if (i2 < i3) {
                    int i5 = i2 + 1;
                    ArraysKt___ArraysJvmKt.e(f2, f(), i2, i5, h2);
                    ArraysKt___ArraysJvmKt.g(d2, d(), i2, i5, h2);
                }
            }
            if (h2 != h()) {
                throw new ConcurrentModificationException();
            }
            l(i3);
        }
        return obj;
    }

    public final int indexOf(Object obj) {
        return obj == null ? ArraySetKt.d(this) : ArraySetKt.c(this, obj, obj.hashCode());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return h() <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator iterator() {
        return new ElementIterator();
    }

    public final void j(Object[] objArr) {
        Intrinsics.e(objArr, "<set-?>");
        this.f1183h = objArr;
    }

    public final void k(int[] iArr) {
        Intrinsics.e(iArr, "<set-?>");
        this.f1182c = iArr;
    }

    public final void l(int i2) {
        this.f1184i = i2;
    }

    public final Object m(int i2) {
        return d()[i2];
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf < 0) {
            return false;
        }
        i(indexOf);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        Iterator<E> it = elements.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z |= remove(it.next());
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(Collection elements) {
        boolean x;
        Intrinsics.e(elements, "elements");
        boolean z = false;
        for (int h2 = h() - 1; -1 < h2; h2--) {
            x = CollectionsKt___CollectionsKt.x(elements, d()[h2]);
            if (!x) {
                i(h2);
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public final /* bridge */ int size() {
        return g();
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray() {
        Object[] j2;
        j2 = ArraysKt___ArraysJvmKt.j(this.f1183h, 0, this.f1184i);
        return j2;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(h() * 14);
        sb.append('{');
        int h2 = h();
        for (int i2 = 0; i2 < h2; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            Object m2 = m(i2);
            if (m2 != this) {
                sb.append(m2);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        String sb2 = sb.toString();
        Intrinsics.d(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    public ArraySet(int i2) {
        this.f1182c = ContainerHelpersKt.f1413a;
        this.f1183h = ContainerHelpersKt.f1415c;
        if (i2 > 0) {
            ArraySetKt.a(this, i2);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray(Object[] array) {
        Intrinsics.e(array, "array");
        Object[] result = ArraySetJvmUtil.a(array, this.f1184i);
        ArraysKt___ArraysJvmKt.g(this.f1183h, result, 0, 0, this.f1184i);
        Intrinsics.d(result, "result");
        return result;
    }

    public /* synthetic */ ArraySet(int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i2);
    }
}
