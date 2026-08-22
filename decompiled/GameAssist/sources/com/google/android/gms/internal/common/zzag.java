package com.google.android.gms.internal.common;

import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import org.jspecify.nullness.NullMarked;

@NullMarked
/* loaded from: classes.dex */
public abstract class zzag extends zzac implements List, RandomAccess {
    private static final zzak zza = new zzae(zzai.zza, 0);

    zzag() {
    }

    static zzag l(Object[] objArr, int i2) {
        return i2 == 0 ? zzai.zza : new zzai(objArr, i2);
    }

    public static zzag m(Collection collection) {
        if (!(collection instanceof zzac)) {
            Object[] array = collection.toArray();
            int length = array.length;
            zzah.a(array, length);
            return l(array, length);
        }
        zzag g2 = ((zzac) collection).g();
        if (!g2.i()) {
            return g2;
        }
        Object[] array2 = g2.toArray();
        return l(array2, array2.length);
    }

    public static zzag n() {
        return zzai.zza;
    }

    public static zzag o(Object obj) {
        Object[] objArr = {obj};
        zzah.a(objArr, 1);
        return l(objArr, 1);
    }

    public static zzag p(Object obj, Object obj2) {
        Object[] objArr = {obj, obj2};
        zzah.a(objArr, 2);
        return l(objArr, 2);
    }

    @Override // java.util.List
    public final void add(int i2, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public final boolean addAll(int i2, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.common.zzac
    int b(Object[] objArr, int i2) {
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            objArr[i3] = get(i3);
        }
        return size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    for (int i2 = 0; i2 < size; i2++) {
                        if (zzr.a(get(i2), list.get(i2))) {
                        }
                    }
                    return true;
                }
                Iterator it = iterator();
                Iterator it2 = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (!it2.hasNext() || !zzr.a(it.next(), it2.next())) {
                            break;
                        }
                    } else if (!it2.hasNext()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.common.zzac
    public final zzag g() {
        return this;
    }

    @Override // com.google.android.gms.internal.common.zzac
    /* renamed from: h */
    public final zzaj iterator() {
        return listIterator(0);
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int size = size();
        int i2 = 1;
        for (int i3 = 0; i3 < size; i3++) {
            i2 = (i2 * 31) + get(i3).hashCode();
        }
        return i2;
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (obj.equals(get(i2))) {
                return i2;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.common.zzac, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public zzag subList(int i2, int i3) {
        zzs.c(i2, i3, size());
        int i4 = i3 - i2;
        return i4 == size() ? this : i4 == 0 ? zzai.zza : new zzaf(this, i2, i4);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final /* synthetic */ ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    /* renamed from: q, reason: merged with bridge method [inline-methods] */
    public final zzak listIterator(int i2) {
        zzs.b(i2, size(), VirtualHandleWrapper.KEY_INDEX);
        return isEmpty() ? zza : new zzae(this, i2);
    }

    @Override // java.util.List
    public final Object remove(int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public final Object set(int i2, Object obj) {
        throw new UnsupportedOperationException();
    }
}
