package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import com.google.android.gms.common.api.Api;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes.dex */
final class zbtw extends zbsl implements RandomAccess, zbuk {

    /* renamed from: j, reason: collision with root package name */
    private static final zbtw f12970j = new zbtw(new float[0], 0, false);

    /* renamed from: h, reason: collision with root package name */
    private float[] f12971h;

    /* renamed from: i, reason: collision with root package name */
    private int f12972i;

    private zbtw(float[] fArr, int i2, boolean z) {
        super(z);
        this.f12971h = fArr;
        this.f12972i = i2;
    }

    public static zbtw f() {
        return f12970j;
    }

    private final String h(int i2) {
        return "Index:" + i2 + ", Size:" + this.f12972i;
    }

    private final void i(int i2) {
        if (i2 < 0 || i2 >= this.f12972i) {
            throw new IndexOutOfBoundsException(h(i2));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i2, Object obj) {
        int i3;
        float floatValue = ((Float) obj).floatValue();
        b();
        if (i2 < 0 || i2 > (i3 = this.f12972i)) {
            throw new IndexOutOfBoundsException(h(i2));
        }
        int i4 = i2 + 1;
        float[] fArr = this.f12971h;
        if (i3 < fArr.length) {
            System.arraycopy(fArr, i2, fArr, i4, i3 - i2);
        } else {
            float[] fArr2 = new float[((i3 * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, i2);
            System.arraycopy(this.f12971h, i2, fArr2, i4, this.f12972i - i2);
            this.f12971h = fArr2;
        }
        this.f12971h[i2] = floatValue;
        this.f12972i++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        b();
        byte[] bArr = zbuo.f12985b;
        collection.getClass();
        if (!(collection instanceof zbtw)) {
            return super.addAll(collection);
        }
        zbtw zbtwVar = (zbtw) collection;
        int i2 = zbtwVar.f12972i;
        if (i2 == 0) {
            return false;
        }
        int i3 = this.f12972i;
        if (Api.BaseClientBuilder.API_PRIORITY_OTHER - i3 < i2) {
            throw new OutOfMemoryError();
        }
        int i4 = i3 + i2;
        float[] fArr = this.f12971h;
        if (i4 > fArr.length) {
            this.f12971h = Arrays.copyOf(fArr, i4);
        }
        System.arraycopy(zbtwVar.f12971h, 0, this.f12971h, this.f12972i, zbtwVar.f12972i);
        this.f12972i = i4;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final float d(int i2) {
        i(i2);
        return this.f12971h[i2];
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbun
    public final /* bridge */ /* synthetic */ zbun e(int i2) {
        if (i2 >= this.f12972i) {
            return new zbtw(Arrays.copyOf(this.f12971h, i2), this.f12972i, true);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zbtw)) {
            return super.equals(obj);
        }
        zbtw zbtwVar = (zbtw) obj;
        if (this.f12972i != zbtwVar.f12972i) {
            return false;
        }
        float[] fArr = zbtwVar.f12971h;
        for (int i2 = 0; i2 < this.f12972i; i2++) {
            if (Float.floatToIntBits(this.f12971h[i2]) != Float.floatToIntBits(fArr[i2])) {
                return false;
            }
        }
        return true;
    }

    public final void g(float f2) {
        b();
        int i2 = this.f12972i;
        float[] fArr = this.f12971h;
        if (i2 == fArr.length) {
            float[] fArr2 = new float[((i2 * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, i2);
            this.f12971h = fArr2;
        }
        float[] fArr3 = this.f12971h;
        int i3 = this.f12972i;
        this.f12972i = i3 + 1;
        fArr3[i3] = f2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i2) {
        i(i2);
        return Float.valueOf(this.f12971h[i2]);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i2 = 1;
        for (int i3 = 0; i3 < this.f12972i; i3++) {
            i2 = (i2 * 31) + Float.floatToIntBits(this.f12971h[i3]);
        }
        return i2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Float)) {
            return -1;
        }
        float floatValue = ((Float) obj).floatValue();
        int i2 = this.f12972i;
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.f12971h[i3] == floatValue) {
                return i3;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i2) {
        b();
        i(i2);
        float[] fArr = this.f12971h;
        float f2 = fArr[i2];
        if (i2 < this.f12972i - 1) {
            System.arraycopy(fArr, i2 + 1, fArr, i2, (r2 - i2) - 1);
        }
        this.f12972i--;
        ((AbstractList) this).modCount++;
        return Float.valueOf(f2);
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i2, int i3) {
        b();
        if (i3 < i2) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        float[] fArr = this.f12971h;
        System.arraycopy(fArr, i3, fArr, i2, this.f12972i - i3);
        this.f12972i -= i3 - i2;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i2, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        b();
        i(i2);
        float[] fArr = this.f12971h;
        float f2 = fArr[i2];
        fArr[i2] = floatValue;
        return Float.valueOf(f2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f12972i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsl, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        g(((Float) obj).floatValue());
        return true;
    }
}
