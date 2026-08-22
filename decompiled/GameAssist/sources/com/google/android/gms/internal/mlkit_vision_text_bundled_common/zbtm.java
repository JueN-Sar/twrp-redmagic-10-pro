package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import com.google.android.gms.common.api.Api;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes.dex */
final class zbtm extends zbsl implements RandomAccess, zbun {

    /* renamed from: h, reason: collision with root package name */
    private double[] f12958h;

    /* renamed from: i, reason: collision with root package name */
    private int f12959i;

    static {
        new zbtm(new double[0], 0, false);
    }

    private zbtm(double[] dArr, int i2, boolean z) {
        super(z);
        this.f12958h = dArr;
        this.f12959i = i2;
    }

    private final String g(int i2) {
        return "Index:" + i2 + ", Size:" + this.f12959i;
    }

    private final void h(int i2) {
        if (i2 < 0 || i2 >= this.f12959i) {
            throw new IndexOutOfBoundsException(g(i2));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i2, Object obj) {
        int i3;
        double doubleValue = ((Double) obj).doubleValue();
        b();
        if (i2 < 0 || i2 > (i3 = this.f12959i)) {
            throw new IndexOutOfBoundsException(g(i2));
        }
        int i4 = i2 + 1;
        double[] dArr = this.f12958h;
        if (i3 < dArr.length) {
            System.arraycopy(dArr, i2, dArr, i4, i3 - i2);
        } else {
            double[] dArr2 = new double[((i3 * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, i2);
            System.arraycopy(this.f12958h, i2, dArr2, i4, this.f12959i - i2);
            this.f12958h = dArr2;
        }
        this.f12958h[i2] = doubleValue;
        this.f12959i++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        b();
        byte[] bArr = zbuo.f12985b;
        collection.getClass();
        if (!(collection instanceof zbtm)) {
            return super.addAll(collection);
        }
        zbtm zbtmVar = (zbtm) collection;
        int i2 = zbtmVar.f12959i;
        if (i2 == 0) {
            return false;
        }
        int i3 = this.f12959i;
        if (Api.BaseClientBuilder.API_PRIORITY_OTHER - i3 < i2) {
            throw new OutOfMemoryError();
        }
        int i4 = i3 + i2;
        double[] dArr = this.f12958h;
        if (i4 > dArr.length) {
            this.f12958h = Arrays.copyOf(dArr, i4);
        }
        System.arraycopy(zbtmVar.f12958h, 0, this.f12958h, this.f12959i, zbtmVar.f12959i);
        this.f12959i = i4;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final double d(int i2) {
        h(i2);
        return this.f12958h[i2];
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbun
    public final /* bridge */ /* synthetic */ zbun e(int i2) {
        if (i2 >= this.f12959i) {
            return new zbtm(Arrays.copyOf(this.f12958h, i2), this.f12959i, true);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zbtm)) {
            return super.equals(obj);
        }
        zbtm zbtmVar = (zbtm) obj;
        if (this.f12959i != zbtmVar.f12959i) {
            return false;
        }
        double[] dArr = zbtmVar.f12958h;
        for (int i2 = 0; i2 < this.f12959i; i2++) {
            if (Double.doubleToLongBits(this.f12958h[i2]) != Double.doubleToLongBits(dArr[i2])) {
                return false;
            }
        }
        return true;
    }

    public final void f(double d2) {
        b();
        int i2 = this.f12959i;
        double[] dArr = this.f12958h;
        if (i2 == dArr.length) {
            double[] dArr2 = new double[((i2 * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, i2);
            this.f12958h = dArr2;
        }
        double[] dArr3 = this.f12958h;
        int i3 = this.f12959i;
        this.f12959i = i3 + 1;
        dArr3[i3] = d2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i2) {
        h(i2);
        return Double.valueOf(this.f12958h[i2]);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i2 = 1;
        for (int i3 = 0; i3 < this.f12959i; i3++) {
            long doubleToLongBits = Double.doubleToLongBits(this.f12958h[i3]);
            byte[] bArr = zbuo.f12985b;
            i2 = (i2 * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        }
        return i2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Double)) {
            return -1;
        }
        double doubleValue = ((Double) obj).doubleValue();
        int i2 = this.f12959i;
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.f12958h[i3] == doubleValue) {
                return i3;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i2) {
        b();
        h(i2);
        double[] dArr = this.f12958h;
        double d2 = dArr[i2];
        if (i2 < this.f12959i - 1) {
            System.arraycopy(dArr, i2 + 1, dArr, i2, (r3 - i2) - 1);
        }
        this.f12959i--;
        ((AbstractList) this).modCount++;
        return Double.valueOf(d2);
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i2, int i3) {
        b();
        if (i3 < i2) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        double[] dArr = this.f12958h;
        System.arraycopy(dArr, i3, dArr, i2, this.f12959i - i3);
        this.f12959i -= i3 - i2;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i2, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        b();
        h(i2);
        double[] dArr = this.f12958h;
        double d2 = dArr[i2];
        dArr[i2] = doubleValue;
        return Double.valueOf(d2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f12959i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        f(((Double) obj).doubleValue());
        return true;
    }
}
