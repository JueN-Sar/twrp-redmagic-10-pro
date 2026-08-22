package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;

/* loaded from: classes.dex */
final class zbvv extends zbsl implements RandomAccess {

    /* renamed from: j, reason: collision with root package name */
    private static final zbvv f13026j = new zbvv(new Object[0], 0, false);

    /* renamed from: h, reason: collision with root package name */
    private Object[] f13027h;

    /* renamed from: i, reason: collision with root package name */
    private int f13028i;

    private zbvv(Object[] objArr, int i2, boolean z) {
        super(z);
        this.f13027h = objArr;
        this.f13028i = i2;
    }

    public static zbvv d() {
        return f13026j;
    }

    private final String f(int i2) {
        return "Index:" + i2 + ", Size:" + this.f13028i;
    }

    private final void g(int i2) {
        if (i2 < 0 || i2 >= this.f13028i) {
            throw new IndexOutOfBoundsException(f(i2));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.List
    public final void add(int i2, Object obj) {
        int i3;
        b();
        if (i2 < 0 || i2 > (i3 = this.f13028i)) {
            throw new IndexOutOfBoundsException(f(i2));
        }
        int i4 = i2 + 1;
        Object[] objArr = this.f13027h;
        if (i3 < objArr.length) {
            System.arraycopy(objArr, i2, objArr, i4, i3 - i2);
        } else {
            Object[] objArr2 = new Object[((i3 * 3) / 2) + 1];
            System.arraycopy(objArr, 0, objArr2, 0, i2);
            System.arraycopy(this.f13027h, i2, objArr2, i4, this.f13028i - i2);
            this.f13027h = objArr2;
        }
        this.f13027h[i2] = obj;
        this.f13028i++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbun
    public final /* bridge */ /* synthetic */ zbun e(int i2) {
        if (i2 >= this.f13028i) {
            return new zbvv(Arrays.copyOf(this.f13027h, i2), this.f13028i, true);
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i2) {
        g(i2);
        return this.f13027h[i2];
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.List
    public final Object remove(int i2) {
        b();
        g(i2);
        Object[] objArr = this.f13027h;
        Object obj = objArr[i2];
        if (i2 < this.f13028i - 1) {
            System.arraycopy(objArr, i2 + 1, objArr, i2, (r2 - i2) - 1);
        }
        this.f13028i--;
        ((AbstractList) this).modCount++;
        return obj;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.List
    public final Object set(int i2, Object obj) {
        b();
        g(i2);
        Object[] objArr = this.f13027h;
        Object obj2 = objArr[i2];
        objArr[i2] = obj;
        ((AbstractList) this).modCount++;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13028i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        b();
        int i2 = this.f13028i;
        Object[] objArr = this.f13027h;
        if (i2 == objArr.length) {
            this.f13027h = Arrays.copyOf(objArr, ((i2 * 3) / 2) + 1);
        }
        Object[] objArr2 = this.f13027h;
        int i3 = this.f13028i;
        this.f13028i = i3 + 1;
        objArr2[i3] = obj;
        ((AbstractList) this).modCount++;
        return true;
    }
}
