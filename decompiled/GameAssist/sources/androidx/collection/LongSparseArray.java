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
public class LongSparseArray<E> implements Cloneable {

    /* renamed from: c, reason: collision with root package name */
    public /* synthetic */ boolean f1293c;

    /* renamed from: h, reason: collision with root package name */
    public /* synthetic */ long[] f1294h;

    /* renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object[] f1295i;

    /* renamed from: j, reason: collision with root package name */
    public /* synthetic */ int f1296j;

    public LongSparseArray() {
        this(0, 1, null);
    }

    public void a(long j2, Object obj) {
        Object obj2;
        int i2 = this.f1296j;
        if (i2 != 0 && j2 <= this.f1294h[i2 - 1]) {
            k(j2, obj);
            return;
        }
        if (this.f1293c) {
            long[] jArr = this.f1294h;
            if (i2 >= jArr.length) {
                Object[] objArr = this.f1295i;
                int i3 = 0;
                for (int i4 = 0; i4 < i2; i4++) {
                    Object obj3 = objArr[i4];
                    obj2 = LongSparseArrayKt.f1297a;
                    if (obj3 != obj2) {
                        if (i4 != i3) {
                            jArr[i3] = jArr[i4];
                            objArr[i3] = obj3;
                            objArr[i4] = null;
                        }
                        i3++;
                    }
                }
                this.f1293c = false;
                this.f1296j = i3;
            }
        }
        int i5 = this.f1296j;
        if (i5 >= this.f1294h.length) {
            int f2 = ContainerHelpersKt.f(i5 + 1);
            long[] copyOf = Arrays.copyOf(this.f1294h, f2);
            Intrinsics.d(copyOf, "copyOf(this, newSize)");
            this.f1294h = copyOf;
            Object[] copyOf2 = Arrays.copyOf(this.f1295i, f2);
            Intrinsics.d(copyOf2, "copyOf(this, newSize)");
            this.f1295i = copyOf2;
        }
        this.f1294h[i5] = j2;
        this.f1295i[i5] = obj;
        this.f1296j = i5 + 1;
    }

