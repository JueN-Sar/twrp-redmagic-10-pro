package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public class SparseArrayCompat<E> implements Cloneable {

    /* renamed from: c, reason: collision with root package name */
    public /* synthetic */ boolean f1404c;

    /* renamed from: h, reason: collision with root package name */
    public /* synthetic */ int[] f1405h;

    /* renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object[] f1406i;

    /* renamed from: j, reason: collision with root package name */
    public /* synthetic */ int f1407j;

    public SparseArrayCompat() {
        this(0, 1, null);
    }

    public void a(int i2, Object obj) {
        int i3 = this.f1407j;
        if (i3 != 0 && i2 <= this.f1405h[i3 - 1]) {
            i(i2, obj);
            return;
        }
        if (this.f1404c && i3 >= this.f1405h.length) {
            SparseArrayCompatKt.e(this);
        }
        int i4 = this.f1407j;
        if (i4 >= this.f1405h.length) {
            int e2 = ContainerHelpersKt.e(i4 + 1);
            int[] copyOf = Arrays.copyOf(this.f1405h, e2);
            Intrinsics.d(copyOf, "copyOf(this, newSize)");
            this.f1405h = copyOf;
            Object[] copyOf2 = Arrays.copyOf(this.f1406i, e2);
            Intrinsics.d(copyOf2, "copyOf(this, newSize)");
            this.f1406i = copyOf2;
        }
        this.f1405h[i4] = i2;
        this.f1406i[i4] = obj;
        this.f1407j = i4 + 1;
    }

    public void b() {
        int i2 = this.f1407j;
        Object[] objArr = this.f1406i;
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = null;
        }
        this.f1407j = 0;
        this.f1404c = false;
    }

    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public SparseArrayCompat clone() {
        Object clone = super.clone();
        Intrinsics.c(clone, "null cannot be cast to non-null type androidx.collection.SparseArrayCompat<E of androidx.collection.SparseArrayCompat>");
        SparseArrayCompat sparseArrayCompat = (SparseArrayCompat) clone;
        sparseArrayCompat.f1405h = (int[]) this.f1405h.clone();
        sparseArrayCompat.f1406i = (Object[]) this.f1406i.clone();
        return sparseArrayCompat;
    }

    public Object e(int i2) {
        return SparseArrayCompatKt.c(this, i2);
    }

    public Object f(int i2, Object obj) {
        return SparseArrayCompatKt.d(this, i2, obj);
    }

    public int g(Object obj) {
        if (this.f1404c) {
            SparseArrayCompatKt.e(this);
        }
        int i2 = this.f1407j;
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.f1406i[i3] == obj) {
                return i3;
            }
        }
        return -1;
    }

    public int h(int i2) {
        if (this.f1404c) {
            SparseArrayCompatKt.e(this);
        }
        return this.f1405h[i2];
    }

    public void i(int i2, Object obj) {
        Object obj2;
        int a2 = ContainerHelpersKt.a(this.f1405h, this.f1407j, i2);
        if (a2 >= 0) {
            this.f1406i[a2] = obj;
            return;
        }
        int i3 = ~a2;
        if (i3 < this.f1407j) {
            Object obj3 = this.f1406i[i3];
            obj2 = SparseArrayCompatKt.f1408a;
            if (obj3 == obj2) {
                this.f1405h[i3] = i2;
                this.f1406i[i3] = obj;
                return;
            }
        }
        if (this.f1404c && this.f1407j >= this.f1405h.length) {
            SparseArrayCompatKt.e(this);
            i3 = ~ContainerHelpersKt.a(this.f1405h, this.f1407j, i2);
        }
        int i4 = this.f1407j;
        if (i4 >= this.f1405h.length) {
            int e2 = ContainerHelpersKt.e(i4 + 1);
            int[] copyOf = Arrays.copyOf(this.f1405h, e2);
            Intrinsics.d(copyOf, "copyOf(this, newSize)");
            this.f1405h = copyOf;
            Object[] copyOf2 = Arrays.copyOf(this.f1406i, e2);
            Intrinsics.d(copyOf2, "copyOf(this, newSize)");
            this.f1406i = copyOf2;
        }
        int i5 = this.f1407j;
        if (i5 - i3 != 0) {
            int[] iArr = this.f1405h;
            int i6 = i3 + 1;
            ArraysKt___ArraysJvmKt.e(iArr, iArr, i6, i3, i5);
            Object[] objArr = this.f1406i;
            ArraysKt___ArraysJvmKt.g(objArr, objArr, i6, i3, this.f1407j);
        }
        this.f1405h[i3] = i2;
        this.f1406i[i3] = obj;
        this.f1407j++;
    }

    public int j() {
        if (this.f1404c) {
            SparseArrayCompatKt.e(this);
        }
        return this.f1407j;
    }

    public Object k(int i2) {
        if (this.f1404c) {
            SparseArrayCompatKt.e(this);
        }
        return this.f1406i[i2];
    }

    public String toString() {
        if (j() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f1407j * 28);
        sb.append('{');
        int i2 = this.f1407j;
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            sb.append(h(i3));
            sb.append('=');
            Object k2 = k(i3);
            if (k2 != this) {
                sb.append(k2);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String sb2 = sb.toString();
        Intrinsics.d(sb2, "buffer.toString()");
        return sb2;
    }

    public SparseArrayCompat(int i2) {
        if (i2 == 0) {
            this.f1405h = ContainerHelpersKt.f1413a;
            this.f1406i = ContainerHelpersKt.f1415c;
        } else {
            int e2 = ContainerHelpersKt.e(i2);
            this.f1405h = new int[e2];
            this.f1406i = new Object[e2];
        }
    }

    public /* synthetic */ SparseArrayCompat(int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 10 : i2);
    }
}
