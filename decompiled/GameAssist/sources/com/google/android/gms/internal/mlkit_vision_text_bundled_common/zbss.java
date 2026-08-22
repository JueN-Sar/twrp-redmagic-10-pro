package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import com.google.android.gms.common.api.Api;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes.dex */
final class zbss extends zbsl implements RandomAccess, zbun {

    /* renamed from: h, reason: collision with root package name */
    private boolean[] f12942h;

    /* renamed from: i, reason: collision with root package name */
    private int f12943i;

    static {
        new zbss(new boolean[0], 0, false);
    }

    private zbss(boolean[] zArr, int i2, boolean z) {
        super(z);
        this.f12942h = zArr;
        this.f12943i = i2;
    }

    private final String g(int i2) {
        return "Index:" + i2 + ", Size:" + this.f12943i;
    }

    private final void h(int i2) {
        if (i2 < 0 || i2 >= this.f12943i) {
            throw new IndexOutOfBoundsException(g(i2));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i2, Object obj) {
        int i3;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        b();
        if (i2 < 0 || i2 > (i3 = this.f12943i)) {
            throw new IndexOutOfBoundsException(g(i2));
        }
        int i4 = i2 + 1;
        boolean[] zArr = this.f12942h;
        if (i3 < zArr.length) {
            System.arraycopy(zArr, i2, zArr, i4, i3 - i2);
        } else {
            boolean[] zArr2 = new boolean[((i3 * 3) / 2) + 1];
            System.arraycopy(zArr, 0, zArr2, 0, i2);
            System.arraycopy(this.f12942h, i2, zArr2, i4, this.f12943i - i2);
            this.f12942h = zArr2;
        }
        this.f12942h[i2] = booleanValue;
        this.f12943i++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        b();
        byte[] bArr = zbuo.f12985b;
        collection.getClass();
        if (!(collection instanceof zbss)) {
            return super.addAll(collection);
        }
        zbss zbssVar = (zbss) collection;
        int i2 = zbssVar.f12943i;
        if (i2 == 0) {
            return false;
        }
        int i3 = this.f12943i;
        if (Api.BaseClientBuilder.API_PRIORITY_OTHER - i3 < i2) {
            throw new OutOfMemoryError();
        }
        int i4 = i3 + i2;
        boolean[] zArr = this.f12942h;
        if (i4 > zArr.length) {
            this.f12942h = Arrays.copyOf(zArr, i4);
        }
        System.arraycopy(zbssVar.f12942h, 0, this.f12942h, this.f12943i, zbssVar.f12943i);
        this.f12943i = i4;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final void d(boolean z) {
        b();
        int i2 = this.f12943i;
        boolean[] zArr = this.f12942h;
        if (i2 == zArr.length) {
            boolean[] zArr2 = new boolean[((i2 * 3) / 2) + 1];
            System.arraycopy(zArr, 0, zArr2, 0, i2);
            this.f12942h = zArr2;
        }
        boolean[] zArr3 = this.f12942h;
        int i3 = this.f12943i;
        this.f12943i = i3 + 1;
        zArr3[i3] = z;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbun
    public final /* bridge */ /* synthetic */ zbun e(int i2) {
        if (i2 >= this.f12943i) {
            return new zbss(Arrays.copyOf(this.f12942h, i2), this.f12943i, true);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zbss)) {
            return super.equals(obj);
        }
        zbss zbssVar = (zbss) obj;
        if (this.f12943i != zbssVar.f12943i) {
            return false;
        }
        boolean[] zArr = zbssVar.f12942h;
        for (int i2 = 0; i2 < this.f12943i; i2++) {
            if (this.f12942h[i2] != zArr[i2]) {
                return false;
            }
        }
        return true;
    }

    public final boolean f(int i2) {
        h(i2);
        return this.f12942h[i2];
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i2) {
        h(i2);
        return Boolean.valueOf(this.f12942h[i2]);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i2 = 1;
        for (int i3 = 0; i3 < this.f12943i; i3++) {
            i2 = (i2 * 31) + zbuo.a(this.f12942h[i3]);
        }
        return i2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Boolean)) {
            return -1;
        }
        boolean booleanValue = ((Boolean) obj).booleanValue();
        int i2 = this.f12943i;
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.f12942h[i3] == booleanValue) {
                return i3;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i2) {
        b();
        h(i2);
        boolean[] zArr = this.f12942h;
        boolean z = zArr[i2];
        if (i2 < this.f12943i - 1) {
            System.arraycopy(zArr, i2 + 1, zArr, i2, (r2 - i2) - 1);
        }
        this.f12943i--;
        ((AbstractList) this).modCount++;
        return Boolean.valueOf(z);
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i2, int i3) {
        b();
        if (i3 < i2) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        boolean[] zArr = this.f12942h;
        System.arraycopy(zArr, i3, zArr, i2, this.f12943i - i3);
        this.f12943i -= i3 - i2;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i2, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        b();
        h(i2);
        boolean[] zArr = this.f12942h;
        boolean z = zArr[i2];
        zArr[i2] = booleanValue;
        return Boolean.valueOf(z);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f12943i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        d(((Boolean) obj).booleanValue());
        return true;
    }
}