    public void b() {
        int i2 = this.f1296j;
        Object[] objArr = this.f1295i;
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = null;
        }
        this.f1296j = 0;
        this.f1293c = false;
    }

    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public LongSparseArray clone() {
        Object clone = super.clone();
        Intrinsics.c(clone, "null cannot be cast to non-null type androidx.collection.LongSparseArray<E of androidx.collection.LongSparseArray>");
        LongSparseArray longSparseArray = (LongSparseArray) clone;
        longSparseArray.f1294h = (long[]) this.f1294h.clone();
        longSparseArray.f1295i = (Object[]) this.f1295i.clone();
        return longSparseArray;
    }

    public boolean e(long j2) {
        return h(j2) >= 0;
    }

    public Object f(long j2) {
        Object obj;
        int b2 = ContainerHelpersKt.b(this.f1294h, this.f1296j, j2);
        if (b2 >= 0) {
            Object obj2 = this.f1295i[b2];
            obj = LongSparseArrayKt.f1297a;
            if (obj2 != obj) {
                return this.f1295i[b2];
            }
        }
        return null;
    }

    public Object g(long j2, Object obj) {
        Object obj2;
        int b2 = ContainerHelpersKt.b(this.f1294h, this.f1296j, j2);
        if (b2 < 0) {
            return obj;
        }
        Object obj3 = this.f1295i[b2];
        obj2 = LongSparseArrayKt.f1297a;
        return obj3 == obj2 ? obj : this.f1295i[b2];
    }

    public int h(long j2) {
        Object obj;
        if (this.f1293c) {
            int i2 = this.f1296j;
            long[] jArr = this.f1294h;
            Object[] objArr = this.f1295i;
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                Object obj2 = objArr[i4];
                obj = LongSparseArrayKt.f1297a;
                if (obj2 != obj) {
                    if (i4 != i3) {
                        jArr[i3] = jArr[i4];
                        objArr[i3] = obj2;
                        objArr[i4] = null;
                    }
                    i3++;
                }
            }
            this.f1293c = false;
            this.f1296j = i3;
        }
        return ContainerHelpersKt.b(this.f1294h, this.f1296j, j2);
    }

    public boolean i() {
        return n() == 0;
    }

    public long j(int i2) {
        int i3;
        Object obj;
        if (i2 < 0 || i2 >= (i3 = this.f1296j)) {
            throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i2).toString());
        }
        if (this.f1293c) {
            long[] jArr = this.f1294h;
            Object[] objArr = this.f1295i;
            int i4 = 0;
            for (int i5 = 0; i5 < i3; i5++) {
                Object obj2 = objArr[i5];
                obj = LongSparseArrayKt.f1297a;
                if (obj2 != obj) {
                    if (i5 != i4) {
                        jArr[i4] = jArr[i5];
                        objArr[i4] = obj2;
                        objArr[i5] = null;
                    }
                    i4++;
                }
            }
            this.f1293c = false;
            this.f1296j = i4;
        }
        return this.f1294h[i2];
    }

    public void k(long j2, Object obj) {
        Object obj2;
        Object obj3;
        int b2 = ContainerHelpersKt.b(this.f1294h, this.f1296j, j2);
        if (b2 >= 0) {
            this.f1295i[b2] = obj;
            return;
        }
        int i2 = ~b2;
        if (i2 < this.f1296j) {
            Object obj4 = this.f1295i[i2];
            obj3 = LongSparseArrayKt.f1297a;
            if (obj4 == obj3) {
                this.f1294h[i2] = j2;
                this.f1295i[i2] = obj;
                return;
            }
        }
        if (this.f1293c) {
            int i3 = this.f1296j;
            long[] jArr = this.f1294h;
            if (i3 >= jArr.length) {
                Object[] objArr = this.f1295i;
                int i4 = 0;
                for (int i5 = 0; i5 < i3; i5++) {
                    Object obj5 = objArr[i5];
                    obj2 = LongSparseArrayKt.f1297a;
                    if (obj5 != obj2) {
                        if (i5 != i4) {
                            jArr[i4] = jArr[i5];
                            objArr[i4] = obj5;
                            objArr[i5] = null;
                        }
                        i4++;
                    }
                }
                this.f1293c = false;
                this.f1296j = i4;
                i2 = ~ContainerHelpersKt.b(this.f1294h, i4, j2);
            }
        }
        int i6 = this.f1296j;
        if (i6 >= this.f1294h.length) {
            int f2 = ContainerHelpersKt.f(i6 + 1);
            long[] copyOf = Arrays.copyOf(this.f1294h, f2);
            Intrinsics.d(copyOf, "copyOf(this, newSize)");
            this.f1294h = copyOf;
            Object[] copyOf2 = Arrays.copyOf(this.f1295i, f2);
            Intrinsics.d(copyOf2, "copyOf(this, newSize)");
            this.f1295i = copyOf2;
        }
        int i7 = this.f1296j;
        if (i7 - i2 != 0) {
            long[] jArr2 = this.f1294h;
            int i8 = i2 + 1;
            ArraysKt___ArraysJvmKt.f(jArr2, jArr2, i8, i2, i7);
            Object[] objArr2 = this.f1295i;
            ArraysKt___ArraysJvmKt.g(objArr2, objArr2, i8, i2, this.f1296j);
        }
        this.f1294h[i2] = j2;
        this.f1295i[i2] = obj;
        this.f1296j++;
    }

    public void l(long j2) {
        Object obj;
        Object obj2;
        int b2 = ContainerHelpersKt.b(this.f1294h, this.f1296j, j2);
        if (b2 >= 0) {
            Object obj3 = this.f1295i[b2];
            obj = LongSparseArrayKt.f1297a;
            if (obj3 != obj) {
                Object[] objArr = this.f1295i;
                obj2 = LongSparseArrayKt.f1297a;
                objArr[b2] = obj2;
                this.f1293c = true;
            }
        }
    }

    public void m(int i2) {
        Object obj;
        Object obj2;
        Object obj3 = this.f1295i[i2];
        obj = LongSparseArrayKt.f1297a;
        if (obj3 != obj) {
            Object[] objArr = this.f1295i;
            obj2 = LongSparseArrayKt.f1297a;
            objArr[i2] = obj2;
            this.f1293c = true;
        }
    }

    public int n() {
        Object obj;
        if (this.f1293c) {
            int i2 = this.f1296j;
            long[] jArr = this.f1294h;
            Object[] objArr = this.f1295i;
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                Object obj2 = objArr[i4];
                obj = LongSparseArrayKt.f1297a;
                if (obj2 != obj) {
                    if (i4 != i3) {
                        jArr[i3] = jArr[i4];
                        objArr[i3] = obj2;
                        objArr[i4] = null;
                    }
                    i3++;
                }
            }
            this.f1293c = false;
            this.f1296j = i3;
        }
        return this.f1296j;
    }

    public Object o(int i2) {
        int i3;
        Object obj;
        if (i2 < 0 || i2 >= (i3 = this.f1296j)) {
            throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i2).toString());
        }
        if (this.f1293c) {
            long[] jArr = this.f1294h;
            Object[] objArr = this.f1295i;
            int i4 = 0;
            for (int i5 = 0; i5 < i3; i5++) {
                Object obj2 = objArr[i5];
                obj = LongSparseArrayKt.f1297a;
                if (obj2 != obj) {
                    if (i5 != i4) {
                        jArr[i4] = jArr[i5];
                        objArr[i4] = obj2;
                        objArr[i5] = null;
                    }
                    i4++;
                }
            }
            this.f1293c = false;
            this.f1296j = i4;
        }
        return this.f1295i[i2];
    }

    public String toString() {
        if (n() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f1296j * 28);
        sb.append('{');
        int i2 = this.f1296j;
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            sb.append(j(i3));
            sb.append('=');
            Object o2 = o(i3);
            if (o2 != sb) {
                sb.append(o2);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String sb2 = sb.toString();
        Intrinsics.d(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    public LongSparseArray(int i2) {
        if (i2 == 0) {
            this.f1294h = ContainerHelpersKt.f1414b;
            this.f1295i = ContainerHelpersKt.f1415c;
        } else {
            int f2 = ContainerHelpersKt.f(i2);
            this.f1294h = new long[f2];
            this.f1295i = new Object[f2];
        }
    }

    public /* synthetic */ LongSparseArray(int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 10 : i2);
    }
}
