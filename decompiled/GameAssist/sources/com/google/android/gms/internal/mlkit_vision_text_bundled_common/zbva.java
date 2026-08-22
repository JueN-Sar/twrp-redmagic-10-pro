package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import com.google.android.gms.common.api.Api;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes.dex */
final class zbva extends zbsl implements RandomAccess, zbum {

    /* renamed from: j, reason: collision with root package name */
    private static final zbva f12991j = new zbva(new long[0], 0, false);

    /* renamed from: h, reason: collision with root package name */
    private long[] f12992h;

    /* renamed from: i, reason: collision with root package name */
    private int f12993i;

    private zbva(long[] jArr, int i2, boolean z) {
        super(z);
        this.f12992h = jArr;
        this.f12993i = i2;
    }

    public static zbva f() {
        return f12991j;
    }

    private final String h(int i2) {
        return "Index:" + i2 + ", Size:" + this.f12993i;
    }

    private final void i(int i2) {
        if (i2 < 0 || i2 >= this.f12993i) {
            throw new IndexOutOfBoundsException(h(i2));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i2, Object obj) {
        int i3;
        long longValue = ((Long) obj).longValue();
        b();
        if (i2 < 0 || i2 > (i3 = this.f12993i)) {
            throw new IndexOutOfBoundsException(h(i2));
        }
        int i4 = i2 + 1;
        long[] jArr = this.f12992h;
        if (i3 < jArr.length) {
            System.arraycopy(jArr, i2, jArr, i4, i3 - i2);
        } else {
            long[] jArr2 = new long[((i3 * 3) / 2) + 1];
            System.arraycopy(jArr, 0, jArr2, 0, i2);
            System.arraycopy(this.f12992h, i2, jArr2, i4, this.f12993i - i2);
            this.f12992h = jArr2;
        }
        this.f12992h[i2] = longValue;
        this.f12993i++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        b();
        byte[] bArr = zbuo.f12985b;
        collection.getClass();
        if (!(collection instanceof zbva)) {
            return super.addAll(collection);
        }
        zbva zbvaVar = (zbva) collection;
        int i2 = zbvaVar.f12993i;
        if (i2 == 0) {
            return false;
        }
        int i3 = this.f12993i;
        if (Api.BaseClientBuilder.API_PRIORITY_OTHER - i3 < i2) {
            throw new OutOfMemoryError();
        }
        int i4 = i3 + i2;
        long[] jArr = this.f12992h;
        if (i4 > jArr.length) {
            this.f12992h = Arrays.copyOf(jArr, i4);
        }
        System.arraycopy(zbvaVar.f12992h, 0, this.f12992h, this.f12993i, zbvaVar.f12993i);
        this.f12993i = i4;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final long d(int i2) {
        i(i2);
        return this.f12992h[i2];
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbun
    public final /* bridge */ /* synthetic */ zbun e(int i2) {
        if (i2 >= this.f12993i) {
            return new zbva(Arrays.copyOf(this.f12992h, i2), this.f12993i, true);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zbva)) {
            return super.equals(obj);
        }
        zbva zbvaVar = (zbva) obj;
        if (this.f12993i != zbvaVar.f12993i) {
            return false;
        }
        long[] jArr = zbvaVar.f12992h;
        for (int i2 = 0; i2 < this.f12993i; i2++) {
            if (this.f12992h[i2] != jArr[i2]) {
                return false;
            }
        }
        return true;
    }

    public final void g(long j2) {
        b();
        int i2 = this.f12993i;
        long[] jArr = this.f12992h;
        if (i2 == jArr.length) {
            long[] jArr2 = new long[((i2 * 3) / 2) + 1];
            System.arraycopy(jArr, 0, jArr2, 0, i2);
            this.f12992h = jArr2;
        }
        long[] jArr3 = this.f12992h;
        int i3 = this.f12993i;
        this.f12993i = i3 + 1;
        jArr3[i3] = j2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i2) {
        i(i2);
        return Long.valueOf(this.f12992h[i2]);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i2 = 1;
        for (int i3 = 0; i3 < this.f12993i; i3++) {
            long j2 = this.f12992h[i3];
            byte[] bArr = zbuo.f12985b;
            i2 = (i2 * 31) + ((int) (j2 ^ (j2 >>> 32)));
        }
        return i2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Long)) {
            return -1;
        }
        long longValue = ((Long) obj).longValue();
        int i2 = this.f12993i;
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.f12992h[i3] == longValue) {
                return i3;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i2) {
        b();
        i(i2);
        long[] jArr = this.f12992h;
        long j2 = jArr[i2];
        if (i2 < this.f12993i - 1) {
            System.arraycopy(jArr, i2 + 1, jArr, i2, (r3 - i2) - 1);
        }
        this.f12993i--;
        ((AbstractList) this).modCount++;
        return Long.valueOf(j2);
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i2, int i3) {
        b();
        if (i3 < i2) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        long[] jArr = this.f12992h;
        System.arraycopy(jArr, i3, jArr, i2, this.f12993i - i3);
        this.f12993i -= i3 - i2;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i2, Object obj) {
        long longValue = ((Long) obj).longValue();
        b();
        i(i2);
        long[] jArr = this.f12992h;
        long j2 = jArr[i2];
        jArr[i2] = longValue;
        return Long.valueOf(j2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f12993i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        g(((Long) obj).longValue());
        return true;
    }
}
