package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import com.google.android.gms.common.api.Api;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes.dex */
final class zbug extends zbsl implements RandomAccess, zbul {

    /* renamed from: j, reason: collision with root package name */
    private static final zbug f12981j = new zbug(new int[0], 0, false);

    /* renamed from: h, reason: collision with root package name */
    private int[] f12982h;

    /* renamed from: i, reason: collision with root package name */
    private int f12983i;

    private zbug(int[] iArr, int i2, boolean z) {
        super(z);
        this.f12982h = iArr;
        this.f12983i = i2;
    }

    public static zbug f() {
        return f12981j;
    }

    private final String h(int i2) {
        return "Index:" + i2 + ", Size:" + this.f12983i;
    }

    private final void i(int i2) {
        if (i2 < 0 || i2 >= this.f12983i) {
            throw new IndexOutOfBoundsException(h(i2));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i2, Object obj) {
        int i3;
        int intValue = ((Integer) obj).intValue();
        b();
        if (i2 < 0 || i2 > (i3 = this.f12983i)) {
            throw new IndexOutOfBoundsException(h(i2));
        }
        int i4 = i2 + 1;
        int[] iArr = this.f12982h;
        if (i3 < iArr.length) {
            System.arraycopy(iArr, i2, iArr, i4, i3 - i2);
        } else {
            int[] iArr2 = new int[((i3 * 3) / 2) + 1];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            System.arraycopy(this.f12982h, i2, iArr2, i4, this.f12983i - i2);
            this.f12982h = iArr2;
        }
        this.f12982h[i2] = intValue;
        this.f12983i++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        b();
        byte[] bArr = zbuo.f12985b;
        collection.getClass();
        if (!(collection instanceof zbug)) {
            return super.addAll(collection);
        }
        zbug zbugVar = (zbug) collection;
        int i2 = zbugVar.f12983i;
        if (i2 == 0) {
            return false;
        }
        int i3 = this.f12983i;
        if (Api.BaseClientBuilder.API_PRIORITY_OTHER - i3 < i2) {
            throw new OutOfMemoryError();
        }
        int i4 = i3 + i2;
        int[] iArr = this.f12982h;
        if (i4 > iArr.length) {
            this.f12982h = Arrays.copyOf(iArr, i4);
        }
        System.arraycopy(zbugVar.f12982h, 0, this.f12982h, this.f12983i, zbugVar.f12983i);
        this.f12983i = i4;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final int d(int i2) {
        i(i2);
        return this.f12982h[i2];
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbun
    public final /* bridge */ /* synthetic */ zbun e(int i2) {
        if (i2 >= this.f12983i) {
            return new zbug(Arrays.copyOf(this.f12982h, i2), this.f12983i, true);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zbug)) {
            return super.equals(obj);
        }
        zbug zbugVar = (zbug) obj;
        if (this.f12983i != zbugVar.f12983i) {
            return false;
        }
        int[] iArr = zbugVar.f12982h;
        for (int i2 = 0; i2 < this.f12983i; i2++) {
            if (this.f12982h[i2] != iArr[i2]) {
                return false;
            }
        }
        return true;
    }

    public final void g(int i2) {
        b();
        int i3 = this.f12983i;
        int[] iArr = this.f12982h;
        if (i3 == iArr.length) {
            int[] iArr2 = new int[((i3 * 3) / 2) + 1];
            System.arraycopy(iArr, 0, iArr2, 0, i3);
            this.f12982h = iArr2;
        }
        int[] iArr3 = this.f12982h;
        int i4 = this.f12983i;
        this.f12983i = i4 + 1;
        iArr3[i4] = i2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i2) {
        i(i2);
        return Integer.valueOf(this.f12982h[i2]);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i2 = 1;
        for (int i3 = 0; i3 < this.f12983i; i3++) {
            i2 = (i2 * 31) + this.f12982h[i3];
        }
        return i2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int intValue = ((Integer) obj).intValue();
        int i2 = this.f12983i;
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.f12982h[i3] == intValue) {
                return i3;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i2) {
        b();
        i(i2);
        int[] iArr = this.f12982h;
        int i3 = iArr[i2];
        if (i2 < this.f12983i - 1) {
            System.arraycopy(iArr, i2 + 1, iArr, i2, (r2 - i2) - 1);
        }
        this.f12983i--;
        ((AbstractList) this).modCount++;
        return Integer.valueOf(i3);
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i2, int i3) {
        b();
        if (i3 < i2) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        int[] iArr = this.f12982h;
        System.arraycopy(iArr, i3, iArr, i2, this.f12983i - i3);
        this.f12983i -= i3 - i2;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i2, Object obj) {
        int intValue = ((Integer) obj).intValue();
        b();
        i(i2);
        int[] iArr = this.f12982h;
        int i3 = iArr[i2];
        iArr[i2] = intValue;
        return Integer.valueOf(i3);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f12983i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        g(((Integer) obj).intValue());
        return true;
    }
}
